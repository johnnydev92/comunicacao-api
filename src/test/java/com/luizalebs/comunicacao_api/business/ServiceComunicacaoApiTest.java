package com.luizalebs.comunicacao_api.business;


import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.converter.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.business.mapper.ComunicacaoMapper;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import com.luizalebs.comunicacao_api.comunicacaoIN.ComunicacaoInDTOFixture;
import com.luizalebs.comunicacao_api.comunicacaoOUT.ComunicacaoOutDTOFixture;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.BusinessException;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceComunicacaoApiTest {

    @InjectMocks
    private ComunicacaoService comunicacaoService;

    @Mock
    private ComunicacaoRepository comunicacaoRepository;

    @Mock
    private ComunicacaoMapper comunicacaoMapper;

    @Mock
    private ComunicacaoConverter comunicacaoConverter;

    ComunicacaoEntity comunicacaoEntity;

    ComunicacaoInDTO comunicacaoInDTO;

    ComunicacaoOutDTO comunicacaoOutDTO;

    String email;

    Date dataHora;

    @Mock
    Clock clock;

    @BeforeEach
    public void setup(){

        dataHora = Date.from(Instant.parse("2024-12-25"));

        comunicacaoEntity = ComunicacaoEntity.builder()
                .dataHoraenvio(dataHora)
                .nomeDestinatario("José de Campos")
                .emailDestinatario("jose@gmail.com")
                .telefoneDestinatario("98992223333")
                .mensagem("Olá, Mundo!")
                .modoDeEnvio(ModoEnvioEnum.EMAIL)
                .statusEnvio(StatusEnvioEnum.PENDENTE)
                .build();

        comunicacaoInDTO = ComunicacaoInDTOFixture.build("José de Campos",
                "jose@gmail.com", "98992223333","Olá, Mundo!"
        , ModoEnvioEnum.EMAIL, StatusEnvioEnum.PENDENTE);

        comunicacaoOutDTO = ComunicacaoOutDTOFixture.build
                ("José de Campos", "jose@gmail.com",
                        "98992223333", "Olá, Mundo!", ModoEnvioEnum.EMAIL,
                        StatusEnvioEnum.PENDENTE);

        email = "jose@gmail.com";


    }

    @Test
    void agendarComunicacaoComSucesso() {

        when(comunicacaoConverter.paraEntity(comunicacaoInDTO)).thenReturn(comunicacaoEntity);

        when(comunicacaoRepository.save(comunicacaoEntity)).thenReturn(comunicacaoEntity);

        when(comunicacaoMapper.paraComunicacaoDTO(comunicacaoEntity)).thenReturn(comunicacaoOutDTO);

        ComunicacaoOutDTO dto = comunicacaoService.agendarComunicacao(comunicacaoInDTO);

        assertEquals(dto, comunicacaoOutDTO);
        verify(comunicacaoConverter).paraEntity(comunicacaoInDTO);
        verify(comunicacaoRepository).save(comunicacaoEntity);
        verify(comunicacaoMapper).paraComunicacaoDTO(comunicacaoEntity);
        verifyNoMoreInteractions(comunicacaoRepository, comunicacaoConverter, comunicacaoMapper);
    }

    @Test
    void naoDeveAgendarComunicacaoCasoComunicaoNull() {

        BusinessException e = assertThrows(BusinessException.class,
                () -> comunicacaoService.agendarComunicacao(null));

        assertThat(e, notNullValue());
        assertThat(e.getMessage(),
                is ("Erro ao agendar evento"));
        assertThat(e.getMessage(), notNullValue());
        assertThat(e.getCause().getMessage(),
                is("Os dados são obrigatórios"));
        verifyNoInteractions(comunicacaoMapper, comunicacaoConverter, comunicacaoRepository);
    }

    @Test
    void deveBuscarStatusComunicacaoComSucesso () {

        when(comunicacaoRepository.findByEmailDestinatario(email)).thenReturn(comunicacaoEntity);
        when(comunicacaoMapper.paraComunicacaoDTO(comunicacaoEntity)).thenReturn(comunicacaoOutDTO);

        ComunicacaoOutDTO dto = comunicacaoService.buscarStatusComunicacao(email);

        verify(comunicacaoRepository).findByEmailDestinatario(email);
        verify(comunicacaoMapper).paraComunicacaoDTO(comunicacaoEntity);
        assertEquals(dto, comunicacaoOutDTO);

    }

    @Test
    void deveGerarExcecaoCasoOcorraErroAoBuscaStatusComunicacao (){

        when(comunicacaoRepository.findByEmailDestinatario(email))
                .thenThrow(new RuntimeException("Falha ao buscar comunicacao"));

        BusinessException e = assertThrows(BusinessException.class, ()->
                comunicacaoService.buscarStatusComunicacao(email));

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), is("Erro ao gravar comunicacao"));
        assertThat(e.getCause().getClass(), is(RuntimeException.class));
        assertThat(e.getCause().getMessage(), is("Falha ao buscar comunicacao"));
        verify(comunicacaoRepository).findByEmailDestinatario(email);
        verifyNoInteractions(comunicacaoMapper);
        verifyNoMoreInteractions(comunicacaoRepository);
    }

    @Test
    void deveRetornarNullCasoComunicacaoNaoEncontrada (){

        when(comunicacaoRepository.findByEmailDestinatario(email)).thenReturn(null);

        ComunicacaoOutDTO dto = comunicacaoService.buscarStatusComunicacao(email);

        assertEquals(dto, null);
        verify(comunicacaoRepository).findByEmailDestinatario(email);
        verifyNoInteractions(comunicacaoMapper);

    }

    @Test
    void deveDeletarComunicacaoComSucesso() {

        doNothing().when(comunicacaoRepository).deleByEmail(email);

        comunicacaoService.deletaComunicaoPorEmail(email);

        verify(comunicacaoRepository).deleByEmail(email);
    }


}
