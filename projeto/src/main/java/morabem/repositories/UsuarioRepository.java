package morabem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @link { https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation }
 * @link { http://blog.netgloo.com/2014/12/18/handling-entities-inheritance-with-spring-data-jpa/ }
 * @link { https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/ }
 */
@NoRepositoryBean
public interface UsuarioRepository<T> extends JpaRepository<T, Long>  {

    T findFirstByEmailEqualsAndSenhaEquals(String email, String senha);

    T findFirstByEmailEquals(String email);
}
