package morabem.services;

import morabem.domain.Imovel;
import morabem.exceptions.ImovelException;
import morabem.repositories.EnderecoRepository;
import morabem.repositories.FotoRepository;
import morabem.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;


@Service
public class ImovelService {

    @Autowired
    public ImovelRepository imovelRepository;


    @Autowired
    public EnderecoRepository enderecoRepository;

    @Autowired
    public FotoRepository fotoRepository;

    public void salvar(Imovel imovel) {
        enderecoRepository.saveAndFlush(imovel.getEndereco());
        imovel.getFotos().forEach(fotoRepository::saveAndFlush);
        imovelRepository.saveAndFlush(imovel);
    }

    public Page<Imovel> obterImoveisDoDono(Long id, Pageable pageable) {
        return imovelRepository.findAllByDonoId(id, pageable);
    }

    public void deletarImovel(Imovel imovel) {
        imovel.getCaracteristicas().clear();
        imovelRepository.saveAndFlush(imovel);
        imovelRepository.delete(imovel);
        imovelRepository.flush();
    }

    public Imovel obterPorCodigo(String codImovel) throws ImovelException.ImovelNaoExiste {
        return imovelRepository
                .findById(Long.valueOf(codImovel))
                .orElseThrow(ImovelException.ImovelNaoExiste::new);
    }
}
