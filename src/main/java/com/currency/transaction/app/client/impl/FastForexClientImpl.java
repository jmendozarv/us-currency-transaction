package com.currency.transaction.app.client.impl;

import com.currency.transaction.app.client.FastForexClientService;
import com.currency.transaction.app.config.client.RestTemplateClient;
import com.currency.transaction.app.model.entity.Currency;
import com.currency.transaction.app.beans.response.CurrencyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class FastForexClientImpl implements FastForexClientService {

    @Value("${client.extern.api-fastforex.url}")
    private String apiUrl;

    @Value("${client.extern.api-fastforex.token}")
    private String token;

    @Autowired
    private RestTemplateClient restTemplateClient;


    @Override
    public List<Currency> fetchListSupportedCurrencies() {
        ResponseEntity<CurrencyResponse> responseEntity;
        HashMap parameters = new HashMap();
        try{
            List<Currency> currencies  = new ArrayList<>();
            parameters.put("token",token);
            String url = apiUrl+"/currencies?api_key={token}";
            responseEntity = restTemplateClient.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,CurrencyResponse.class,parameters);
            CurrencyResponse currencyResponse =  responseEntity.getBody();
            currencyResponse.getCurrencies().forEach((iso,description)->{
                Currency currency = new Currency();
                currency.setIso(iso);
                currency.setDescription(description);
                currencies.add(currency);
            });
            return currencies;
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
        return null;
    }

}
