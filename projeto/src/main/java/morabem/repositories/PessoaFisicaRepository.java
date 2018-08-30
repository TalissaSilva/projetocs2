package morabem.repositories;

import morabem.domain.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PessoaFisicaRepository extends UsuarioRepository<PessoaFisica> {

    PessoaFisica findFirstByEmailEqualsOrAndCpfEquals(String email, String cpf);
}
