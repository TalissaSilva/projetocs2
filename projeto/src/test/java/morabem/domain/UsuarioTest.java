package morabem.domain;

import morabem.repositories.PessoaFisicaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioTest {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Test
    public void obterAnunciosDoTipoVendaDoUsuario() {
        PessoaFisica u = pessoaFisicaRepository.findById(1L).get();
        Boolean result = u.getRelatorioDeVendas().getAnuncios()
                .stream()
                .map(Anuncio::getTipo)
                .allMatch((Anuncio.Tipo t) -> t == Anuncio.Tipo.VENDER );

        assertThat(result, equalTo(true));
    }

    @Test
    public void obterAnunciosDoTipoAluguelDoUsuario() {
        PessoaFisica u = pessoaFisicaRepository.findById(1L).get();
        Boolean result = u.getRelatorioDeAlugueis().getAnuncios()
                .stream()
                .map(Anuncio::getTipo)
                .allMatch((Anuncio.Tipo t) -> t == Anuncio.Tipo.ALUGAR );

        assertThat(result, equalTo(true));
    }
}