package morabem.controllers;

import builders.ImovelBuilder;
import builders.RequestBuilder;
import builders.SessionBuilder;
import builders.UsuarioBuilder;
import morabem.services.ImovelService;
import morabem.storage.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ImovelControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ImovelService imovelService;

    @MockBean
    private StorageService storageService;

    @Test
    public void formSubmit_sucesso() throws Exception {

        Mockito.doNothing().when(imovelService).salvar(Mockito.any());
        mockMvc.perform(RequestBuilder.postPara("/cadastro/imovel")
                .comOsParamentros(ImovelBuilder.obterUm().comoParametros()).agora()
                .session(SessionBuilder.obterUma().agora())
                .sessionAttr("usuarioLogado", UsuarioBuilder.PessoaFisica.obterUm().agora())
                .param("foto64[]", "data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDYwIDYwIiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA2MCA2MDsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSIxNnB4IiBoZWlnaHQ9IjE2cHgiPgo8Zz4KCTxwYXRoIGQ9Ik04LDIyYy00LjQxMSwwLTgsMy41ODktOCw4czMuNTg5LDgsOCw4czgtMy41ODksOC04UzEyLjQxMSwyMiw4LDIyeiBNOCwzNmMtMy4zMDksMC02LTIuNjkxLTYtNnMyLjY5MS02LDYtNnM2LDIuNjkxLDYsNiAgIFMxMS4zMDksMzYsOCwzNnoiIGZpbGw9IiMwMDAwMDAiLz4KCTxwYXRoIGQ9Ik01MiwyMmMtNC40MTEsMC04LDMuNTg5LTgsOHMzLjU4OSw4LDgsOHM4LTMuNTg5LDgtOFM1Ni40MTEsMjIsNTIsMjJ6IE01MiwzNmMtMy4zMDksMC02LTIuNjkxLTYtNnMyLjY5MS02LDYtNiAgIHM2LDIuNjkxLDYsNlM1NS4zMDksMzYsNTIsMzZ6IiBmaWxsPSIjMDAwMDAwIi8+Cgk8cGF0aCBkPSJNMzAsMjJjLTQuNDExLDAtOCwzLjU4OS04LDhzMy41ODksOCw4LDhzOC0zLjU4OSw4LThTMzQuNDExLDIyLDMwLDIyeiBNMzAsMzZjLTMuMzA5LDAtNi0yLjY5MS02LTZzMi42OTEtNiw2LTYgICBzNiwyLjY5MSw2LDZTMzMuMzA5LDM2LDMwLDM2eiIgZmlsbD0iIzAwMDAwMCIvPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+Cjwvc3ZnPgo=")
/*
                .param("foto64[]", "data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTguMS4xLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDIyIDIyIiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCAyMiAyMjsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSIxNnB4IiBoZWlnaHQ9IjE2cHgiPgo8Zz4KCTxnPgoJCTxwYXRoIGQ9Ik0xMSwwQzQuOTI2LDAsMCw0LjkyNiwwLDExczQuOTI2LDExLDExLDExczExLTQuOTI2LDExLTExUzE3LjA3NCwwLDExLDB6IiBmaWxsPSIjMDAwMDAwIi8+Cgk8L2c+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPC9zdmc+Cg==")
*/
        ).andDo(print());

        Mockito.verify(imovelService, Mockito.times(1)).salvar(Mockito.any());
    }


}