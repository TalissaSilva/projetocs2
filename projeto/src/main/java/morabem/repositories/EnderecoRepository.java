package morabem.repositories;

import morabem.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

    @Query("SELECT obj FROM Endereco obj WHERE lower(obj.logradouro) like %:data% ")
    List<Endereco> buscarProLogradouro(@Param("data") String data);

    @Query("SELECT obj FROM Endereco obj WHERE lower(obj.bairro) like %:data% ")
    List<Endereco> buscarProBairro(@Param("data") String data);

    @Query("SELECT obj FROM Endereco obj WHERE lower(obj.cidade) like %:data% ")
    List<Endereco> buscarProCidade(@Param("data") String data);
}
