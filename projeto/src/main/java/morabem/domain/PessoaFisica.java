package morabem.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends Usuario {

	private String cpf;

	public PessoaFisica() { }

	public PessoaFisica(String cpf) {
		this.cpf = cpf;
	}

	public PessoaFisica(String nome, String telefone01, String telefone02, String email, String senha, String cpf, Integer numero) {
		super(nome, telefone01, telefone02, email, senha, numero);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "PessoaFisica{" +
				"cpf='" + cpf + '\'' +
				"} " + super.toString();
	}
}

