package morabem.domain.relatorio;

import morabem.domain.Anuncio;

import java.util.List;

public final class ExportarAnunciosStrategy extends ExportarStrategy<Anuncio, byte[]> {

    ExportarAnunciosStrategy() {
        super.sethandler(Exportar.Formato.CSV, new ExportarHandlerCSVBytesArray<Anuncio>())
                .sethandler(Exportar.Formato.JSON, new ExportarhandlerJSONBytesArray<Anuncio>())
                .sethandler(Exportar.Formato.TXT, new ExportarHandlerCSVBytesArray<Anuncio>());
    }
}
