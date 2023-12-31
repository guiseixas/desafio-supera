package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import br.com.banco.types.TransferenciaFilterType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> findAll(TransferenciaFilterType filter){
        log.info("Buscando todas as transferências por: {}", filter);
        List<Transferencia> transferencias = transferenciaService.findAll(filter);
        if (transferencias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum dado de transferência foi encontrado.");
        } else {
            return ResponseEntity.ok(transferencias);
        }
    }
}
