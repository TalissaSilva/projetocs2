package morabem.services;

import builders.EnderecoBuilder;
import builders.ImovelBuilder;
import builders.UsuarioBuilder;
import morabem.domain.Imovel;
import morabem.repositories.EnderecoRepository;
import morabem.repositories.ImovelRepository;
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

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImovelServiceTest {

    @Autowired
    public ImovelService imovelService;

    @MockBean
    public ImovelRepository imovelRepository;

    @MockBean
    public EnderecoRepository enderecoRepository;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void SalvarNovoImovel() {
        Imovel imovel = ImovelBuilder.obterUm()
                .comODono(UsuarioBuilder.PessoaFisica.obterUm().agora())
                .comEndereco(EnderecoBuilder.obterUm().agora())
                .comAsCaracteristicas(Arrays.asList("foo, 1", "bar, 2"))
                .agora();

        Mockito.doReturn(imovel.getEndereco()).when(enderecoRepository).saveAndFlush(Mockito.any());

        imovelService.salvar(imovel);

        verify(enderecoRepository, times(1)).saveAndFlush(Mockito.any());
        verify(imovelRepository, times(1)).saveAndFlush(Mockito.any());
    }
}