package morabem.domain.relatorio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public interface ExportarHandler<T, R> {

    List<Map<String, Object>> dados = new LinkedList<>();
    Map<String, Object> cabecalhos = new HashMap<>();
    Map<String, Object> rodapes = new HashMap<>();
    Set<String> titulos = new HashSet<>();

    R gerar();

    default ExportarHandler<T, R> adicionarCabecalho(Map<String, Object> c) {
        this.cabecalhos.putAll(c);
        return this;
    }

    default ExportarHandler<T, R> adicionarRodape(Map<String, Object> c) {
        this.rodapes.putAll(c);
        return this;
    }

    default ExportarHandler<T, R> exportar(List<T> dados) {
        obterDados(dados);
        return this;
    }

    default void obterDados(List<T> dados) {
        this.dados.clear();
        dados.stream()
                .map(this::mapearValoresDoDado)
                .forEach(this.dados::add);
    }

    default Map<String, Object> mapearValoresDoDado(Object item) {
        Class clazz = item.getClass();

        List<Method> exportaveis =  Arrays.stream(clazz.getMethods())
                .filter(m -> m.isAnnotationPresent(Exportavel.class))
                .collect(Collectors.toList());

        Map<String, Object> attr = new HashMap<>();

        for (Method m : exportaveis) {
            Exportavel annotationdata = m.getAnnotation(Exportavel.class);
            try {
                if (annotationdata.object()) {
                    Map<String, Object> values = mapearValoresDoDado(clazz.getMethod(m.getName()).invoke(item));
                    values.forEach(attr::put);
                    titulos.addAll(values.keySet());
                } else {
                    attr.put(annotationdata.titulo(), clazz.getMethod(m.getName()).invoke(item));
                    titulos.add(annotationdata.titulo());
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return attr;
    }
}
