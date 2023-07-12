package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query("SELECT DISTINCT t FROM Transferencia t " +
            "WHERE (:numeroConta IS NULL OR t.conta.id = :numeroConta) " +
            "AND (:nomeOperadorTransacao IS NULL OR LOWER(t.nomeOperadorTransacao) LIKE CONCAT('%', LOWER(CAST(:nomeOperadorTransacao AS text)), '%')) " +
            "AND (:dataInicio IS NULL OR :dataFim IS NULL OR CAST(t.dataTransferencia AS LocalDate) >= :dataInicio AND CAST(t.dataTransferencia AS LocalDate) <= :dataFim) " +
            "AND (:dataExata IS NULL OR CAST(t.dataTransferencia AS LocalDate) = :dataExata) ")
    List<Transferencia> findAll(
            @Param("numeroConta") Long numeroConta,
            @Param("nomeOperadorTransacao") String nomeOperadorTransacao,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("dataExata") LocalDate dataExata
    );





}
