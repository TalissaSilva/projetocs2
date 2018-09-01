package morabem.services;

import morabem.domain.Anuncio;
import morabem.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    public void salvarAnuncio(Anuncio anuncio) {
        this.anuncioRepository.saveAndFlush(anuncio);
    }

    public List<Anuncio> getDestaques() {
        return anuncioRepository.getAllByOrderByDatadaPublicacaoAsc();
    }
}
