package morabem.domain;

import org.hibernate.annotations.Type;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Anuncio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private Tipo tipo;

	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date datadaPublicacao;

	private Double valor;

	@Type(type = "text")
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario anunciante;

	@OneToOne(fetch = FetchType.EAGER)
	private Imovel imovel;

	public Anuncio() { }

	public Anuncio(String titulo, Tipo tipo, Date dataInicio, Double valor, String descricao, Usuario anunciante, Imovel imovel) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.datadaPublicacao = dataInicio;
		this.valor = valor;
		this.descricao = descricao;
		this.anunciante = anunciante;
		this.imovel = imovel;
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


	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}
	public String getDescricaoResulmida() {
		return descricao.length() > 30 ? descricao.substring(0, 30) : descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Usuario getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Usuario anunciante) {
		this.anunciante = anunciante;
	}

	public Date getDatadaPublicacao() {
		return datadaPublicacao;
	}

	public void setDatadaPublicacao(Date datadaPublicacao) {
		this.datadaPublicacao = datadaPublicacao;
	}

	public static enum Tipo {
		ALUGAR,
		VENDER
	}

	@Override
	public String toString() {
		return "Anuncio{" +
				"id=" + id +
				", titulo='" + titulo + '\'' +
				", tipo=" + tipo +
				", datadaPublicacao=" + datadaPublicacao +
				", valor=" + valor +
				", descricao='" + descricao + '\'' +
				", imovel=" + imovel +
				'}';
	}
}
