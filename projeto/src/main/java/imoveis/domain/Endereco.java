package imoveis.domain;

public class Endereco {
public String numero;
public String rua;
public String bairro;
public String cidade;
public String estado;
public String cep;

//construtor
public Endereco() {
	super();
}

//metodos getters e setters
public String getNumero() {
	return numero;
}

public void setNumero(String numero) {
	this.numero = numero;
}

public String getRua() {
	return rua;
}

public void setRua(String rua) {
	this.rua = rua;
}

public String getBairro() {
	return bairro;
}

public void setBairro(String bairro) {
	this.bairro = bairro;
}

public String getCidade() {
	return cidade;
}

public void setCidade(String cidade) {
	this.cidade = cidade;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public String getCep() {
	return cep;
}

public void setCep(String cep) {
	this.cep = cep;
}


}
