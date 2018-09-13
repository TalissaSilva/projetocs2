package builders;
import morabem.domain.Anuncio;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
public class AnuncioBuilder {
	
	private AnuncioBuilder() {

	}
	public static class Anuncio{

		private morabem.domain.Anuncio anuncio;

		private Anuncio() {}

		public static Anuncio obterUm() {
			Anuncio builder = new Anuncio();
			builder.anuncio = new morabem.domain.Anuncio();
			builder.anuncio.setAnunciante(UsuarioBuilder.PessoaJuridica.obterUm().agora());
			builder.anuncio.setImovel(ImovelBuilder.obterUm().agora());
			return builder;
		}

		public morabem.domain.Anuncio agora() {
			return anuncio;
		}
	}
}