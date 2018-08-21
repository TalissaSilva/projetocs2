package morabem.services;

import builders.AtributoBuilder;
import builders.ImovelBuilder;
import builders.UsuarioBuilder;
import morabem.domain.AtributoImovel;
import morabem.domain.Imovel;
import morabem.domain.Usuario;
import morabem.repositories.AtributoRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImovelServiceTest {

    @Autowired
    public ImovelService imovelService;

    @MockBean
    public ImovelRepository imovelRepository;

    @MockBean
    public AtributoRepository atributoRepository;

    @MockBean
    public AtributoImovelService atributoImovelService;

    @MockBean
    public AtributoService atributoService;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void SalvarNovoImovel() {
        Mockito.doReturn(Arrays.asList(
                AtributoBuilder.obterUm().comId(1).agora(),
                AtributoBuilder.obterUm().comId(2).agora()
        )).when(atributoService).obterAtributos(1, 2);
        Mockito.doNothing().when(atributoImovelService)
                .salvarTudo(Mockito.any());

        Usuario usuario = UsuarioBuilder.PessoaFisica.obterUm().agora();
        Imovel imovel = ImovelBuilder.obterUm().comODono(usuario).agora();
        Set<AtributoImovel> atributosImovel = atributoService
                .obterAtributos(1, 2)
                .stream()
                .map(at -> new AtributoImovel(imovel, at, 1))
                .collect(Collectors.toSet());
        imovel.setAtributos(atributosImovel);

        imovelService.salvar(imovel);

        error.checkThat(imovel.getAtributos(), is(equalTo(atributosImovel)));
        error.checkThat(imovel.getDono(), is(equalTo(usuario)));
    }
}