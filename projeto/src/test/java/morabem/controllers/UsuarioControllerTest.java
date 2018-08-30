package morabem.controllers;

import builders.UsuarioBuilder;
import morabem.exceptions.UsuarioException;
import morabem.services.UsuarioService;
import morabem.storage.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @see { https://spring.io/guides/gs/testing-web/ }
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private StorageService storageService;

    @Test
    public void submitCadastroDePessoaFisica_sucesso() throws Exception {
        Mockito.doReturn(false).when(usuarioService).verificarSeOEmailEstaSendoUsado(Mockito.any());
        Mockito.doNothing().when(usuarioService).cadastrar(Mockito.any());

        this.mockMvc.perform(
                builders.RequestBuilder.postPara("/cadastro/pessoa-fisica")
                        .comOsParamentros(UsuarioBuilder.PessoaFisica.obterUm().comoParametros())
                        .agora().contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/login"));

        Mockito.verify(usuarioService, Mockito.times(1)).cadastrar(Mockito.any());
    }

    @Test
    public void submitCadastroDePessoaFisica_erro() throws Exception {
        Mockito.doReturn(true).when(usuarioService).verificarSeOUsuariojaNaoEstaCadastrado(Mockito.any());
        Mockito.doNothing().when(usuarioService).cadastrar(Mockito.any());

        this.mockMvc.perform(
                builders.RequestBuilder.postPara("/cadastro/pessoa-fisica")
                        .comOsParamentros(UsuarioBuilder.PessoaFisica.obterUm().comoParametros())
                        .agora().contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(model().attribute("error", is(equalTo("O E-mail ou CPF já está em uso."))));

        Mockito.verify(usuarioService, Mockito.times(0)).cadastrar(Mockito.any());
    }

    @Test
    public void loginSubmit_sucesso() throws Exception {
        Mockito.doReturn(UsuarioBuilder.PessoaFisica.obterUm().agora())
                .when(usuarioService).login("foo@email", "foo bar");

        this.mockMvc.perform(
                builders.RequestBuilder.postPara("/login").agora()
                        .param("email", "foo@email")
                        .param("senha", "foo bar"))
                .andExpect(redirectedUrl("/"));

        Mockito.verify(usuarioService, Mockito.times(1)).login("foo@email", "foo bar");
    }

    @Test
    public void loginSubmit_erro() throws Exception {
        Mockito.doThrow(UsuarioException.UsuarioNaoEncontrado.class)
                .when(usuarioService).login("foo@email", "foo bar");

        this.mockMvc.perform(
                builders.RequestBuilder.postPara("/login").agora()
                        .param("email","foo@email")
                        .param("senha", "foo bar"))
                .andExpect(model().attribute("error", is(equalTo("Usuário não encontrado."))));

        Mockito.verify(usuarioService, Mockito.times(1)).login("foo@email", "foo bar");
    }
}