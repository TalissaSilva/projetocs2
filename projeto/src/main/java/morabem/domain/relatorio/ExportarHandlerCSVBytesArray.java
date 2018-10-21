package morabem.domain.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class ExportarHandlerCSVBytesArray<T> implements ExportarHandler<T, byte[]> {

    public String formatarItem(Map<String, Object> item) {
        List<String> list = new LinkedList<>();

        for (String titulo : titulos) {
            list.add(String.valueOf(item.get(titulo)));
        }

        return String.join(";", list);
    }

    @Override
    public byte[] gerar() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream ();
        try {
            List<String> list = new LinkedList<>();
            for (Map.Entry entry : cabecalhos.entrySet()) {
                list.add(String.format("%s:%s", entry.getKey(), entry.getValue()));
            }
            buffer.write(String.join(";", list).concat("\r\n").getBytes());
            buffer.write(String.join(";", titulos).concat("\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dados.stream()
                .map(i -> this.formatarItem(i) + "\r\n" )
                .map(String::getBytes)
                .forEach(bytes -> {
                    try {
                        buffer.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        try {
            List<String> list = new LinkedList<>();
            for (Map.Entry entry : rodapes.entrySet()) {
                list.add(String.format("%s:%s", entry.getKey(), entry.getValue()));
            }
            buffer.write(String.join(";", list).concat("\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toByteArray();
    }
}
