package morabem.services;

import morabem.domain.Imovel;
import morabem.repositories.AtributoRepository;
import morabem.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImovelService {

    @Autowired
    public ImovelRepository imovelRepository;

    @Autowired
    public AtributoRepository atributoRepository;

    @Autowired
    public AtributoImovelService atributoImovelService;

    public void salvar(Imovel imovel) {
        atributoImovelService.salvarTudo(imovel.getAtributos());
        imovelRepository.save(imovel);

    }

}
