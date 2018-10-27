package morabem.domain.relatorio;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ExportarhandlerJSONBytesArray<T> implements ExportarHandler<T, byte[]> {

    private Gson gson;
    Map<String, Object> paylaod;

    @Override
    public void start() {
        this.paylaod = new HashMap<>();
        this.gson = new Gson();
    }

    @Override
    public void inserir(Exportar.Componente componente) {
        paylaod.put(componente.name(), componentes.get(componente));
    }

    @Override
    public byte[] gerar() {
        return gson.toJson(paylaod).getBytes();
    }

}
