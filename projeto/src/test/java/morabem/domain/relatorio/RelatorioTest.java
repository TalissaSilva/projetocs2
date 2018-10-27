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
import org.junit.Before;
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
import java.util.*;

import static morabem.domain.relatorio.Exportar.Formato.*;
import static morabem.domain.relatorio.Exportar.Componente.*;
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

    private Usuario usuario;
    private List<Exportar.Componente> componentes;

    @Before
    public void setup() throws UsuarioException.UsuarioNaoEncontrado {
        usuario = usuarioService.obterUsuarioPorEmail("matheuscg10@live.com");
        componentes = new LinkedList<>();
    }

    @Test
    public void exportar_csv() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);
        componentes.add(DADOS);
        componentes.add(RODAPE);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(CSV, componentes);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));

        assertThat(lines[0].matches(".*[usuario:matheus ale da silva].*[Tipo dos anuncios:ALUGAR].*"), is(true));
        assertThat(lines[1].matches(".*[Descrição].*[Tipo].*[Tipo do Imovel].*[Caracteristicas].*[Titulo].*[Valor].*[Data de Publicação].*[área Total Contruida].*[Endreço].*"), is(true));
        assertThat(lines[2].matches(".*[Lorem ipsum dolor sit amet, co].*[VENDER].*[CASA].*[\\[Banheiro, 2, Sacada, 2, Cozinha, 2\\]].*[Casa].*[1500000\\\\.0].*[2018\\-09\\-08].*[30.0].*[Av. Coronel Antonino, 2091 \\- coronel antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
        assertThat(lines[3].matches(".*[descrição 2].*[VENDER].*[APARTAMENTO].*[\\[Quarto, 2, Sacada, 1, Banheiro, 2\\]].*[anuncio 2].*[2\\\\.0].*[2018\\-09\\-23].*[20.0].*[R. Santo Ângelo, 51 \\- Cel. Antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
        assertThat(lines[4].matches(".*[Total:1500002\\.0].*"), is(true));
    }

    @Test
    public void exportar_csv_cabecalho() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(CSV, componentes);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));
        assertThat(lines[0].matches(".*[usuario:matheus ale da silva].*[Tipo dos anuncios:ALUGAR].*"), is(true));
    }

    public void exportar_dados() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(DADOS);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(CSV, componentes);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));

        assertThat(lines[0].matches(".*[Descrição].*[Tipo].*[Tipo do Imovel].*[Caracteristicas].*[Titulo].*[Valor].*[Data de Publicação].*[área Total Contruida].*[Endreço].*"), is(true));
        assertThat(lines[1].matches(".*[Lorem ipsum dolor sit amet, co].*[VENDER].*[CASA].*[\\[Banheiro, 2, Sacada, 2, Cozinha, 2\\]].*[Casa].*[1500000\\\\.0].*[2018\\-09\\-08].*[30.0].*[Av. Coronel Antonino, 2091 \\- coronel antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
        assertThat(lines[2].matches(".*[descrição 2].*[VENDER].*[APARTAMENTO].*[\\[Quarto, 2, Sacada, 1, Banheiro, 2\\]].*[anuncio 2].*[2\\\\.0].*[2018\\-09\\-23].*[20.0].*[R. Santo Ângelo, 51 \\- Cel. Antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
    }

    public void exportar_rodape() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(RODAPE);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(CSV, componentes);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));
        assertThat(lines[0].matches(".*[Total:1500002\\.0].*"), is(true));
    }

    @Test
    public void exportar_csv_cabecalho_dados() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);
        componentes.add(DADOS);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(CSV, componentes);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));

        assertThat(lines[0].matches(".*[usuario:matheus ale da silva].*[Tipo dos anuncios:ALUGAR].*"), is(true));
        assertThat(lines[1].matches(".*[Descrição].*[Tipo].*[Tipo do Imovel].*[Caracteristicas].*[Titulo].*[Valor].*[Data de Publicação].*[área Total Contruida].*[Endreço].*"), is(true));
        assertThat(lines[2].matches(".*[Lorem ipsum dolor sit amet, co].*[VENDER].*[CASA].*[\\[Banheiro, 2, Sacada, 2, Cozinha, 2\\]].*[Casa].*[1500000\\\\.0].*[2018\\-09\\-08].*[30.0].*[Av. Coronel Antonino, 2091 \\- coronel antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
        assertThat(lines[3].matches(".*[descrição 2].*[VENDER].*[APARTAMENTO].*[\\[Quarto, 2, Sacada, 1, Banheiro, 2\\]].*[anuncio 2].*[2\\\\.0].*[2018\\-09\\-23].*[20.0].*[R. Santo Ângelo, 51 \\- Cel. Antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
    }

    @Test
    public void exportar_csv_cabecalho_rodape() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);
        componentes.add(RODAPE);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(CSV, componentes);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));

        assertThat(lines[0].matches(".*[usuario:matheus ale da silva].*[Tipo dos anuncios:ALUGAR].*"), is(true));
        assertThat(lines[1].matches(".*[Total:1500002\\.0].*"), is(true));
    }

    @Test
    public void exportar_csv_rodape_dados() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(DADOS);
        componentes.add(RODAPE);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(CSV, componentes);

        String[] lines = new String(bytes, StandardCharsets.UTF_8).split(System.getProperty("line.separator"));

        assertThat(lines[0].matches(".*[Descrição].*[Tipo].*[Tipo do Imovel].*[Caracteristicas].*[Titulo].*[Valor].*[Data de Publicação].*[área Total Contruida].*[Endreço].*"), is(true));
        assertThat(lines[1].matches(".*[Lorem ipsum dolor sit amet, co].*[VENDER].*[CASA].*[\\[Banheiro, 2, Sacada, 2, Cozinha, 2\\]].*[Casa].*[1500000\\\\.0].*[2018\\-09\\-08].*[30.0].*[Av. Coronel Antonino, 2091 \\- coronel antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
        assertThat(lines[2].matches(".*[descrição 2].*[VENDER].*[APARTAMENTO].*[\\[Quarto, 2, Sacada, 1, Banheiro, 2\\]].*[anuncio 2].*[2\\\\.0].*[2018\\-09\\-23].*[20.0].*[R. Santo Ângelo, 51 \\- Cel. Antonino, Campo Grande \\- Campo Grande, ms].*"), is(true));
        assertThat(lines[3].matches(".*[Total:1500002\\.0].*"), is(true));
    }

    @Test
    public void exportar_json() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);
        componentes.add(RODAPE);
        componentes.add(DADOS);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(JSON, componentes);

        HashMap<String, String> results = new Gson().fromJson(new String(bytes), HashMap.class);

        assertThat(results.containsKey("DADOS"), is(true));
        assertThat(results.containsKey("CABECALHO"), is(true));
        assertThat(results.containsKey("RODAPE"), is(true));
    }

    @Test
    public void exportar_json_cabecalho() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);
        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(JSON, componentes);

        HashMap<String, String> results = new Gson().fromJson(new String(bytes), HashMap.class);

        assertThat(results.containsKey("CABECALHO"), is(true));
        assertThat(results.containsKey("DADOS"), is(false));
        assertThat(results.containsKey("RODAPE"), is(false));
    }

    @Test
    public void exportar_json_rodape() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(RODAPE);
        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(JSON, componentes);

        HashMap<String, String> results = new Gson().fromJson(new String(bytes), HashMap.class);

        assertThat(results.containsKey("RODAPE"), is(true));
        assertThat(results.containsKey("CABECALHO"), is(false));
        assertThat(results.containsKey("DADOS"), is(false));
    }

    @Test
    public void exportar_json_dados() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(DADOS);
        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(JSON, componentes);

        HashMap<String, String> results = new Gson().fromJson(new String(bytes), HashMap.class);

        assertThat(results.containsKey("DADOS"), is(true));
        assertThat(results.containsKey("CABECALHO"), is(false));
        assertThat(results.containsKey("RODAPE"), is(false));
    }

    @Test
    public void exportar_json_cabecalho_dados() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);
        componentes.add(DADOS);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(JSON, componentes);

        HashMap<String, String> results = new Gson().fromJson(new String(bytes), HashMap.class);

        assertThat(results.containsKey("DADOS"), is(true));
        assertThat(results.containsKey("CABECALHO"), is(true));
        assertThat(results.containsKey("RODAPE"), is(false));
    }


    @Test
    public void exportar_json_cabecalho_rodape() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(CABECALHO);
        componentes.add(RODAPE);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(JSON, componentes);

        HashMap<String, String> results = new Gson().fromJson(new String(bytes), HashMap.class);

        assertThat(results.containsKey("RODAPE"), is(true));
        assertThat(results.containsKey("CABECALHO"), is(true));
        assertThat(results.containsKey("DADOS"), is(false));
    }

    @Test
    public void exportar_json_dados_rodape() throws UsuarioException.UsuarioNaoEncontrado {
        componentes.add(DADOS);
        componentes.add(RODAPE);

        byte[] bytes = (byte[]) usuario.getRelatorioDeVendas()
                .exportar()
                .para(JSON, componentes);

        HashMap<String, String> results = new Gson().fromJson(new String(bytes), HashMap.class);

        assertThat(results.containsKey("DADOS"), is(true));
        assertThat(results.containsKey("RODAPE"), is(true));
        assertThat(results.containsKey("CABECALHO"), is(false));
    }

}