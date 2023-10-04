package com.mercadolivre.conversor.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseExchangeApiDto {


    @JsonProperty("USDBRL")
    private BidDto usdBrl;

    @JsonProperty("BRLUSD")
    private BidDto brlUsd;
}
