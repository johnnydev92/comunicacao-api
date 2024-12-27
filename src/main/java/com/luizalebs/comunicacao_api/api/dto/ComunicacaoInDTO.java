package com.luizalebs.comunicacao_api.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ComunicacaoInDTO implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataHoraEnvio;
    private String nomeDestinatario;
    private String emailDestinatario;
    private String telefoneDestinatario;
    private String mensagem;
    private ModoEnvioEnum modoDeEnvio;
    @JsonIgnore
    private StatusEnvioEnum statusEnvio;

    public ComunicacaoInDTO
            (String nomeDestinatario, String emailDestinatario, String telefoneDestinatario, String mensagem, ModoEnvioEnum modoDeEnvio, StatusEnvioEnum statusEnvio) {
    }
}
