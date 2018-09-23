package morabem.services;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import morabem.exceptions.AnuncioException;
import morabem.repositories.AnuncioRepository;
import morabem.utils.BuscaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    public void deletar(Anuncio anuncio) {
        anuncioRepository.delete(anuncio);
    }

    public Page<Anuncio> buscarAnuncio(BuscaData data, Pageable pageable) {
        return anuncioRepository.buscar(
                data.getTitulo(),
                data.getDescricao(),
                data.getImovel().getEndereco().getLogradouro(),
                data.getImovel().getEndereco().getBairro(),
                data.getImovel().getEndereco().getCidade(),
                pageable
        );
    }

    public Double getMaiorPreco() {
        return anuncioRepository.findTopByOrderByValorDesc().getValor();
    }
}
