package morabem.repositories;

import builders.EnderecoBuilder;
import builders.ImovelBuilder;
import builders.UsuarioBuilder;
import morabem.domain.Endereco;
import morabem.domain.Imovel;
import morabem.domain.PessoaFisica;
import morabem.services.UsuarioService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaFisicaRepositoryTest {

    @Autowired
    public TestEntityManager testEntityManager;

    @Autowired
    public PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public EnderecoRepository enderecoRepository;

    @Autowired
    public ImovelRepository imovelRepository;


    @Test
    public void salvarPessoaFisica() {
        Endereco end = EnderecoBuilder.obterUm().agora();
        PessoaFisica usuario = UsuarioBuilder.PessoaFisica.obterUm()
                .comASenha("123456").comOEmail("user@email").comEndereco(end).agora();
        enderecoRepository.save(end);
        enderecoRepository.flush();
        pessoaFisicaRepository.save(usuario);
        pessoaFisicaRepository.flush();
    }

    @Test
    public void buscarPorEmailESenha() {
        PessoaFisica p = pessoaFisicaRepository.findFirstByEmailEqualsAndSenhaEquals("user@email", "123456");
        assertThat(p.getEmail(), is(equalTo("user@email")));
        assertThat(p.getSenha(), is(equalTo("123456")));
    }

}