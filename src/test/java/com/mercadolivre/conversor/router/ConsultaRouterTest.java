package com.mercadolivre.conversor.router;

import com.mercadolivre.conversor.core.entity.Consulta;
import com.mercadolivre.conversor.core.repository.ConsultasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ConsultaRouterTest {
    @InjectMocks
    private ConsultaRouter consultaRouter;

    @Mock
    private ConsultasRepository consultasRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllConsultasNoContent() {
        // Mock do repository para retornar uma lista vazia
        when(consultasRepository.findAll()).thenReturn(new ArrayList<>());

        // Chamada ao método
        ResponseEntity<List<Consulta>> response = consultaRouter.getAllConsultas();

        // Verifica se o status da resposta é NO_CONTENT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        assert (response.getHeaders().isEmpty());
    }


    @Test
    void testGetAllConsultasOk() {
        // Cria um Mock para o teste
        List<Consulta> consultas = new ArrayList<>();
        consultas.add(Consulta.builder()
                .id(1L)
                .moedaDestino("USD")
                .moedaOrigem("BRL")
                .valorOriginal(10.0)
                .valorConvertido(20.0)
                .build());


        when(consultasRepository.findAll()).thenReturn(consultas);

        // Chamada ao método
        ResponseEntity<List<Consulta>> response = consultaRouter.getAllConsultas();

        // Verifica os resultados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consultas, response.getBody());
        verify(consultasRepository, times(1)).findAll();
        assertEquals(1, response.getBody().get(0).getId());
    }


    @Test
    void testRealizarConsulta() {
        //TODO

       // when(ConsultaRouter.restTemplate(any(),any())).thenReturn(responseApi);

        //CHAMADA
        //ResponseEntity response = consultaRouter.realizarConsulta("USD", "BRL", 10.0);

        //VERIFICAÇÃO
        //assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(50.0, ((Consulta) Objects.requireNonNull(response.getBody())).getValorConvertido());


    }


}