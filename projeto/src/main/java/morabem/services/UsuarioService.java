package morabem.services;

import morabem.domain.Anuncio;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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

    public void cadastrarAtualizar(Usuario usuario, Usuario usuarioLogado, String foto) {


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

        if (u instanceof PessoaJuridica) {
            PessoaJuridica p = pessoaJuridicaRepository.
                    findFirstByEmailOrCnpjOrCreciEquals(u.getEmail(),
                            ((PessoaJuridica) u).getCnpj(),
                            ((PessoaJuridica) u).getCreci());
            return p != null;
        }

        return false;
    }

    public void deletar(Usuario usuario) {
        if (usuario instanceof PessoaFisica) {
            pessoaFisicaRepository.delete((PessoaFisica) usuario);
            pessoaFisicaRepository.flush();
        }

        if (usuario instanceof PessoaJuridica) {
            pessoaJuridicaRepository.delete((PessoaJuridica) usuario);
            pessoaJuridicaRepository.flush();
        }
    }
    

    public double precoAnunciosVenda(Usuario usuario){
	double soma;
	where(usuario.anuncios.anuncio.getTipo=='venda'){
		soma += anuncio.getValor();
	}
	return soma;
    }

    public double precoAnunciosAluguel(Usuario usuario){
	double soma;
	where(usuario.anuncios.anuncio.getTipo=='aluguel'){
		soma += anuncio.getValor();
	}
	return soma;
    }
}
