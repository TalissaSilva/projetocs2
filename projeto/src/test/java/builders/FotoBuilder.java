package builders;

import morabem.domain.Foto;

public class FotoBuilder {

    private Foto foto;

    private FotoBuilder() {}

    public static FotoBuilder obterUma() {
        FotoBuilder builder = new FotoBuilder();
        builder.foto = new Foto(1L, "foo url");
        return builder;
    }

    public FotoBuilder deId(Long id) {
        foto.setId(id);
        return this;
    }

    public Foto agora() {
        return foto;
    }
}
