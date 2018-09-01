package morabem.controllers;

import morabem.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping(path = "/")
    public String index(Model model) {
        model.addAttribute("anuncios", anuncioService.getDestaques());
        return "index";
    }

}
