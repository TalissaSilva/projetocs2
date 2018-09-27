package morabem.resources;

import builders.EnderecoBuilder;
import builders.RequestBuilder;
import morabem.services.EnderecoService;
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
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    @Test
    public void search_semCampo() throws Exception {
        mockMvc.perform(RequestBuilder.getPara("/api/search").comOParamentroGET("termo", "coronel").agora())
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("'campo' is not present")));
    }

    @Test
    public void search_semTermo() throws Exception {
        mockMvc.perform(RequestBuilder.getPara("/api/search").comOParamentroGET("campo", "coronel").agora())
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("'termo' is not present")));
    }

    @Test
    public void search_logradouro() throws Exception {
        Mockito.doReturn(Arrays.asList(
                EnderecoBuilder.obterUm().agora(),
                EnderecoBuilder.obterUm().agora()
        )).when(enderecoService).buscarPorLogradouros("coronel");

        mockMvc.perform(RequestBuilder.getPara("/api/search")
                    .comOParamentroGET("campo", "imovel.endereco.logradouro")
                    .comOParamentroGET("termo", "coronel")
                    .agora())
                .andExpect(status().is(200))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Mockito.verify(enderecoService, Mockito.times(1)).buscarPorLogradouros("coronel");
    }

    @Test
    public void search_bairro() throws Exception {
        Mockito.doReturn(Arrays.asList(
                EnderecoBuilder.obterUm().agora(),
                EnderecoBuilder.obterUm().agora()
        )).when(enderecoService).buscarPorBairro("coronel");

        mockMvc.perform(RequestBuilder.getPara("/api/search")
                .comOParamentroGET("campo", "imovel.endereco.bairro")
                .comOParamentroGET("termo", "coronel")
                .agora())
                .andExpect(status().is(200))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Mockito.verify(enderecoService, Mockito.times(1)).buscarPorBairro("coronel");
    }

    @Test
    public void search_cidade() throws Exception {
        Mockito.doReturn(Arrays.asList(
                EnderecoBuilder.obterUm().agora(),
                EnderecoBuilder.obterUm().agora()
        )).when(enderecoService).buscarPorCidade("campo");

        mockMvc.perform(RequestBuilder.getPara("/api/search")
                .comOParamentroGET("campo", "imovel.endereco.cidade")
                .comOParamentroGET("termo", "campo")
                .agora())
                .andExpect(status().is(200))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Mockito.verify(enderecoService, Mockito.times(1)).buscarPorCidade("campo");
    }


}