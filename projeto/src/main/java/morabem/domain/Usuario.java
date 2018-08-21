package morabem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone01;
    private String telefone02;
    private String email;
    private String senha;

    @ManyToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "dono")
    private List<Imovel> imoveis = new ArrayList();

    @OneToOne
    private Foto fotoPerfil;

    @OneToMany(mappedBy = "anunciante")
    @Column(nullable = true)
    private List<Anuncio> anuncios = new ArrayList();


    public Usuario() { }

    public Usuario(String nome, String telefone01, String telefone02, String email, String senha) {
        this.nome = nome;
        this.telefone01 = telefone01;
        this.telefone02 = telefone02;
        this.email = email;
        this.senha = senha;
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

    public List<Imovel> getImoveis() {
        return imoveis;
    }

    public void setImoveis(List<Imovel> imoveis) {
        this.imoveis = imoveis;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone01='" + telefone01 + '\'' +
                ", telefone02='" + telefone02 + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", endereco=" + endereco +
                ", imoveis=" + imoveis +
                ", fotoPerfil=" + fotoPerfil +
                ", anuncios=" + anuncios +
                '}';
    }


    public void setFotoPerfil(Foto fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
