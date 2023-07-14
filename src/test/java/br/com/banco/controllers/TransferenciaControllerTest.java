package br.com.banco.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
     * Teste feito para requisições findAll com filtro de número de conta tal que existe transação no banco STATUS OK
     */
    @Test
    void testFindAll_WithNumeroContaOk() throws Exception {
        var numeroConta = "1";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("numeroConta", numeroConta))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*
     * Teste feito para requisições findAll com filtro de número de conta tal que NÃO existe transação no banco STATUS 404
     */
    @Test
    void testFindAll_WithNumeroContaNotFound() throws Exception {
        var numeroConta = "9999";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("numeroConta", numeroConta))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /*
     * Teste feito para requisições findAll com filtro de nome operador da transação que existe no banco STATUS OK
     */
    @Test
    void testFindAll_WithNomeOperadorFilterOk() throws Exception {
        var nomeOperadorTransacaoTeste = "Beltrano";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("nomeOperadorTransacao", nomeOperadorTransacaoTeste))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*
    * Teste feito para requisições findAll com filtro de nome operador da transação que NÃO existe no banco STATUS 404
    */
    @Test
    void testFindAll_WithNomeOperadorFilterNotFound() throws Exception {
        var nomeOperadorTransacaoTeste = "NomeInexistente";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .param("nomeOperadorTransacao", nomeOperadorTransacaoTeste)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /*
    * Teste feito para requisições findAll com filtros de data início e fim
    * para transferências presentes no banco com dataTransferencia no intervalo entre inicio e fim STATUS OK
    */
    @Test
    void testFindAll_DataInicioEFimOk() throws Exception {
        var dataInicio = "2019-01-01";
        var dataFim = "2019-02-03";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dataInicio", dataInicio)
                        .param("dataFim", dataFim))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*
     * Teste feito para requisições findAll com filtros de data início e fim
     * para transferências presentes no banco que NÃO possuem o atributo dataTransferencia no intervalo entre inicio e fim STATUS 404
     */
    @Test
    void testFindAll_DataInicioEFimNotFound() throws Exception {
        var dataInicio = "2023-02-03";
        var dataFim = "2023-02-15";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dataInicio", dataInicio)
                        .param("dataFim", dataFim))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /*
     * Teste feito para requisições findAll com filtros de data exata
     * para transferências presentes no banco com dataTransferencia igual a data passada STATUS OK
     */
    @Test
    void testFindAll_DataTransferenciaExataOk() throws Exception {
        var dataExata = "2019-01-01";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dataExata", dataExata))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*
     * Teste feito para requisições findAll com filtros de data exata
     * para transferências presentes no banco com dataTransferencia não encontrada igual a data passada STATUS 404
     */
    @Test
    void testFindAll_DataTransferenciaExataNotFound() throws Exception {
        var dataExata = "2023-12-12";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dataExata", dataExata))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
