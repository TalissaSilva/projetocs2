package morabem.services;

import builders.UsuarioBuilder;
import morabem.domain.PessoaFisica;
import morabem.domain.PessoaJuridica;
import morabem.domain.Usuario;
import morabem.exceptions.UsuarioException;
import morabem.repositories.PessoaFisicaRepository;
import morabem.repositories.PessoaJuridicaRepository;
import morabem.repositories.UsuarioRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    public UsuarioService usuarioService;

    @MockBean
    public PessoaFisicaRepository pessoaFisicaRepository;

    @MockBean
    public PessoaJuridicaRepository pessoaJuridicaRepository;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void loginComSucessoDePessoaFisica() throws UsuarioException.UsuarioNaoEncontrado {
        PessoaFisica usuario = UsuarioBuilder.PessoaFisica.obterUm()
                .comASenha("123456").comOEmail("user@email").agora();
        Mockito.doReturn(usuario).when(pessoaFisicaRepository)
                .findFirstByEmailEqualsAndSenhaEquals("user@email", "123456");

        PessoaFisica us = (PessoaFisica) usuarioService.login("user@email", "123456");

        assertThat(us.getEmail(), is(equalTo("user@email")));
        assertThat(us.getSenha(), is(equalTo("123456")));
    }

    @Test
    public void loginComSucessoDePessoaJuridica() throws UsuarioException.UsuarioNaoEncontrado {
        PessoaJuridica usuario = UsuarioBuilder.PessoaJuridica.obterUm()
                .comASenha("123456").comOEmail("user@email").agora();
        Mockito.doReturn(null).when(pessoaFisicaRepository)
                .findFirstByEmailEqualsAndSenhaEquals("user@email", "123456");
        Mockito.doReturn(usuario).when(pessoaJuridicaRepository)
                .findFirstByEmailEqualsAndSenhaEquals("user@email", "123456");

        PessoaJuridica us = (PessoaJuridica) usuarioService.login("user@email", "123456");

        assertThat(us.getEmail(), is(equalTo("user@email")));
        assertThat(us.getSenha(), is(equalTo("123456")));
    }

    @Test
    public void falhaDeAutenticacaoAoTentarFazeroLogin() throws UsuarioException.UsuarioNaoEncontrado {
        Mockito.doReturn(null).when(pessoaFisicaRepository)
                .findFirstByEmailEqualsAndSenhaEquals("user@email", "123456");
        Mockito.doReturn(null).when(pessoaJuridicaRepository)
                .findFirstByEmailEqualsAndSenhaEquals("user@email", "123456");

        expected.expect(UsuarioException.UsuarioNaoEncontrado.class);

        usuarioService.login("user@email", "123456");
    }
}