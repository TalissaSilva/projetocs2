package morabem.services;

import morabem.domain.AtributoImovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.Collection;

@Service
public class AtributoImovelService {

    @Autowired
    public EntityManager entityManager;

    public void salvarTudo(Collection<AtributoImovel> atributos) {
        EntityTransaction em = entityManager.getTransaction();
        em.begin();
        atributos.forEach(entityManager::persist);
        em.commit();
    }
}
