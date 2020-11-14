package spgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spgpetclinic.services.OwnerService;

@Controller
public class OwnerIndexController {
    private final OwnerService ownerService;

    public OwnerIndexController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"owners/index","owners"})
    public String ownersIndex(Model model){
        model.addAttribute("owners", ownerService.findAll());
    return"owners/index";
    }

    @RequestMapping("owners/find")
    public String findOwner(){
        return "notImplemented";
    }
}
