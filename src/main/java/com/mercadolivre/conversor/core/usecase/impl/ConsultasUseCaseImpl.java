package com.mercadolivre.conversor.core.usecase.impl;

import com.mercadolivre.conversor.core.dto.ResponseExchangeApiDto;
import com.mercadolivre.conversor.core.entity.Consulta;
import com.mercadolivre.conversor.core.repository.ConsultasRepository;
import com.mercadolivre.conversor.core.usecase.ConsultasUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
@Service
public class ConsultasUseCaseImpl implements ConsultasUseCase {

    static final String URL_BASE = "https://economia.awesomeapi.com.br/json/last/";

    @Autowired
    private ConsultasRepository consultasRepository;

    @Override
    public List<Consulta> getAllConsultas() {

        return consultasRepository.findAll();
    }

    @Override
    public Consulta createExchangeTransaction(String from, String to, Double valor) {

        ResponseExchangeApiDto transaction = getExchangeTransactionFromApi(from, to);

        double valorConvertido = validationTransactionValue(transaction, from, to, valor);

        Consulta consulta = Consulta.builder()
                .moedaOrigem(from)
                .moedaDestino(to)
                .valorOriginal(valor)
                .valorConvertido(valorConvertido)
                .build();

        if(!checkExistTransaction(valor, from , to)){
            consultasRepository.save(consulta);
        }

        return consulta;
    }



    @Override
    public ResponseExchangeApiDto getExchangeTransactionFromApi(String from, String to) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_BASE + from + "-" + to + "," + to + "-" + from;
        return restTemplate.getForObject(url, ResponseExchangeApiDto.class);
    }

    double validationTransactionValue(ResponseExchangeApiDto transaction, String from, String to, Double valor) {

        if (transaction != null) {
            double valorConvertido = 0;
            if (from.equals("USD") && to.equals("BRL")) {
                valorConvertido = Double.parseDouble(transaction.getUsdBrl().getBid()) * valor;
            }
            if (from.equals("BRL") && to.equals("USD")) {
                valorConvertido = Double.parseDouble(transaction.getBrlUsd().getBid()) * valor;
            }

            if(from.equals("EUR") && to.equals("BRL")){
                valorConvertido = Double.parseDouble(transaction.getEurBrl().getBid()) * valor;
            }
            if(from.equals("BRL") && to.equals("EUR")){
                valorConvertido = Double.parseDouble(transaction.getBrlEur().getBid()) * valor;
            }

            if(from.equals("ARS") && to.equals("BRL")){
                valorConvertido = Double.parseDouble(transaction.getArsBrl().getBid()) * valor;
            }
            if(from.equals("BRL") && to.equals("ARS")){
                valorConvertido = Double.parseDouble(transaction.getBrlArs().getBid()) * valor;
            }

            if(from.equals("USD") && to.equals("ARS")){
                valorConvertido = Double.parseDouble(transaction.getUsdArs().getBid()) * valor;
            }
            if(from.equals("ARS") && to.equals("USD")){
                valorConvertido = Double.parseDouble(transaction.getArsUsd().getBid()) * valor;
            }

            if(from.equals("EUR") && to.equals("ARS")){
                valorConvertido = Double.parseDouble(transaction.getEurArs().getBid()) * valor;
            }
            if(from.equals("ARS") && to.equals("EUR")){
                valorConvertido = Double.parseDouble(transaction.getArsEur().getBid()) * valor;
            }

            if(from.equals("EUR") && to.equals("USD")){
                valorConvertido = Double.parseDouble(transaction.getEurUsd().getBid()) * valor;
            }
            if(from.equals("USD") && to.equals("EUR")){
                valorConvertido = Double.parseDouble(transaction.getUsdEur().getBid()) * valor;
            }


            return valorConvertido;
        }
        throw new RuntimeException("Moeda informada nao existente.");
    }

    private boolean checkExistTransaction(Double valor, String from, String to) {
        Optional<Consulta> consultaExistente = consultasRepository.findByMoedaOrigemAndMoedaDestinoAndValorOriginal(from, to, valor);
        if (consultaExistente.isPresent()) {
            return true;
        }
        return false;
    }


}
