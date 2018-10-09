package morabem.repositories;

import morabem.domain.Anuncio;
import morabem.domain.Endereco;
import morabem.domain.PessoaFisica;

import morabem.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import builders.AnuncioBuilder;
import builders.UsuarioBuilder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import morabem.repositories.*;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnuncioRepositoryTest {

	@Autowired
	private AnuncioRepository anuncioRepository;


    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private ImovelRepository imovelRepository;

	@Test
	public void buscar_titulo() {
		Page<Anuncio> list = anuncioRepository.buscar("casa", null,  null, null, null, null);
		list.forEach((a) -> {
			assertThat(a.getTitulo().toLowerCase().contains("casa"), is(true));
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
			assertThat(a.getImovel().getEndereco().getLogradouro().toLowerCase().contains("coronel"), is(true));
		});
	}

	@Test
	public void buscar_bairro() {
		Page<Anuncio> list = anuncioRepository.buscar(null, null, null, "coronel", null, null);
		list.forEach((a) -> {
			assertThat(a.getImovel().getEndereco().getBairro().toLowerCase().contains("coronel"), is(true));
		});
	}

	@Test
	public void buscar_cidade() {
		Page<Anuncio> list = anuncioRepository.buscar(null, null, null, null, "campo", null);
		list.forEach((a) -> {
			assertThat(a.getImovel().getEndereco().getCidade().toLowerCase().contains("campo"), is(true));
		});
	}

	@Test
	public void obterAnucniosDoTipoVendaDoUsuario() {
		PessoaFisica u = pessoaFisicaRepository.findById(1L).get();
		Boolean result = anuncioRepository.getAllByTipoEqualsAndAnuncianteEquals(Anuncio.Tipo.VENDER, u)
			.stream()
			.map(Anuncio::getTipo)
			.allMatch((Anuncio.Tipo t) -> t == Anuncio.Tipo.VENDER );

		assertThat(result, equalTo(true));
	}

	@Test
	public void obterAnucniosDoTipoAluguelDoUsuario() {
		PessoaFisica u = pessoaFisicaRepository.findById(1L).get();
		Boolean result = anuncioRepository.getAllByTipoEqualsAndAnuncianteEquals(Anuncio.Tipo.ALUGAR, u)
				.stream()
				.map(Anuncio::getTipo)
				.allMatch((Anuncio.Tipo t) -> t == Anuncio.Tipo.ALUGAR );

		assertThat(result, equalTo(true));
	}
}