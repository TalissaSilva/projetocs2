package builders;

import morabem.domain.Endereco;
import morabem.domain.Foto;
import morabem.domain.Usuario;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class UsuarioBuilder {

    private UsuarioBuilder() {
    }

    public static class PessoaFisica {

        private morabem.domain.PessoaFisica pessoaFisica;

        private PessoaFisica() {}

        public static PessoaFisica obterUm() {
            PessoaFisica builder = new PessoaFisica();
            builder.pessoaFisica = new morabem.domain.PessoaFisica(
                    "Nome", "Tel 01", "Tel 02", "foo@email", "foo senha", "foo cpf", 1
            );
            builder.pessoaFisica.setEndereco(EnderecoBuilder.obterUm().agora());
            builder.pessoaFisica.setFotoPerfil(null);
            return builder;
        }

        public PessoaFisica comASenha(String pass) {
            pessoaFisica.setSenha(pass);
            return this;
        }

        public PessoaFisica comOEmail(String email) {
            pessoaFisica.setEmail(email);
            return this;
        }

        public morabem.domain.PessoaFisica agora() {
            return pessoaFisica;
        }

        public PessoaFisica comEndereco(Endereco end) {
            pessoaFisica.setEndereco(end);
            return this;
        }

        public MultiValueMap<String, String> comoParametros() {
            MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
            values.add("cpf", pessoaFisica.getCpf());
            values.add("nome", pessoaFisica.getNome());
            values.add("telefone01", pessoaFisica.getTelefone01());
            values.add("telefone02", pessoaFisica.getTelefone02());
            values.add("endereco.cep", pessoaFisica.getEndereco().getCep());
            values.add("endereco.logradouro", pessoaFisica.getEndereco().getLogradouro());
            values.add("endereco.bairro", pessoaFisica.getEndereco().getBairro());
            values.add("endereco.cidade", pessoaFisica.getEndereco().getCidade());
            values.add("endereco.uf", pessoaFisica.getEndereco().getUf());
            values.add("email", pessoaFisica.getEmail());
            values.add("senha", pessoaFisica.getSenha());
            values.add("numero", String.valueOf(pessoaFisica.getNumero()));
            return values;
        }
    }

    public static class PessoaJuridica {

        private morabem.domain.PessoaJuridica pessoaJuridica;

        private PessoaJuridica() {}

        public static PessoaJuridica obterUm() {
            PessoaJuridica builder = new PessoaJuridica();
            builder.pessoaJuridica = new morabem.domain.PessoaJuridica(
                    "Nome", "Tel 01", "Tel 02", "foo@email", "foo senha", "foo cnpj", "foo creci", "foo", 1
            );

            return builder;
        }

        public morabem.domain.PessoaJuridica agora() {
            return pessoaJuridica;
        }

        public PessoaJuridica comASenha(String pass) {
            pessoaJuridica.setSenha(pass);
            return this;
        }

        public PessoaJuridica comOEmail(String email) {
            pessoaJuridica.setEmail(email);
            return this;
        }
    }
}
