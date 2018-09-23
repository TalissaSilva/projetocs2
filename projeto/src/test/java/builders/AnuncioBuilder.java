package builders;
import morabem.domain.Anuncio;
import morabem.domain.Usuario;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public class AnuncioBuilder {

    private Anuncio anuncio;

	private AnuncioBuilder() { }

	public static AnuncioBuilder obterUm() {
        AnuncioBuilder builder = new AnuncioBuilder();
		builder.anuncio = new morabem.domain.Anuncio();
		builder.anuncio.setAnunciante(UsuarioBuilder.PessoaJuridica.obterUm().agora());
		builder.anuncio.setImovel(ImovelBuilder.obterUm().agora());
		return builder;
	}

    public AnuncioBuilder comOtitulo(String s) {
        anuncio.setTitulo(s);
        return this;
    }

    public AnuncioBuilder comADescricao(String s) {
        anuncio.setDescricao(s);
        return this;
    }

    public AnuncioBuilder comOAnunciante(Usuario s) {
        anuncio.setAnunciante(s);
        return this;
    }
	public Anuncio agora() {
		return anuncio;
	}
}