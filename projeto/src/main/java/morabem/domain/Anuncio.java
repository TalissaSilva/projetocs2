package morabem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Anuncio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String titulo;
	private Tipo tipo;
	private Date dataInicio;
	private Date dataFinal;
	private Long valor;
	private String descricao;

	@ManyToOne
	private Usuario anunciante;
	public Anuncio() { }

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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
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

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static enum Tipo {
		ALUGAR,
		VENDER
	}
}
