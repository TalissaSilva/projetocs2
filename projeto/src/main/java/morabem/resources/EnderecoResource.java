package morabem.resources;

import morabem.domain.Endereco;
import morabem.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoResource;

    @GetMapping(path = "/api/search")
    public List<String> search(@RequestParam("campo") String campo, @RequestParam("termo") String termo) {
        switch (campo) {
            case "imovel.endereco.logradouro":
                return enderecoResource.buscarPorLogradouros(termo).stream()
                        .map(Endereco::getLogradouro)
                        .collect(Collectors.toList());
            case "imovel.endereco.bairro":
                return enderecoResource.buscarPorBairro(termo).stream()
                        .map(Endereco::getBairro)
                        .collect(Collectors.toList());
            case "imovel.endereco.cidade":
                return enderecoResource.buscarPorCidade(termo).stream()
                        .map(Endereco::getCidade)
                        .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
