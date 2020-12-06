package spgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spgpetclinic.model.Pet;
import spgpetclinic.model.Visit;
import spgpetclinic.services.PetService;
import spgpetclinic.services.VisitService;

import java.util.Map;

@Controller
public class VisitIndexController {
    private final VisitService visitService;
    private final PetService petService;

    public VisitIndexController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }
    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setAllowedFields("id");

    }

    @ModelAttribute("visit")
    public Visit loadPetsWithVisits(@PathVariable("petId")Long petId, Map<String, Object> model){
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId, Map<String, Object>model){
        return "pets/createOrUpdateVisitForm";
    }
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Validated Visit visit, BindingResult result){
        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else{
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
