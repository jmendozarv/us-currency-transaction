package com.currency.transaction.app.rest;

import com.currency.transaction.app.beans.ExchangeRate;
import com.currency.transaction.app.client.FastForexClientService;
import com.currency.transaction.app.model.entity.Currency;
import com.currency.transaction.app.model.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1.0/operation")
public class ExchangeCurrencyRest {

    @Autowired
    private FastForexClientService fastForexClientService;

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/fetch-currencies")
    public List<Currency> listCurrenciesSupported(){
        return fastForexClientService.fetchListSupportedCurrencies();
    }

    @GetMapping("/exchange")
    public ResponseEntity<ExchangeRate> exchangeCurrency(@RequestParam(name = "source_iso") String sourceIso,
                                                         @RequestParam(name="target_iso") String targetIso ,
                                                         @RequestParam(name = "mount") double mount){
        return new ResponseEntity<>(currencyService.getExchangeRate(sourceIso,targetIso,mount), HttpStatus.OK);
    }

    @PostMapping("/updateExchangeRate")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void updateExchangueRate(@RequestBody Currency currency){
        currencyService.updateCurrency(currency);
    }









}
