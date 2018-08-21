package morabem.repositories;

import morabem.domain.Atributo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtributoRepository extends JpaRepository<Atributo, Long> {
}
