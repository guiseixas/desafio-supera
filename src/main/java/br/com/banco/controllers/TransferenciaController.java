package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import br.com.banco.types.TransferenciaFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @CrossOrigin
    @GetMapping
    public List<Transferencia> findAll(TransferenciaFilterType filter){
        return transferenciaService.findAll(filter);
    }
}
