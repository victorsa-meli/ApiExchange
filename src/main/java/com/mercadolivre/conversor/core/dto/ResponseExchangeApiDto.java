package com.mercadolivre.conversor.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseExchangeApiDto {

    //@SerializedName("USDBRL")
    @JsonProperty("USDBRL")
    private BidDto usdBrl;
}
