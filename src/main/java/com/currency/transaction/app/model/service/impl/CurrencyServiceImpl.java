package com.currency.transaction.app.model.service.impl;

import com.currency.transaction.app.beans.ExchangeRate;
import com.currency.transaction.app.beans.response.MessageClientResponse;
import com.currency.transaction.app.exception.CustomException;
import com.currency.transaction.app.model.entity.Currency;
import com.currency.transaction.app.model.repository.CurrencyRepository;
import com.currency.transaction.app.model.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public ExchangeRate getExchangeRate(String sourceISO, String targetISO, Double mount) {
        Optional<Currency> optionalCurrencySource = currencyRepository.findById(sourceISO);
        Optional<Currency> optionalCurrencyTarget = currencyRepository.findById(targetISO);
        if (!optionalCurrencySource.isPresent() || !optionalCurrencyTarget.isPresent()) {
            MessageClientResponse messageClientResponse = new MessageClientResponse();
            messageClientResponse.setCode("00");
            messageClientResponse.setMessage(String.format( "No was code found ISO %s or  %s  " , sourceISO, targetISO));
            throw new CustomException(messageClientResponse);
        }
        Currency sourceCurrency = optionalCurrencySource.get();
        Currency targetCurrency = optionalCurrencyTarget.get();
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setMount(mount);
        exchangeRate.setSourceIso(String.format("%s - %s", sourceCurrency.getIso(), sourceCurrency.getDescription()));
        exchangeRate.setTargetIso(String.format("%s - %s", targetCurrency.getIso(), targetCurrency.getDescription()));
        exchangeRate.setMountExchange(new BigDecimal((mount / sourceCurrency.getExchange()) * targetCurrency.getExchange()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        exchangeRate.setExchangeRate(new BigDecimal((1 / sourceCurrency.getExchange()) * targetCurrency.getExchange()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return exchangeRate;
    }

    @Override
    public void updateCurrency(Currency currencyRequest) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyRequest.getIso());
        if(!optionalCurrency.isPresent()){
            MessageClientResponse messageClientResponse = new MessageClientResponse();
            messageClientResponse.setCode("00");
            messageClientResponse.setMessage(String.format( "Iso not found %s " , currencyRequest.getIso()));
            throw new CustomException(messageClientResponse);
        }
        Currency currency = optionalCurrency.get();
        currency.setExchange(currencyRequest.getExchange());
        currencyRepository.save(currency);
    }
}
