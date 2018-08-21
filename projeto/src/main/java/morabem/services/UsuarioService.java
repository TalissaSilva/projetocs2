package morabem.services;

import morabem.domain.Usuario;
import morabem.exceptions.UsuarioException;
import morabem.repositories.PessoaFisicaRepository;
import morabem.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    public PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    public PessoaFisicaRepository pessoaFisicaRepository;

    public Usuario login(String email, String senha) throws UsuarioException.UsuarioNaoEncontrado {

        Usuario usuario = pessoaFisicaRepository.findFirstByEmailEqualsAndSenhaEquals(email, senha);

        if(usuario == null) {
            usuario = pessoaJuridicaRepository.findFirstByEmailEqualsAndSenhaEquals(email, senha);
        }

        if (usuario == null) {
            throw new UsuarioException.UsuarioNaoEncontrado();
        }

        return usuario;
    }

}
