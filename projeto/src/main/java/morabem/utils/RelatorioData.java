package morabem.utils;

import morabem.domain.Anuncio;

import java.util.List;

public class RelatorioData {

    private List<Anuncio> anuncios;
    private Double total;

    public RelatorioData(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
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
}
