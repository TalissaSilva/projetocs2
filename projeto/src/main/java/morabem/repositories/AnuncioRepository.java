package morabem.repositories;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    List<Anuncio> getAllByOrderByDatadaPublicacaoAsc();

    List<Anuncio> getAllByAnunciante(Usuario usuario);
}
