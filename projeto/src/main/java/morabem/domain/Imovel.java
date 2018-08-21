package morabem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="imovel")
public class Imovel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Tipo tipo;
	private Double areaConstruida;
	private Double areaTotal;

    @OneToMany(mappedBy = "atributo")
	private Set<AtributoImovel> atributos = new HashSet();

    @ManyToOne
    private Endereco endereco;

    @ManyToOne()
    private Usuario dono;

    @OneToMany()
    private List<Foto> fotos = new ArrayList();

    public Imovel() { }

    public Imovel(Tipo tipo, Double areaConstruida, Double areaTotal, Usuario dono) {
        this.tipo = tipo;
        this.areaConstruida = areaConstruida;
        this.areaTotal = areaTotal;
        this.dono = dono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


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

    public Set<AtributoImovel> getAtributos() {
        return atributos;
    }

    public void setAtributos(Set<AtributoImovel> atributos) {
        this.atributos = atributos;
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

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", areaConstruida=" + areaConstruida +
                ", areaTotal=" + areaTotal +
                ", atributos=" + atributos +
                ", endereco=" + endereco +
                ", dono=" + dono +
                ", fotos=" + fotos +
                '}';
    }

    public static enum Tipo {
        CASA,
        APARTAMENTO,
        TERRENO,
        FAZENDA
    }
}
