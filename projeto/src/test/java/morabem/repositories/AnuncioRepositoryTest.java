package morabem.repositories;

import morabem.domain.Anuncio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnuncioRepositoryTest {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Test
    public void buscar_titulo() {
        Page<Anuncio> list = anuncioRepository.buscar("titu", null,  null, null, null, null);
        list.forEach((a) -> {
            assertThat(a.getTitulo().contains("titu"), is(true));
        });
    }

    @Test
    public void buscar_descricao() {
        Page<Anuncio> list = anuncioRepository.buscar(null, "cricao", null, null, null, null);
        list.forEach((a) -> {
            assertThat(a.getDescricao().contains("cricao"), is(true));
        });
    }

    @Test
    public void buscar_Logradouro() {
        Page<Anuncio> list = anuncioRepository.buscar(null, null, "coronel", null, null, null);
        list.forEach((a) -> {
            assertThat(a.getImovel().getEndereco().getLogradouro().contains("coronel"), is(true));
        });
    }

    @Test
    public void buscar_bairro() {
        Page<Anuncio> list = anuncioRepository.buscar(null, null, null, "coronel", null, null);
        list.forEach((a) -> {
            assertThat(a.getImovel().getEndereco().getBairro().contains("coronel"), is(true));
        });
    }

    @Test
    public void buscar_cidade() {
        Page<Anuncio> list = anuncioRepository.buscar(null, null, null, null, "campo", null);
        list.forEach((a) -> {
            assertThat(a.getImovel().getEndereco().getCidade().contains("campo"), is(true));
        });
    }

    @Test
    public void obterAnuncioDeMaiorValor() {
       Anuncio anucioMaisCaro = anuncioRepository.findTopByOrderByValorDesc();
       assertThat(anucioMaisCaro, is(notNullValue()));
       assertThat(anucioMaisCaro.getValor(), is(equalTo(1500000.0D)));
    }

}