package builders;

import morabem.domain.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class ImovelBuilder {

    private Imovel imovel;

    private ImovelBuilder() { }

    public static ImovelBuilder obterUm() {
        ImovelBuilder imovelBuilder = new ImovelBuilder();
        imovelBuilder.imovel = new Imovel(Imovel.Tipo.CASA,200D, 400D, null, 1);
        imovelBuilder.imovel.setEndereco(EnderecoBuilder.obterUm().agora());
        imovelBuilder.imovel.setDono(UsuarioBuilder.PessoaFisica.obterUm().agora());
        imovelBuilder.imovel.getCaracteristicas().add("foo, 2");
        return imovelBuilder;
    }

    public Imovel agora() {
        return imovel;
    }

    public ImovelBuilder comODono(Usuario usuario) {
        imovel.setDono(usuario);
        return this;
    }

    public ImovelBuilder comAsCaracteristicas(List<String> att) {
        imovel.setCaracteristicas(att);
        return this;
    }

    public ImovelBuilder comEndereco(Endereco end) {
        imovel.setEndereco(end);
        return this;
    }

    public MultiValueMap<String, String> comoParametros() {
        MultiValueMap<String, String> values = new LinkedMultiValueMap<String, String>();
        values.add("areaTotal", String.valueOf(imovel.getAreaTotal()));
        values.add("areaConstruida", String.valueOf(imovel.getAreaConstruida()));
        values.add("endereco.cep", imovel.getEndereco().getCep());
        values.add("endereco.logradouro", imovel.getEndereco().getLogradouro());
        values.add("endereco.bairro", imovel.getEndereco().getBairro());
        values.add("endereco.cidade", imovel.getEndereco().getCidade());
        values.add("endereco.uf", imovel.getEndereco().getUf());
        values.add("numero", String.valueOf(imovel.getNumero()));
        values.add("tipo", String.valueOf(imovel.getTipo()));
        values.add("caracteristicas", imovel.getCaracteristicas().stream().reduce("", (a, i) -> a.concat(", " + i)));
        return values;
    }
}
