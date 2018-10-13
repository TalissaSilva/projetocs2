package morabem.domain.relatorio;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;

import java.util.List;

public class Relatorio {

    private List<Anuncio> anuncios;
    private Double total;
    private Usuario usuario;
    private Anuncio.Tipo tipo;

    public Relatorio(List<Anuncio> anuncios, Anuncio.Tipo tipo, Usuario usuario) {
        this.anuncios = anuncios;
        this.usuario = usuario;
        this.tipo = tipo;
        this.total = anuncios.stream().mapToDouble(Anuncio::getValor).sum();
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ExportarStrategy<Anuncio, byte[]> exportar() {
        return new ExportarAnunciosStrategy(this.anuncios)
                .adicionarCabecalho("usuario", usuario.getNome())
                .adicionarCabecalho("Tipo dos anuncios", tipo.toString())
                .adicionarRodape("Total", String.valueOf(this.total));
    }
}
