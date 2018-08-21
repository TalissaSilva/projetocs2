package builders;

import morabem.domain.Endereco;

public class EnderecoBuilder {

    private Endereco endereco;

    private EnderecoBuilder() {}

    public static EnderecoBuilder obterUm() {
        EnderecoBuilder builder = new EnderecoBuilder();
        builder.endereco = new Endereco(1, "Rua", "Bairro", "79011000", "Cidade", "UF");
        return builder;
    }

    public Endereco agora() {
        return endereco;
    }
}
