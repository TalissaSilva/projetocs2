package morabem.domain;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedEntityGraph(
        name="Usuario.imoveis",
        attributeNodes = @NamedAttributeNode(value = "id"))
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone01;
    private String telefone02;
    private String email;
    private String senha;
    private Integer numero;

    @ManyToOne
    private Endereco endereco = new Endereco();

    @OneToMany(mappedBy = "dono")
    private Set<Imovel> imoveis = new LinkedHashSet<>();

    @OneToOne
    private Foto fotoPerfil;

    @OneToMany(mappedBy = "anunciante")
    @Column(nullable = true)
    private Set<Anuncio> anuncios = new LinkedHashSet<>();


    public Usuario() { }

    public Usuario(String nome, String telefone01, String telefone02, String email, String senha, Integer numero) {
        this.nome = nome;
        this.telefone01 = telefone01;
        this.telefone02 = telefone02;
        this.email = email;
        this.senha = senha;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone01() {
        return telefone01;
    }

    public void setTelefone01(String telefone01) {
        this.telefone01 = telefone01;
    }

    public String getTelefone02() {
        return telefone02;
    }

    public void setTelefone02(String telefone02) {
        this.telefone02 = telefone02;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    /*@Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone01='" + telefone01 + '\'' +
                ", telefone02='" + telefone02 + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", numero=" + numero +
                ", endereco=" + endereco +
                ", imoveis=" + imoveis +
                ", fotoPerfil=" + fotoPerfil +
                ", anuncios=" + anuncios +
                '}';
    }*/


    public void setFotoPerfil(Foto fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Foto getFotoPerfil() {
        return this.fotoPerfil;
    }
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Set<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Set<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public Set<Imovel> getImoveis() {
        return imoveis;
    }

    public void setImoveis(Set<Imovel> imoveis) {
        this.imoveis = imoveis;
    }
}
