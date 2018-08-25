package builders;

import morabem.domain.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class ImovelBuilder {

    private Imovel imovel;

    private ImovelBuilder() { }

    public static ImovelBuilder obterUm() {
        ImovelBuilder imovelBuilder = new ImovelBuilder();
        imovelBuilder.imovel = new Imovel(Imovel.Tipo.CASA,200D, 400D, null, 1);
        imovelBuilder.imovel.setEndereco(EnderecoBuilder.obterUm().agora());
        imovelBuilder.imovel.setDono(UsuarioBuilder.PessoaFisica.obterUm().agora());
        /*imovelBuilder.imovel.getFotos().addAll(Arrays.asList(
                FotoBuilder.obterUma().deId(1L).agora(),
                FotoBuilder.obterUma().deId(2L).agora(),
                FotoBuilder.obterUma().deId(3L).agora()
        ));*/
        return imovelBuilder;
    }

    public Imovel agora() {
        return imovel;
    }

    public ImovelBuilder comODono(Usuario usuario) {
        imovel.setDono(usuario);
        return this;
    }

    public ImovelBuilder comOsAtributos(Set<AtributoImovel> att) {
        imovel.setAtributos(att);
        return this;
    }

    public ImovelBuilder comEndereco(Endereco end) {
        imovel.setEndereco(end);
        return this;
    }
}
