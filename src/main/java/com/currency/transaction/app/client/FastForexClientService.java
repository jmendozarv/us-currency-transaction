package com.currency.transaction.app.client;

import com.currency.transaction.app.model.entity.Currency;

import java.util.List;

public interface FastForexClientService {
    List<Currency> fetchListSupportedCurrencies();
}
