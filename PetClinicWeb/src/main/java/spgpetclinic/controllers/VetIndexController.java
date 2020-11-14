package spgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spgpetclinic.services.VetService;

@Controller
public class VetIndexController {
    private final VetService vetService;

    public VetIndexController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"vets/index/","vets/", "vets.html"})
    public String vetIndex(Model model){
        model.addAttribute("vets", vetService.findAll());
    return "vets/index";
}
}
