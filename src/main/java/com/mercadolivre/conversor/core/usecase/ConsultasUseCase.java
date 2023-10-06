package com.mercadolivre.conversor.core.usecase;

import com.mercadolivre.conversor.core.dto.ResponseExchangeApiDto;
import com.mercadolivre.conversor.core.entity.Consulta;

import java.util.List;

public interface ConsultasUseCase {


    public List<Consulta> getAllConsultas();

    public Consulta createExchangeTransaction(String from, String to, Double valor);

    ResponseExchangeApiDto getExchangeTransactionFromApi(String from, String to);
}
