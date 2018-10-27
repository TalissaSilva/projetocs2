package morabem.domain.relatorio;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExportarStrategy<T, R> {

    private Map<Exportar.Formato, ExportarHandler<T, R>> handlers = new HashMap<>();
    Map<Exportar.Componente, Object> componentes = new HashMap<>();

    ExportarStrategy<T, R> sethandler(Exportar.Formato formato, ExportarHandler<T, R> handler) {
        this.handlers.put(formato, handler);
        return this;
    }

    ExportarStrategy adicionar(Exportar.Componente c, Object d) {
        componentes.put(c, d);
        return this;
    }

    public R para(Exportar.Formato formato, List<Exportar.Componente> componentes) {
        if (this.handlers.containsKey(formato)) {
            return this.handlers.get(formato)
                    .setComponentes(this.componentes)
                    .exportar(componentes);
        } else {
            throw new RuntimeException("Formato não suportado.");
        }
    }
}
