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

    Page<Anuncio> getAllByOrderByDatadaPublicacaoAsc(Pageable pageable);

    Page<Anuncio> getAllByAnunciante(Usuario usuario, Pageable p);

    List<Anuncio> getAllByTipoEqualsAndAnuncianteEquals(Anuncio.Tipo tipo, Usuario usuario);

    @Query("SELECT anu FROM Anuncio anu INNER JOIN anu.imovel imv WHERE " +
            "(:titulo is null OR :titulo='' OR lower(anu.titulo) LIKE %:titulo%) AND " +
            "(:descricao is null OR :descricao='' OR lower(anu.descricao) LIKE %:descricao%) AND " +
            "(:logradouro is null OR :logradouro='' OR lower(imv.endereco.logradouro) LIKE %:logradouro%) AND " +
            "(:bairro is null OR :bairro='' OR lower(imv.endereco.bairro) LIKE %:bairro%) AND " +
            "(:cidade is null OR :cidade='' OR lower(imv.endereco.cidade) LIKE %:cidade%)")
    Page<Anuncio> buscar(
            @Param("titulo") String titulo,
            @Param("descricao") String descricao,
            @Param("logradouro") String logradouro,
            @Param("bairro") String bairro,
            @Param("cidade") String cidade,
            Pageable pageable
    );
}
