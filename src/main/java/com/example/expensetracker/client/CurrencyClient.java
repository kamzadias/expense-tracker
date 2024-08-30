package com.example.expensetracker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "currencyClient", url = "https://www.alphavantage.co")
public interface CurrencyClient {

    @GetMapping("/query")
    Map<String, Object> getExchangeRate(@RequestParam("function") String function,
                                        @RequestParam("from_currency") String fromCurrency,
                                        @RequestParam("to_currency") String toCurrency,
                                        @RequestParam("apikey") String apiKey);
}
