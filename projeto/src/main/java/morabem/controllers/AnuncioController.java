package morabem.controllers;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import morabem.exceptions.ImovelException;
import morabem.services.AnuncioService;
import morabem.services.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
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

        return "index";
    }
}
