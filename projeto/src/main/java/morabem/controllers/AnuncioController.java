package morabem.controllers;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import morabem.exceptions.AnuncioException;
import morabem.exceptions.ImovelException;
import morabem.services.AnuncioService;
import morabem.services.ImovelService;
import morabem.services.UsuarioService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.servlet.http.HttpSession;
import javax.transaction.TransactionScoped;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AnuncioController {

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping(path = "/imoveis/{imovel}/anunciar")
    public String cadastroAnuncioForm(@PathVariable(value = "imovel") String codImovel, Model model) throws ImovelException.ImovelNaoExiste {
        Anuncio anuncio = new Anuncio();
        anuncio.setImovel(imovelService.obterPorCodigo(codImovel));
        model.addAttribute("anuncio", anuncio);
        model.addAttribute("tipos", Anuncio.Tipo.values());
        return "novoAnuncio";
    }

    @PostMapping(path = "/imoveis/{imovel}/anunciar")
    public String cadastroAnuncioSubmit(Model model,
                                        @PathVariable(value = "imovel") String codImovel,
                                        @ModelAttribute Anuncio anuncio, HttpSession session) throws ImovelException.ImovelNaoExiste {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }
        anuncio.setAnunciante(usuario);
        anuncio.setImovel(imovelService.obterPorCodigo(codImovel));
        anuncio.setDatadaPublicacao(new Date());
        anuncioService.salvarAnuncio(anuncio);

        return "redirect:/meus-anuncios";
    }

    @GetMapping(path = "/meus-anuncios")
    public String anunciosDoUsuario(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }

        model.addAttribute("anuncios", anuncioService.getAnunciosDoUsuario(usuario));
        return "anunciosUsuario";
    }

    @GetMapping(path = "/anuncio/{anuncio}/editar")
    public String editarAnuncio(Model model, HttpSession session, @PathVariable(value = "anuncio") String cod) throws AnuncioException.NaoEmcontrado {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }
        Anuncio anuncio = anuncioService.getById(cod);
        model.addAttribute("anuncio", anuncio);
        System.out.println(anuncio);
        model.addAttribute("tipos", Anuncio.Tipo.values());
        return "novoAnuncio";
    }

    @GetMapping(path = "/meus-anuncios/deletar")
    public String deletarAnuncio(HttpSession session, Model model, @RequestParam(name = "id") Integer id) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }

        anuncioService.deletar(id);

        return "redirect:/meus-anuncios";
    }
}
