package morabem.repositories;

import morabem.domain.PessoaFisica;
import morabem.domain.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PessoaJuridicaRepository extends UsuarioRepository<PessoaJuridica> {
}
