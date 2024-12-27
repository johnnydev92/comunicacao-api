package com.luizalebs.comunicacao_api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import com.luizalebs.comunicacao_api.comunicacaoIN.ComunicacaoInDTOFixture;
import com.luizalebs.comunicacao_api.comunicacaoOUT.ComunicacaoOutDTOFixture;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ControllerComunicacaoApiTest {

    @InjectMocks
    ComunicacaoController comunicacaoController;

    @Mock
    ComunicacaoService comunicacaoService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String url;

    private ComunicacaoOutDTO comunicacaoOutDTO;

    private ComunicacaoInDTO comunicacaoInDTO;

    private String json;

    @BeforeEach
    void setup() throws JsonProcessingException{

        mockMvc = MockMvcBuilders.standaloneSetup(comunicacaoController).alwaysDo
                (print()).build();

        url = "/user";

        comunicacaoInDTO = ComunicacaoInDTOFixture.build("Jose",
                                                            "jose@gmail.com",
                                                            "98980804141",
                                                            "Ola, bem-vindo",
                                                            ModoEnvioEnum.EMAIL,
                                                                StatusEnvioEnum.PENDENTE);

        comunicacaoOutDTO = ComunicacaoOutDTOFixture.build("Jose",
                "jose@gmail.com",
                "98980804141",
                "Ola, bem-vindo",
                ModoEnvioEnum.EMAIL,
                StatusEnvioEnum.PENDENTE);


    }

    @Test
    void deveAgendarComunicacaoComSucesso() throws Exception {

        when(comunicacaoService.agendarComunicacao(comunicacaoInDTO)).thenReturn(comunicacaoOutDTO);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(comunicacaoService).agendarComunicacao(comunicacaoInDTO);
        verifyNoMoreInteractions(comunicacaoService);

    }

    @Test
    void naoDeveAgendarComunicacaoComJsonNull() throws Exception {

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verifyNoInteractions(comunicacaoService);

    }

    @Test
    void deveBuscarStatusComunicacaoComSucesso() throws Exception {

        when(comunicacaoService.buscarStatusComunicacao("jose@gmail.com"))
                .thenReturn(comunicacaoOutDTO);

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(comunicacaoService).buscarStatusComunicacao("jose@gmail.com");
        verifyNoMoreInteractions(comunicacaoService);

    }

    @Test
    void naoDeveBuscarStatusComunicacaoComSucessoJsonNull () throws Exception {


        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(comunicacaoService);

    }

    @Test
    void deveAlterarStatusComunicacaoComSucesso() throws Exception{

        when(comunicacaoService.alterarStatusComunicacao("jose@gmail.com"))
                .thenReturn(comunicacaoOutDTO);

        mockMvc.perform(patch(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(comunicacaoService).alterarStatusComunicacao("jose@gmail.com");
        verifyNoMoreInteractions(comunicacaoService);
    }

    @Test
    void naoDeveAlterarStatusComunicacaoJsonNull () throws Exception{


        mockMvc.perform(patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(comunicacaoService);
    }

    @Test
    void deveDeletarComunicaoPorEmailComSucesso() throws Exception{

        doNothing().when(comunicacaoService).deletaComunicaoPorEmail("jose@gmail.com");


        mockMvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(comunicacaoService).deletaComunicaoPorEmail("jose@gmail.com");
        verifyNoMoreInteractions(comunicacaoService);
    }

    @Test
    void naoDeveDeletarComunicaoPorEmailComSucesso() throws Exception{

        mockMvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(comunicacaoService);
    }





}
