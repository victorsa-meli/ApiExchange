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

    @JsonProperty("EURBRL")
    private BidDto eurBrl;

    @JsonProperty("BRLEUR")
    private BidDto brlEur;

    @JsonProperty("ARSBRL")
    private BidDto arsBrl;

    @JsonProperty("BRLARS")
    private BidDto brlArs;


    @JsonProperty("USDARS")
    private BidDto usdArs;

    @JsonProperty("ARSUSD")
    private BidDto arsUsd;

    @JsonProperty("EURARS")
    private BidDto eurArs;

    @JsonProperty("ARSEUR")
    private BidDto arsEur;


    @JsonProperty("USDEUR")
    private BidDto usdEur;

    @JsonProperty("EURUSD")
    private BidDto eurUsd;



}
