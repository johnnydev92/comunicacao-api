package com.luizalebs.comunicacao_api.controller;


import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comunicacao")
@Tag(name = "comunicacao", description = "agenda, busca, cancela e deleta")
public class ComunicacaoController {

    private final ComunicacaoService service;

    public ComunicacaoController(ComunicacaoService service) {
        this.service = service;
    }

    @PostMapping("/agendar")
    @Operation(summary = "agendar um evento", description = "Cria um novo evento")
    @ApiResponse(responseCode = "200", description = "Evento criado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Requisição inválida! Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Error de servidor.")
    public ResponseEntity<ComunicacaoOutDTO> agendar(@RequestBody ComunicacaoInDTO dto)  {
        return ResponseEntity.ok(service.agendarComunicacao(dto));
    }

    @GetMapping()
    @Operation(summary = "Busca um evento", description = "busca um evento específico")
    @ApiResponse(responseCode = "200", description = "Evento encontrado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Requisição inválida! Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Error de servidor.")
    public ResponseEntity<ComunicacaoOutDTO> buscarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.buscarStatusComunicacao(emailDestinatario));
    }

    @PatchMapping("/cancelar")
    @Operation(summary = "Cancela um evento", description = "cancela um evento específico")
    @ApiResponse(responseCode = "200", description = "Evento criado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Requisição inválida! Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Error de servidor.")
    public ResponseEntity<ComunicacaoOutDTO> cancelarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.alterarStatusComunicacao(emailDestinatario));
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "agendar um evento", description = "Cria um novo evento")
    @ApiResponse(responseCode = "200", description = "Evento criado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Requisição inválida! Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Error de servidor.")
    public ResponseEntity<Void> deletaUmEvento(@PathVariable String emailDestinatario){

        service.deletaComunicaoPorEmail(emailDestinatario);
        return ResponseEntity.ok().build();

    }
}
