package morabem.domain.relatorio;

import morabem.domain.Anuncio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public abstract class ExportarStrategy<T, R> {

    protected List<T> itens;
    private Map<Formato, ExportarHandler<T, R>> handlers = new HashMap<>();
    protected Map<String, Object> cabecalho = new HashMap<>();
    protected Map<String, Object> rodape = new HashMap<>();


    ExportarStrategy(List<T> itens) {
        this.itens = itens;
    }

    protected ExportarStrategy<T, R> sethandler(Formato formato, ExportarHandler<T, R> handler) {
        this.handlers.put(formato, handler);
        return this;
    }

    public ExportarStrategy adicionarCabecalho(String key, String value) {
        cabecalho.put(key, value);
        return this;
    }

    public ExportarStrategy adicionarRodape(String key, String value) {
        rodape.put(key, value);
        return this;
    }

    public R para(Formato formato) {
        if (this.handlers.containsKey(formato)) {
                return this.handlers.get(formato)
                        .adicionarCabecalho(cabecalho)
                        .adicionarRodape(rodape)
                        .exportar(this.itens)
                        .gerar();
        } else {
            throw new RuntimeException("Formato não suportado.");
        }
    }

    public static enum Formato {
        CSV,
        JSON,
        TXT
    }

}
