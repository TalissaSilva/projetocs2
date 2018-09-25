package morabem.controllers;

import morabem.domain.Anuncio;
import morabem.domain.Foto;
import morabem.domain.Imovel;
import morabem.domain.Usuario;
import morabem.exceptions.ImovelException;
import morabem.services.ImovelService;
import morabem.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ImovelController {


    @Autowired
    public ImovelService imovelService;

    @Autowired
    public StorageService storageService;


    @GetMapping(path = "/cadastro/imovel")
    public String getPaginaDeCadastro(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/";
        }
        model.addAttribute("imovel", new Imovel());
        model.addAttribute("tipos", Imovel.Tipo.values());
        return "cadastroImovel";
    }

    @RequestMapping(path = "/meus-imoveis")
    public String imoveisDoUsuario(Model model, HttpSession session, @PageableDefault(value=1, page=0) Pageable pageable) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }
        Page<Imovel> imoveis = imovelService.obterImoveisDoDono(usuario.getId(), pageable);
        model.addAttribute("imoveis", imoveis);


        if (imoveis.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, imoveis.getTotalPages() - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        return "imoveisUsuario";
    }

    @PostMapping(path = "/cadastro/imovel")
    public String submitPaginaDeCadastro(HttpSession session, Model model,
                                         @ModelAttribute Imovel imovel,
                                         @RequestParam(name = "foto64[]", required = false) String[] fotos) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }
        List<String> erros = new LinkedList<>();
        if (fotos == null || fotos.length == 0) {
            erros.add("Cadastre pelo menos uma foto.");
        }
        if (imovel.getCaracteristicas().size() == 0) {
            erros.add("Cadastre as qualidades deste imovel.");
        }

        if(erros.size() > 0) {
            model.addAttribute("erros", erros);
            return "cadastroImovel";
        }

        List<Foto> fotosImovel = Arrays.stream(fotos)
                .map(Base64.getDecoder()::decode)
                .map(String::new)
                .map(storageService::saveBase64Image)
                .map(url -> new Foto(null, url))
                .collect(Collectors.toList());

        imovel.setFotos(fotosImovel);

        imovel.setDono(usuario);
        imovelService.salvar(imovel);
        return "redirect:/meus-imoveis";
    }

    @GetMapping(path = "/meus-imoveis/deletar")
    public String deletarImovel(HttpSession session, Model model,
                                @RequestParam(name = "id") Long id)
            throws ImovelException.ImovelNaoExiste {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) {
            model.addAttribute("error", "Sua sessão expirou.");
            return "redirect:/login";
        }

        Imovel imovel = imovelService.obterPorCodigo(String.valueOf(id));
        imovel.getFotos().stream().map(Foto::getUrl).forEach(storageService::delete);
        imovelService.deletarImovel(imovel);

        return "redirect:/meus-imoveis";
    }
}
