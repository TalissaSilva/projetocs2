package morabem.controllers;

import morabem.domain.Anuncio;
import morabem.domain.PessoaFisica;
import morabem.domain.Usuario;
import morabem.exceptions.UsuarioException;
import morabem.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utils.LoginData;


/**
 * @link { https://spring.io/guides/gs/handling-form-submission/ }
 */
@Controller
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @PostMapping(path = "/login")
    public String loginSubmit(@ModelAttribute LoginData data, Model model) {
        try {
            Usuario us = usuarioService.login(data.getEmail(), data.getSenha());
            return us.toString();
        } catch (UsuarioException.UsuarioNaoEncontrado ex) {
            model.addAttribute("error", "Usuário não encontrado.");
            return "login";

        }
    }

    @GetMapping(path = "/login")
    public String loginForm(Model model) {
        model.addAttribute("loginData", new LoginData());
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
