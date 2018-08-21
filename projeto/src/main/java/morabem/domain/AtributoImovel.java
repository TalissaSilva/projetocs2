package morabem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @see { https://www.thoughts-on-java.org/many-relationships-additional-properties/ }
 * para saber em detalhes como essa classe funciona.
 */
@Entity
public class AtributoImovel {


    @EmbeddedId
    private AtributoImovelId id;

    @ManyToOne
    @JoinColumn(name = "fk_imovel", insertable = false, updatable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "fk_atributo", insertable = false, updatable = false)
    private Atributo atributo;

    private Integer quantidade;

    public AtributoImovel(Imovel imovel, Atributo atributo, Integer quantidade) {
        this.id = new AtributoImovelId(imovel.getId(), atributo.getId());
        this.imovel = imovel;
        this.atributo = atributo;
        this.quantidade = quantidade;

        imovel.getAtributos().add(this);
        atributo.getImoveis().add(this);
    }

    public AtributoImovel() {
    }

    public AtributoImovelId getId() {
        return id;
    }

    public void setId(AtributoImovelId id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtributoImovel that = (AtributoImovel) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getImovel(), that.getImovel()) &&
                Objects.equals(getAtributo(), that.getAtributo()) &&
                Objects.equals(getQuantidade(), that.getQuantidade());
    }

    @Override
    public String toString() {
        return "AtributoImovel{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getImovel(), getAtributo(), getQuantidade());
    }

    @Embeddable
    public static class AtributoImovelId implements Serializable {

        @Column(name = "fk_imovel")
        private Integer imovel;

        @Column(name = "fk_atributo")
        private Integer atributo;

        public AtributoImovelId(Integer imovel, Integer atributo) {
            this.imovel = imovel;
            this.atributo = atributo;
        }

        public AtributoImovelId() {
        }

        public Integer getImovel() {
            return imovel;
        }

        public void setImovel(Integer imovel) {
            this.imovel = imovel;
        }

        public Integer getAtributo() {
            return atributo;
        }

        public void setAtributo(Integer atributo) {
            this.atributo = atributo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AtributoImovelId that = (AtributoImovelId) o;
            return Objects.equals(getImovel(), that.getImovel()) &&
                    Objects.equals(getAtributo(), that.getAtributo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getImovel(), getAtributo());
        }

        @Override
        public String toString() {
            return "AtributoImovelId{" +
                    "imovel=" + imovel +
                    ", atributo=" + atributo +
                    '}';
        }
    }
}
