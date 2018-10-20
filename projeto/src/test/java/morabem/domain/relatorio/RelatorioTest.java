package morabem.domain.relatorio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import morabem.exceptions.UsuarioException;
import morabem.services.AnuncioService;
import morabem.services.UsuarioService;
import org.hamcrest.CoreMatchers;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RelatorioTest {

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public EntityManager entityManager;

    @Test
    public void exportar_csv() throws UsuarioException.UsuarioNaoEncontrado {
        Usuario usuario = usuarioService.obterUsuarioPorEmail("matheuscg10@live.com");

        byte[] bytes = usuario.getRelatorioDeVendas()
                .exportar()
                .para(ExportarStrategy.Formato.CSV);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split("\r\n");

        assertThat(lines[0].matches(".*[usuario:matheus ale da silva].*[Tipo dos anuncios:ALUGAR].*"), is(true));
        assertThat(lines[1].matches(".*[Descrição].*[Tipo].*[Tipo do Imovel].*[Caracteristicas].*[Titulo].*[Valor].*[Data de Publicação].*[área Total Contruida].*[Endreço].*"), is(true));
        assertThat(lines[2].matches(".*[Lorem ipsum dolor sit amet, co].*[VENDER].*[CASA].*[\\[Banheiro, 2, Sacada, 2, Cozinha, 2\\]].*[Casa].*[1500000\\\\.0].*[2018\\-09\\-08].*[30.0].*[Av. Coronel Antonino, 2091 \\- coronel antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
        assertThat(lines[4].matches(".*[Total:1500002\\.0].*"), is(true));
    }


    @Test
    public void exportar_json() throws UsuarioException.UsuarioNaoEncontrado {
        Usuario usuario = usuarioService.obterUsuarioPorEmail("matheuscg10@live.com");

        byte[] bytes = usuario.getRelatorioDeVendas()
                .exportar()
                .para(ExportarStrategy.Formato.JSON);

        assertThat(new String(bytes),
                is(equalTo(
                        "{\"cabecalho\":{\"usuario\":\"matheus ale da silva\",\"Tipo dos anuncios\":\"ALUGAR\"},\"data\":[{\"Descrição\":\"descrição 2\",\"Tipo\":\"VENDER\",\"Tipo do Imovel\":\"APARTAMENTO\",\"Caracteristicas\":[\"Quarto, 2\",\"Sacada, 1\",\"Banheiro, 2\"],\"Titulo\":\"anuncio 2\",\"Valor\":2.0,\"Endereco\":\"R. Santo Ângelo, 51 - Cel. Antonino, Campo Grande - Campo Grande, ms\",\"área Total Contruida\":20.0,\"Data de Publicação\":\"set 23, 2018\"},{\"Descrição\":\"Lorem ipsum dolor sit amet, co\",\"Tipo\":\"VENDER\",\"Tipo do Imovel\":\"CASA\",\"Caracteristicas\":[\"Banheiro, 2\",\"Sacada, 2\",\"Cozinha, 2\"],\"Titulo\":\"Casa\",\"Valor\":1500000.0,\"Endereco\":\"Av. Coronel Antonino, 2091 - coronel antonino, Campo Grande - Campo Grande, ms\",\"área Total Contruida\":30.0,\"Data de Publicação\":\"set 8, 2018\"}],\"rodape\":{\"Total\":\"1500002.0\"}}"
                )));
    }

}