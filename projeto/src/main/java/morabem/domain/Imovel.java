package morabem.domain;

import morabem.domain.relatorio.Exportavel;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="imovel")
public class Imovel implements Serializable {

	@Id
    @Column(name = "imovel_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Tipo tipo;
	private Double areaConstruida;
	private Double areaTotal;
	private Integer numero;

	@ElementCollection
    private List<String> caracteristicas = new LinkedList<>();

    @ManyToOne()
    private Endereco endereco = new Endereco();

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario dono;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn()
    private List<Foto> fotos = new ArrayList();

    public Imovel() { }

    public Imovel(Tipo tipo, Double areaConstruida, Double areaTotal, Usuario dono, Integer numero) {
        this.tipo = tipo;
        this.areaConstruida = areaConstruida;
        this.areaTotal = areaTotal;
        this.dono = dono;
        this.numero = numero;
    }

    @Exportavel(titulo = "área Total Contruida")
    public Double getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(Double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(Double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    @Exportavel(titulo = "Tipo do Imovel")
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return Objects.equals(getId(), imovel.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Exportavel(titulo = "Caracteristicas")
    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public static enum Tipo {
        CASA,
        APARTAMENTO,
        TERRENO,
        FAZENDA
    }

    @Exportavel(titulo = "Endereco")
    public String printEndereco() {
        return this.endereco.getLogradouro() + ", " + this.numero +
                " - " + this.endereco.getBairro() + ", " + this.endereco.getCidade() +
                " - " + this.endereco.getCidade() + ", " + this.endereco.getUf();
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", areaConstruida=" + areaConstruida +
                ", areaTotal=" + areaTotal +
                ", numero=" + numero +
                ", caracteristicas=" + caracteristicas +
                ", endereco=" + endereco +
                ", dono=" + dono +
                ", fotos=" + fotos +
                '}';
    }
}
