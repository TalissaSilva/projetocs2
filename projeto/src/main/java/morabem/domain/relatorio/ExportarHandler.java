package morabem.domain.relatorio;

import morabem.domain.relatorio.Exportar.Componente;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static morabem.domain.relatorio.Exportar.Componente.DADOS;

public interface ExportarHandler<T, R> {

    Map<Componente, Object> componentes = new HashMap<>();
    Set<String> titulos = new HashSet<>();

    R gerar();
    void start();
    void inserir(Componente componente);

    default R exportar(List<Componente> componentes) {

        if (componentes.contains(DADOS)) {

            List<T> dados = (List<T>) ((List<T>) this.componentes.get(DADOS)).stream()
                    .map(this::mapearValoresDoDado)
                    .collect(Collectors.toList());

            this.componentes.put(DADOS, dados);
        }

        start();
        componentes.stream().forEach(this::inserir);
        return gerar();
    }

    default ExportarHandler<T, R> setComponentes(Map<Componente, Object> componentes) {
        this.componentes.clear();
        this.componentes.putAll(componentes);
        return this;
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
