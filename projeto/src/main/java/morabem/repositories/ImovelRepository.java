package morabem.repositories;

import morabem.domain.Imovel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    @EntityGraph(value = "Usuario.imoveis", type = EntityGraph.EntityGraphType.LOAD)
    Set<Imovel> findAllByDonoId(Long donoId);

}
