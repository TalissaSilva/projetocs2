package morabem.controllers;

import morabem.domain.Anuncio;
import morabem.services.AnuncioService;
import morabem.utils.BuscaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ApplicationController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping(path = "/")
    public String index(Model model, @PageableDefault(value=1, page=0) Pageable pageable) {
        Page<Anuncio> anuncios = anuncioService.getDestaques(pageable);

        model.addAttribute("anuncios", anuncios);

        if (anuncios.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, anuncios.getTotalPages() - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("busca", new BuscaData());
        return "index";
    }

}
