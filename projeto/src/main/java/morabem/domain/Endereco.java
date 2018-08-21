package morabem.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @link { https://www.ibm.com/developerworks/community/blogs/fd26864d-cb41-49cf-b719-d89c6b072893/entry/usando_uma_secondary_table_no_hibernate?lang=en }
 */
@Entity
public class Endereco implements Serializable {

    private Integer numero;
	private String logradouro;
	private String bairro;
	@Id
	private String cep;
	private String cidade;
	private String uf;

	public Endereco() {
	}

	public Endereco(int numero, String logradouro, String bairro, String cep, String cidade, String uf) {
		this.numero = numero;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
	}

    @Override
    public String toString() {
        return "Endereco{" +
                "numero=" + numero +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }

    public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
