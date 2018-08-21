package morabem.controllers;

import morabem.domain.Anuncio;
import morabem.domain.PessoaFisica;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @link { https://spring.io/guides/gs/handling-form-submission/ }
 */
@Controller
public class UsuarioController {

    @PostMapping(path = "/login")
    public @ResponseBody String loginSubmit(@RequestBody String body) {
        return body;
    }

    @GetMapping(path = "/login")
    public String loginForm(Model model) {
        model.addAttribute("loginData", new Object() { String email; String senha;});
        return "login";
    }

    @GetMapping(path = "/cadastro/pessoa-fisica")
    public String cadastroPessoaFisicaForm(Model model) {
        model.addAttribute("pessoaFisica", new PessoaFisica());
        return "cadastroFisica";
    }

    @PostMapping(path = "/cadastro/pessoa-fisica")
    public String cadastroPessoaFisicaSubmit(Model model) {
        model.addAttribute("pessoaFisica", new PessoaFisica());
        return "cadastrar";
    }
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
