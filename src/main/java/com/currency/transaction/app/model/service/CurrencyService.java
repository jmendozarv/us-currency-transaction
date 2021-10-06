package com.currency.transaction.app.model.service;

import com.currency.transaction.app.beans.ExchangeRate;
import com.currency.transaction.app.model.entity.Currency;


public interface CurrencyService {
    ExchangeRate getExchangeRate(String sourceISO , String targetISO, Double mount);
    void updateCurrency(Currency currency);
}
