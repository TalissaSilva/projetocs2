package builders;

import org.springframework.mock.web.MockHttpSession;

public class SessionBuilder {

    private MockHttpSession session;

    private SessionBuilder() {}

    public static SessionBuilder obterUma() {
        SessionBuilder b = new SessionBuilder();
        b.session = new MockHttpSession();
        return b;
    }

    public SessionBuilder comOParamentro(String nome, Object value) {
        session.setAttribute(nome, value);
        return this;
    }

    public MockHttpSession agora() {
        return session;
    }
}
