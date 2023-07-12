package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.types.TransferenciaFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public List<Transferencia> findAll(TransferenciaFilterType filter){
        return transferenciaRepository.findAll(
                filter.getNumeroConta(),
                filter.getNomeOperadorTransacao(),
                filter.getDataInicio(),
                filter.getDataFim(),
                filter.getDataExata()
        );
    }
}
