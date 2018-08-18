package imoveis.domain;

public class Atributo {
	public String nome;
	public int quantidade;

	//construtor
	public Atributo(String nome, int quantidade) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
	}

	//metodos getters e setters
	public String getNome() {
		return nome;
	}
	public void setNomeAtributo(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


}
