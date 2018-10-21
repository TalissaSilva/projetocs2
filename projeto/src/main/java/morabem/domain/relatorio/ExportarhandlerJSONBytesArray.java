package morabem.domain.relatorio;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ExportarhandlerJSONBytesArray<T> implements ExportarHandler<T, byte[]> {

    @Override
    public byte[] gerar() {
        Gson gson = new Gson();
        Map<String, Object> paylaod = new HashMap<>();
        paylaod.put("cabecalho", cabecalhos);
        paylaod.put("data", dados);
        paylaod.put("rodape", rodapes);
        return gson.toJson(paylaod).getBytes();
    }

}
