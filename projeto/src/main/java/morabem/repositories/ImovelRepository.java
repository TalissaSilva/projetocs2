package morabem.repositories;

import morabem.domain.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
}
