package morabem.services;

import morabem.domain.PessoaFisica;
import morabem.domain.PessoaJuridica;
import morabem.domain.Usuario;
import morabem.exceptions.UsuarioException;
import morabem.repositories.EnderecoRepository;
import morabem.repositories.FotoRepository;
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

    @Autowired
    public EnderecoRepository enderecoRepository;

    @Autowired
    public FotoRepository fotoRepository;


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

    public void cadastrar(Usuario usuario) {
        if (usuario.getFotoPerfil() != null) {
            fotoRepository.saveAndFlush(usuario.getFotoPerfil());
        }
        enderecoRepository.saveAndFlush(usuario.getEndereco());

        if (usuario instanceof PessoaFisica) {
            pessoaFisicaRepository.saveAndFlush((PessoaFisica) usuario);
        }

        if (usuario instanceof PessoaJuridica) {
            pessoaJuridicaRepository.saveAndFlush((PessoaJuridica) usuario);
        }
    }

    public Usuario obterUsuarioPorEmail(String email) throws UsuarioException.UsuarioNaoEncontrado {
        Usuario usuario = this.pessoaFisicaRepository.findFirstByEmailEquals(email);
        if(usuario == null) {
            usuario = pessoaJuridicaRepository.findFirstByEmailEquals(email);
        }
        if (usuario == null) {
            throw new UsuarioException.UsuarioNaoEncontrado();
        }

        return usuario;
    }

    public boolean verificarSeOEmailEstaSendoUsado(String email) {
        try {
            obterUsuarioPorEmail(email);
            return true;
        } catch (UsuarioException.UsuarioNaoEncontrado e) {
            return false;
        }
    }

    public boolean verificarSeOUsuariojaNaoEstaCadastrado(Usuario u) {
        if (u instanceof PessoaFisica) {
            PessoaFisica p = pessoaFisicaRepository
                    .findFirstByEmailEqualsOrAndCpfEquals(u.getEmail(), ((PessoaFisica) u).getCpf());

            return p != null;
        }

        return false;
    }

}
