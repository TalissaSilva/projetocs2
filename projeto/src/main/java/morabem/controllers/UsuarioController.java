package morabem.controllers;

import morabem.domain.*;
import morabem.exceptions.UsuarioException;
import morabem.services.AnuncioService;
import morabem.services.ImovelService;
import morabem.services.UsuarioService;
import morabem.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import morabem.utils.LoginData;
import javax.servlet.http.HttpSession;


/**
 * @link { https://spring.io/guides/gs/handling-form-submission/ }
 */
@Controller
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public StorageService storageService;

    @Autowired
    public ImovelService imovelService;

    @Autowired
    public AnuncioService anuncioService;

    @PostMapping(path = "/login")
    public String loginSubmit(@ModelAttribute LoginData data, Model model, HttpSession session) {
        try {
            Usuario us = usuarioService.login(data.getEmail(), data.getSenha());
            session.setAttribute("usuarioLogado", us);
            return "redirect:/";
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

    @GetMapping(path = "/cadastro/pessoa-juridica")
    public String cadastroPessoaJuridicaForm(Model model) {
        model.addAttribute("pessoaJuridica", new PessoaJuridica());
        return "cadastroJuridica";
    }

    @PostMapping(path = "/cadastro/pessoa-juridica")
    public String cadastroPessoaJuridicaSubmit(@ModelAttribute PessoaJuridica pessoaJuridica, HttpSession session,
                                               Model model, @RequestPart(required = false) MultipartFile foto) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if ( usuario == null &&
                usuarioService.verificarSeOUsuariojaNaoEstaCadastrado(pessoaJuridica)) {
            model.addAttribute("error", "O E-mail ou CNPJ ou CRECI já está em uso.");
            model.addAttribute("pessoaJuridica", pessoaJuridica);
            return "cadastroJuridica";
        }
        return this.cadastroSubmit(pessoaJuridica, usuario, foto, session);
    }

    @PostMapping(path = "/cadastro/pessoa-fisica")
    public String cadastroPessoaFisicaSubmit(@ModelAttribute PessoaFisica pessoaFisica, HttpSession session,
                                             Model model, @RequestPart(required = false) MultipartFile foto) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if ( usuario == null &&
                usuarioService.verificarSeOUsuariojaNaoEstaCadastrado(pessoaFisica)) {
            model.addAttribute("error", "O E-mail ou CPF já está em uso.");
            model.addAttribute("pessoaFisica", pessoaFisica);
            return "cadastroFisica";
        }
        return this.cadastroSubmit(pessoaFisica, usuario, foto, session);
    }

    private String cadastroSubmit(Usuario novo, Usuario logado, MultipartFile foto, HttpSession session) {
        if (logado != null && !foto.isEmpty() && logado.getFotoPerfil() != null) {
            storageService.delete(logado.getFotoPerfil().getUrl());
        }

        if ( foto != null && !foto.isEmpty()) {
            String url = storageService.store(foto);
            novo.setFotoPerfil(new Foto(null, url));
        }

        usuarioService.cadastrar(novo);

        if (logado != null) {
            session.setAttribute("usuarioLogado", novo);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usuarioLogado");
        return "redirect:/";
    }

    @GetMapping(path = "/meu-perfil")
    public String perfilDoUsuario(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }

        if (usuario instanceof PessoaJuridica) {
            model.addAttribute("pessoaJuridica", usuario);
            return "cadastroJuridica";
        }

        if (usuario instanceof PessoaFisica) {
            model.addAttribute("pessoaFisica", usuario);
            return "cadastroFisica";
        }

        return "redirect:/";
    }

    @GetMapping(path = "/usuario/deletar-conta")
    public String deletarContaDoUsuario(HttpSession session, Model model) throws UsuarioException.UsuarioNaoEncontrado {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }

        usuario = usuarioService.obterUsuarioPorEmail(usuario.getEmail());

        usuario.getAnuncios().forEach(anuncioService::deletar);
        usuario.getImoveis().stream()
                .flatMap(l -> l.getFotos().stream())
                .map(Foto::getUrl)
                .forEach(storageService::delete);

        usuario.getImoveis()
                .forEach(imovelService::deletarImovel);

        if (usuario.getFotoPerfil() != null) {
            storageService.delete(usuario.getFotoPerfil().getUrl());
        }

        usuarioService.deletar(usuario);

        session.removeAttribute("usuarioLogado");
        session.invalidate();



        return "redirect:/";
    }


}
