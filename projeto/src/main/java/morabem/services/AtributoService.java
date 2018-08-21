package morabem.services;

import morabem.domain.Atributo;
import morabem.repositories.AtributoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AtributoService {

    @Autowired
    public AtributoRepository atributoRepository;

    public List<Atributo> obterAtributos(Integer ...ids) {
        return Collections.emptyList();
    }


}
