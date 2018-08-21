package morabem.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Atributo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String nome;

	public Atributo(String nome) {
		this.nome = nome;
	}

	@OneToMany(mappedBy = "imovel")
	private Set<AtributoImovel> imoveis = new HashSet<>();

	public Atributo() {
	}

	@Override
	public String toString() {
		return "Atributo{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", imoveis=" + imoveis +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<AtributoImovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(Set<AtributoImovel> imoveis) {
		this.imoveis = imoveis;
	}
}
