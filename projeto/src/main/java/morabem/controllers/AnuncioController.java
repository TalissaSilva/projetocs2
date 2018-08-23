package morabem.controllers;

import morabem.domain.Anuncio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnuncioController {

    @GetMapping(path = "/cadastro/anuncio")
    public String cadastroAnuncioForm(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        return "cadastroAnuncio";
    }

    @PostMapping(path = "/cadastro/anuncio")
    public String cadastroAnuncioSubmit(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        return "cadastrar";
    }
}
