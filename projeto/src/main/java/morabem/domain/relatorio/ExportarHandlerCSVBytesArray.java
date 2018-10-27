package morabem.domain.relatorio;

import com.opencsv.CSVWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

public class ExportarHandlerCSVBytesArray<T> implements ExportarHandler<T, byte[]> {

    private CSVWriter csvWriter;
    private StringWriter stringWriter;
    @Override
    public byte[] gerar() {
        try {
            csvWriter.close();
            return new String(stringWriter.getBuffer()).getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar os dados.", e);
        }
    }

    @Override
    public void start() {
        this.stringWriter = new StringWriter();
        this.csvWriter = new CSVWriter(stringWriter);
    }

    @Override
    public void inserir(Exportar.Componente componente) {
        List<String[]> rows = new LinkedList<>();

        if (componentes.get(componente) instanceof Map) {

        } else if (componentes.get(componente) instanceof List) {

        }

        if (componente == Exportar.Componente.DADOS) {
            List<Map<String, Object>> dados = (List<Map<String, Object>>) componentes.get(componente);
            String[] titulos = this.titulos.toArray(new String[]{});
            rows.add(titulos);

            for(Map<String, Object> dado : dados) {
                List<String> row = new LinkedList();
                for (String titulo : titulos) {
                    row.add(String.valueOf(dado.get(titulo)));
                }
                rows.add(row.toArray(new String[]{}));
            }
            csvWriter.writeAll(rows);
        } else {
            Map<String, Object> dado = (Map<String, Object>) componentes.get(componente);
            List<String> row = new LinkedList();
            dado.entrySet().stream()
                    .map(entry -> {
                        return String.valueOf(entry.getKey()).concat(":").concat(String.valueOf(entry.getValue()));
                    })
                    .forEach(row::add);

            csvWriter.writeNext(row.toArray(new String[]{}));
        }
    }
}
