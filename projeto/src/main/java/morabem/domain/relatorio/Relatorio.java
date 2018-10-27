package morabem.domain.relatorio;

import morabem.domain.Anuncio;
import morabem.domain.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static morabem.domain.relatorio.Exportar.Componente.*;
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

    public ExportarStrategy exportar() {

        Map<String, String> cabecalho = new HashMap<>();
        cabecalho.put("Tipo dos anuncios", tipo.toString());
        cabecalho.put("usuario", this.usuario.getNome());
        Map<String, String> rodape = new HashMap<>();
        rodape.put("Total", String.valueOf(this.total));

        return new ExportarAnunciosStrategy()
                .adicionar(DADOS, this.anuncios)
                .adicionar(CABECALHO, cabecalho)
                .adicionar(RODAPE, rodape);
    }
}
