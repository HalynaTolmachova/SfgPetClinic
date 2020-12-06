package spgpetclinic.controllers;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spgpetclinic.model.Owner;
import spgpetclinic.model.Pet;
import spgpetclinic.model.PetType;
import spgpetclinic.services.OwnerService;
import spgpetclinic.services.PetService;
import spgpetclinic.services.PetTypeService;

import java.util.Collection;

@Controller
@RequestMapping("owners/{ownerId}")
public class PetIndexController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public PetIndexController(OwnerService ownerService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("pets/new")
    public String initCreatingForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;

    }

    @PostMapping("pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, Model model) {
    if(StringUtils.hasLength(pet.getName())&& pet.isNew()&& owner.getPet(pet.getName(),true)!=null) {
        result.rejectValue("name", "duplicate", "already exist");
    }
    owner.getPets().add(pet);
    if(result.hasErrors()) {
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }else {
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
    @GetMapping("pets/{petId}/edit")
    public String initUpdateForm(@PathVariable ("petId") Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("pets/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet,BindingResult result, Owner owner, Model model) {
        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }else{
        owner.getPets().add(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
        }

    }



}
