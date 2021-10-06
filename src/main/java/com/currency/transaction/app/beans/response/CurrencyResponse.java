package com.currency.transaction.app.beans.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class CurrencyResponse {
    private HashMap<String,String> currencies;
}
