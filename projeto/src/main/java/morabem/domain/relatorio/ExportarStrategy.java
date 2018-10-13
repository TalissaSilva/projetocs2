package morabem.domain.relatorio;

import morabem.domain.Anuncio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExportarStrategy<T, R> {

    protected List<T> itens;
    protected Map<String, Object> cabecalho = new HashMap<>();
    protected Map<String, Object> rodape = new HashMap<>();

    ExportarStrategy(List<T> itens) {
        this.itens = itens;
    }

    public ExportarStrategy adicionarCabecalho(String key, String value) {
        this.cabecalho.put(key, value);
        return this;
    }

    public ExportarStrategy adicionarRodape(String key, String value) {
        this.rodape.put(key, value);
        return this;
    }

    abstract R para(Formato f);

    protected ExportarHandler<T, R> para(ExportarHandler<T, R> handler) {
        handler.adicionarRodape(rodape);
        handler.adicionarCabecalho(cabecalho);
        handler.exportar(this.itens);
        return handler;
    }

    public static enum Formato {
        CSV,
        JSON,
        TXT
    }

}
