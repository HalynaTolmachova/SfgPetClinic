package spgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spgpetclinic.model.Owner;
import spgpetclinic.services.OwnerService;

import java.util.List;

@Controller
public class OwnerIndexController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerIndexController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"owners/index","owners"})
    public String ownersIndex(Model model){
        model.addAttribute("owners", ownerService.findAll());
    return "owners/index";
    }

    @RequestMapping("owners/find")
    public String findOwner(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("owners/{id}")
    public ModelAndView showOwner(@PathVariable("id") Long id){
            ModelAndView mv = new ModelAndView("owners/ownerDetails");
            mv.addObject(this.ownerService.findById(Long.valueOf(id)));
            return mv;
    }

    @GetMapping("owners")
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if(results.isEmpty()){
            result.rejectValue("lastName","notFound", "not found");
            return "owners/findOwners";
        }else if(results.size()==1){
            owner=results.get(0);
            return "redirect:/owners/" +owner.getId();
        }else{
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }

    }

    @GetMapping("owners/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("owners/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else{
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" +savedOwner.getId();
        }
    }
    @GetMapping("owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result,@PathVariable Long ownerId){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else{
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" +savedOwner.getId();
        }

    }
}
