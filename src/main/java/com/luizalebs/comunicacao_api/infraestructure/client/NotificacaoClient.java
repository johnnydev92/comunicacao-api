package com.luizalebs.comunicacao_api.infraestructure.client;


import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {

    @PostMapping
    void enviarEmail(@RequestBody ComunicacaoOutDTO dto);
}
