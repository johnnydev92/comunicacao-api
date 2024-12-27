package com.luizalebs.comunicacao_api.comunicacaoIN;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;


public class ComunicacaoInDTOFixture {

    public static ComunicacaoInDTO build(String nomeDestinatario,
                                         String emailDestinatario,
                                         String telefoneDestinatario,
                                         String mensagem,
                                         ModoEnvioEnum modoDeEnvio,
                                         StatusEnvioEnum statusEnvio){

        return new ComunicacaoInDTO(nomeDestinatario, emailDestinatario, telefoneDestinatario, mensagem,
                modoDeEnvio, statusEnvio);
    }

}
