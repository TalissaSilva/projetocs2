package morabem.domain.relatorio;

import morabem.domain.Anuncio;

import java.util.List;

public final class ExportarAnunciosStrategy extends ExportarStrategy<Anuncio, byte[]> {

    ExportarAnunciosStrategy(List<Anuncio> anuncios) {
        super(anuncios);
        super.sethandler(Formato.CSV, new ExportarHandlerCSVBytesArray<Anuncio>())
                .sethandler(Formato.JSON, new ExportarhandlerJSONBytesArray<Anuncio>());
    }
}
