package com.mercadolivre.conversor.router;

import com.mercadolivre.conversor.core.dto.BidDto;
import com.mercadolivre.conversor.core.dto.ResponseExchangeApiDto;
import com.mercadolivre.conversor.core.entity.Consulta;
import com.mercadolivre.conversor.core.repository.ConsultasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaRouter {

    private static final String URL_BASE = "https://economia.awesomeapi.com.br/json/last/";


    @Autowired
    private ConsultasRepository consultasRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Consulta>> getAllConsultas()  {
        List<Consulta> consultas = consultasRepository.findAll();
        if(consultas.isEmpty())    {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(consultas, HttpStatus.OK);
        }
    }

    @PostMapping("/{from}/{to}")
    public ResponseEntity<String> realizarConsulta(@PathVariable String from,
                                                   @PathVariable String to,
                                                   @RequestParam Double valor) {

        RestTemplate restTemplate = new RestTemplate();
        String url = URL_BASE + from + "-"+to;
        ResponseExchangeApiDto respondeApi = restTemplate.getForObject(url, ResponseExchangeApiDto.class);

        if (respondeApi != null && respondeApi.getUsdBrl().getBid()!= null) {

            double valorConvertido = Double.parseDouble(respondeApi.getUsdBrl().getBid()) * valor;

            Consulta consulta = Consulta.builder()
                    .moedaOrigem(from)
                    .moedaDestino(to)
                    .valorOriginal(valor)
                    .valorConvertido(valorConvertido)
                    .build();

            consultasRepository.save(consulta);

            return new ResponseEntity<>("Endereço salvo com sucesso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nao foi possivel identificar a transacao.", HttpStatus.NOT_FOUND);
        }
    }




}
