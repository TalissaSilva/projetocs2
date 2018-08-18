package imoveis.domain;

import java.util.List;
import java.util.ArrayList;

public class Imovel {
	public String tipo;
	public float areaConstruida;
	public float areaTotal;
	//lista de atributos do imovel
	List<Atributo> atributos =  new ArrayList<Atributo>();
	
    //lista de fotos do imovel
	List<Foto> fotos =  new ArrayList<Foto>();
	
	//construtor
	public Imovel() {
		super();
	}

	//getters e setters 
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getAreaConstruida() {
		return areaConstruida;
	}
	public void setAreaConstruida(float areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	public float getAreaTotal() {
		return areaTotal;
	}
	public void setAreaTotal(float areaTotal) {
		this.areaTotal = areaTotal;
	}
	public List<Atributo> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}



}
