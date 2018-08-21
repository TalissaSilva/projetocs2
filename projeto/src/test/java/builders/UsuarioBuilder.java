package builders;

import morabem.domain.Endereco;
import morabem.domain.Foto;
import morabem.domain.Usuario;

public class UsuarioBuilder {

    private UsuarioBuilder() {
    }

    public static class PessoaFisica {

        private morabem.domain.PessoaFisica pessoaFisica;

        private PessoaFisica() {}

        public static PessoaFisica obterUm() {
            PessoaFisica builder = new PessoaFisica();
            builder.pessoaFisica = new morabem.domain.PessoaFisica(
                    "Nome", "Tel 01", "Tel 02", "foo@email", "foo senha", "foo cpf"
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
    }

    public static class PessoaJuridica {

        private morabem.domain.PessoaJuridica pessoaJuridica;

        private PessoaJuridica() {}

        public static PessoaJuridica obterUm() {
            PessoaJuridica builder = new PessoaJuridica();
            builder.pessoaJuridica = new morabem.domain.PessoaJuridica(
                    "Nome", "Tel 01", "Tel 02", "foo@email", "foo senha", "foo cnpj", "foo creci", "foo"
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
