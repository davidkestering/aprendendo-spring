package com.javanauta.aprendendospring.controller;

import com.javanauta.aprendendospring.business.services.TipoContatoService;
import com.javanauta.aprendendospring.infrastructure.entity.TipoContato;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-contato")
@RequiredArgsConstructor
public class TipoContatoController {

    private final TipoContatoService tipoContatoService;

    @PostMapping
    public ResponseEntity<TipoContato> salvaTipoContato(@RequestBody TipoContato tipoContato) {
        return ResponseEntity.ok(tipoContatoService.salvaTipoContato(tipoContato));
    }
}
