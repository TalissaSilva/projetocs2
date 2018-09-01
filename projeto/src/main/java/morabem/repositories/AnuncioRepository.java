package morabem.repositories;

import morabem.domain.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    List<Anuncio> getAllByOrderByDatadaPublicacaoAsc();
}
