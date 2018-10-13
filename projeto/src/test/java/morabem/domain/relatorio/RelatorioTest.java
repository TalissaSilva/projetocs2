package morabem.domain.relatorio;

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
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
    public void exportar() throws UsuarioException.UsuarioNaoEncontrado {
        Usuario usuario = usuarioService.obterUsuarioPorEmail("matheuscg10@live.com");

        byte[] bytes = usuario.getRelatorioDeVendas()
                .exportar()
                .para(ExportarStrategy.Formato.CSV);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split("\r\n");

        assertThat(lines[0].matches(".*[usuario:matheus ale da silva].*[Tipo dos anuncios:ALUGAR].*"), CoreMatchers.is(true));
        assertThat(lines[1].matches(".*[Descrição].*[Tipo].*[Tipo do Imovel].*[Caracteristicas].*[Titulo].*[Valor].*[Data de Publicação].*[área Total Contruida].*[Endreço].*"), CoreMatchers.is(true));
        assertThat(lines[2].matches(".*[Lorem ipsum dolor sit amet, co].*[VENDER].*[CASA].*[\\[Banheiro, 2, Sacada, 2, Cozinha, 2\\]].*[Casa].*[1500000\\\\.0].*[2018\\-09\\-08].*[30.0].*[Av. Coronel Antonino, 2091 \\- coronel antonino, Campo Grande \\- Campo Grande, ms].*"), CoreMatchers.is(true));
        assertThat(lines[4].matches(".*[Total:1500002\\.0].*"), CoreMatchers.is(true));
    }

    @Test
    public void ewew() {
    }
}