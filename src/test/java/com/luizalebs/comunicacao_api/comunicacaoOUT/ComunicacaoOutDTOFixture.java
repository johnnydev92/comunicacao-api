package com.luizalebs.comunicacao_api.comunicacaoOUT;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;

public class ComunicacaoOutDTOFixture {

    public static ComunicacaoOutDTO build(String nomeDestinatario,
                                         String emailDestinatario,
                                         String telefoneDestinatario,
                                         String mensagem,
                                         ModoEnvioEnum modoDeEnvio,
                                         StatusEnvioEnum statusEnvio){

        return new ComunicacaoOutDTO(nomeDestinatario, emailDestinatario, telefoneDestinatario, mensagem,
                modoDeEnvio, statusEnvio);
    }
}
