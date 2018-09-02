package morabem.services;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import morabem.exceptions.AnuncioException;
import morabem.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    public void salvarAnuncio(Anuncio anuncio) {
        System.out.println(anuncio);
        this.anuncioRepository.saveAndFlush(anuncio);
    }

    public List<Anuncio> getDestaques() {
        return anuncioRepository.getAllByOrderByDatadaPublicacaoAsc();
    }

    public List<Anuncio> getAnunciosDoUsuario(Usuario u) { return anuncioRepository.getAllByAnunciante(u); }

    public Anuncio getById(String cod) throws AnuncioException.NaoEmcontrado {
        return anuncioRepository.findById(Integer.valueOf(cod)).orElseThrow(AnuncioException.NaoEmcontrado::new);
    }

    public void deletar(Integer id) {
        anuncioRepository.deleteById(id);
    }
}
