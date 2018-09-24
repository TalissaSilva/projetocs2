package morabem.services;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import morabem.exceptions.AnuncioException;
import morabem.repositories.AnuncioRepository;
import morabem.utils.BuscaData;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private EntityManager entityManager;

    public void salvarAnuncio(Anuncio anuncio) {
        System.out.println(anuncio);
        this.anuncioRepository.saveAndFlush(anuncio);
    }

    public Page<Anuncio> getDestaques(Pageable pageable) {
        return anuncioRepository.getAllByOrderByDatadaPublicacaoAsc(pageable);
    }

    public List<Anuncio> getAnunciosDoUsuario(Usuario u) { return anuncioRepository.getAllByAnunciante(u); }

    public Anuncio getById(String cod) throws AnuncioException.NaoEmcontrado {
        return anuncioRepository.findById(Integer.valueOf(cod)).orElseThrow(AnuncioException.NaoEmcontrado::new);
    }

    public void deletar(Integer id) {
        anuncioRepository.deleteById(id);
    }

    public void deletar(Anuncio anuncio) {
        anuncioRepository.delete(anuncio);
    }

    public Page<Anuncio> buscarAnuncio(BuscaData data, Pageable pageable) {
        return anuncioRepository.buscar(
                data.getTitulo() != null ? data.getTitulo().toLowerCase().trim() : null,
                data.getDescricao() != null ? data.getDescricao().toLowerCase().trim() : null,
                data.getImovel().getEndereco().getLogradouro() != null ? data.getImovel().getEndereco().getLogradouro().toLowerCase().trim() : null,
                data.getImovel().getEndereco().getBairro() != null ? data.getImovel().getEndereco().getBairro().toLowerCase().trim() : null,
                data.getImovel().getEndereco().getCidade() != null ? data.getImovel().getEndereco().getCidade().toLowerCase().trim() : null,
                pageable
        );
    }

}
