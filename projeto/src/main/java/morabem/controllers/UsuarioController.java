package morabem.controllers;

import morabem.domain.PessoaFisica;
import morabem.domain.Usuario;
import morabem.exceptions.UsuarioException;
import morabem.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utils.LoginData;
import javax.servlet.http.HttpSession;


/**
 * @link { https://spring.io/guides/gs/handling-form-submission/ }
 */
@Controller
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @PostMapping(path = "/login")
    public String loginSubmit(@ModelAttribute LoginData data, Model model, HttpSession session) {
        try {
            Usuario us = usuarioService.login(data.getEmail(), data.getSenha());
            session.setAttribute("usuarioLogado", us);
            return "index";
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
    public @ResponseBody  String cadastroPessoaFisicaSubmit(@ModelAttribute PessoaFisica model) {
        return model.toString();
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usuarioLogado");
        return "index";
    }
}
