package builders;

import morabem.domain.Atributo;

public class AtributoBuilder {

    private Atributo atributo;

    private AtributoBuilder() {
    }

    public static AtributoBuilder obterUm() {
        AtributoBuilder builder = new AtributoBuilder();
        builder.atributo = new Atributo("foo");
        return builder;
    }

    public Atributo agora() {
        return atributo;
    }

    public AtributoBuilder comId(Integer l) {
        atributo.setId(l);
        return this;
    }
}
