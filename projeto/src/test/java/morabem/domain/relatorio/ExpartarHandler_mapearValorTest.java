package morabem.domain.relatorio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ExpartarHandler_mapearValorTest {

    @Parameterized.Parameter(value = 0)
    public Object object;

    @Parameterized.Parameter(value = 1)
    public String name;

    @Parameterized.Parameter(value = 2)
    public Object expectedValue;

    private ExportarHandler<String, String> handler;

    @Before
    public void setup() {
        handler = new ExportarHandler<String, String>() {
            @Override
            public String gerar() {
                return null;
            }

            @Override
            public String formatarItem(Map<String, Object> item) {
                return null;
            }
        };
    }

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> getParams() {
        return Arrays.asList(new Object[][] {
                { new AnyString(), "String", "foo return" },
                { new AnyInt(), "int", 1 },
                { new AnyChar(), "char", 'A' },
                { new AnyDouble(), "doble", 1d },
                { new Anybool(), "boolean", true },
        });
    }

    @Test
    public void testarMapeamentoDeValores() {
        Map<String, Object> dados =  handler.mapearValoresDoDado(object);
        assertThat(dados.get("foo"), is(equalTo(expectedValue)));
    }

    public static class AnyString {
        @Exportavel(titulo = "foo") public String foo() { return "foo return"; }
    }

    public static class AnyInt {
        @Exportavel(titulo = "foo") public int foo() { return 1; }
    }

    public static class AnyDouble {
        @Exportavel(titulo = "foo") public double foo() { return 1d; }
    }

    public static class AnyChar {
        @Exportavel(titulo = "foo") public char foo() { return 'A'; }
    }

    public static class Anybool {
        @Exportavel(titulo = "foo") public boolean foo() { return true; }
    }

    public static class AnyList {
        @Exportavel(titulo = "foo")
        public List<String> list () {
            return Arrays.asList("item 1", "item 2");
        }
    }

    public static class AnyArray {
        @Exportavel(titulo = "foo")
        public String[] array () {
            return new String[] {"item 1", "item 2"};
        }
    }
}
