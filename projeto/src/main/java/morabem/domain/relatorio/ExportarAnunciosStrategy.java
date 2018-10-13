package morabem.domain.relatorio;

import morabem.domain.Anuncio;

import java.util.List;

public final class ExportarAnunciosStrategy extends ExportarStrategy<Anuncio, byte[]> {


    ExportarAnunciosStrategy(List<Anuncio> anuncios) {
        super(anuncios);
    }

    public byte[] para(Formato tipo) {
        switch (tipo) {
            case CSV:
                return para(new ExportarHandlerCSVBytesArray<Anuncio>()).gerar();
            case JSON:
            case TXT:
        }
        return null;
    }
}
