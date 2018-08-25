package morabem.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Usuario {

	private String cnpj;
	private String creci;
	private String descricao;

    public PessoaJuridica() { }

    public PessoaJuridica(String nome, String telefone01, String telefone02, String email, String senha, String cnpj, String creci, String descricao, Integer numero) {
        super(nome, telefone01, telefone02, email, senha, numero);
        this.cnpj = cnpj;
        this.creci = creci;
        this.descricao = descricao;
    }

    public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCreci() {
		return creci;
	}

	public void setCreci(String creci) {
		this.creci = creci;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "cnpj='" + cnpj + '\'' +
                ", creci='" + creci + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
