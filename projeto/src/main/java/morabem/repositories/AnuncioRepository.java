package morabem.repositories;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    List<Anuncio> getAllByOrderByDatadaPublicacaoAsc();

    List<Anuncio> getAllByAnunciante(Usuario usuario);

    @Query("SELECT DISTINCT anu " +
            "FROM Anuncio anu INNER JOIN anu.imovel imv WHERE " +
            "anu.titulo like %:titulo% OR " +
            "anu.descricao like %:descricao%")
    Page<Anuncio> buscar(
            @Param("titulo") String titulo,
            @Param("descricao") String descricao,
            Pageable pageable
    );
}
