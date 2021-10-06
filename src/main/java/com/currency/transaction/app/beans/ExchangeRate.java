package com.currency.transaction.app.beans;

import lombok.Data;

@Data
public class ExchangeRate {
    private Double mount;
    private Double mountExchange;
    private String sourceIso;
    private String targetIso;
    private Double exchangeRate;
}
