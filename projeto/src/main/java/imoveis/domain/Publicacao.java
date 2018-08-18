package imoveis.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Publicacao {
	public String id;
	public String titulo;
	public String tipo;
	public Date dataInicio;
	public Date dataFinal;
	public long valor;
	public String descricao;//alterar
    //lista de fotos da publicacao
	List<Foto> fotos =  new ArrayList<Foto>();
	
	//construtor
	public Publicacao() {
		super();
	}
	
	//getters e setters 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public long getValor() {
		return valor;
	}
	public void setValor(long valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Foto> getFotos() {
		return fotos;
	}
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}





}
