package builders;

import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

public class RequestBuilder {

    private MockHttpServletRequestBuilder reqb;

    private RequestBuilder() {

    }

    public static RequestBuilder postPara(String url) {
        RequestBuilder r = new RequestBuilder();
        r.reqb = MockMvcRequestBuilders.post(url);
        return r;
    }

    public static RequestBuilder getPara(String s) {
        RequestBuilder r = new RequestBuilder();
        r.reqb = MockMvcRequestBuilders.get(s);
        return r;
    }

    public RequestBuilder comOParamentroGET(String param, String data) {
        reqb.param(param, data);
        return this;
    }

    public RequestBuilder comOsParamentros(MultiValueMap<String, String> params) {
        reqb = reqb.params(params);
        return this;
    }

    public MockHttpServletRequestBuilder agora() {
        return reqb;
    }
}

