package morabem.utils;

import morabem.domain.Anuncio;

public class BuscaData {

    private String titulo;
    private String descricao;
    private Anuncio.Tipo tipo;
    private PrecoData preco = new PrecoData();
    private ImovelData imovel = new ImovelData();


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Anuncio.Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Anuncio.Tipo tipo) {
        this.tipo = tipo;
    }

    public PrecoData getPreco() {
        return preco;
    }

    public void setPreco(PrecoData preco) {
        this.preco = preco;
    }

    public ImovelData getImovel() {
        return imovel;
    }

    public void setImovel(ImovelData imovel) {
        this.imovel = imovel;
    }
}
