package morabem.services;

import morabem.domain.Imovel;
import morabem.repositories.EnderecoRepository;
import morabem.repositories.FotoRepository;
import morabem.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Set<Imovel> obterImoveisDoDono(Long id) {
        return imovelRepository.findAllByDonoId(id);
    }

    public void deletarImovelComOId(Long id) {
        Imovel i = imovelRepository.getOne(id);
        i.getCaracteristicas().clear();
        imovelRepository.saveAndFlush(i);
        imovelRepository.delete(i);
        imovelRepository.flush();
    }
}
