package com.example.expensetracker.service;

import com.example.expensetracker.client.CurrencyClient;
import com.example.expensetracker.dto.CurrencyRateDto;
import com.example.expensetracker.entity.CurrencyRate;
import com.example.expensetracker.mapper.CurrencyRateMapper;
import com.example.expensetracker.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRateMapper currencyRateMapper;
    private final CurrencyClient currencyClient;

    @Value("${api.key}")
    private String apiKey;

    public CurrencyRateService(CurrencyRateRepository currencyRateRepository,
                               CurrencyRateMapper currencyRateMapper,
                               CurrencyClient currencyClient) {
        this.currencyRateRepository = currencyRateRepository;
        this.currencyRateMapper = currencyRateMapper;
        this.currencyClient = currencyClient;
    }

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
        LocalDate today = LocalDate.now();
        String currencyPair = fromCurrency + "/" + toCurrency;

        // Попробуем получить курс из базы данных
        Optional<CurrencyRate> optionalRate = currencyRateRepository.findByCurrencyPairAndDate(currencyPair, today);
        if (optionalRate.isPresent()) {
            return optionalRate.get().getRate();
        }

        // Если данных за текущий день нет, запрашиваем у внешнего API
        Map<String, Object> response = currencyClient.getExchangeRate("CURRENCY_EXCHANGE_RATE", fromCurrency, toCurrency, apiKey);

        Object rateObject = response.get("Realtime Currency Exchange Rate");
        if (rateObject instanceof Map) {
            Map<?, ?> rateMap = (Map<?, ?>) rateObject;
            Object exchangeRateObj = rateMap.get("5. Exchange Rate");
            if (exchangeRateObj instanceof String) {
                BigDecimal exchangeRate = new BigDecimal((String) exchangeRateObj);

                // Сохранение полученного курса в базе данных
                CurrencyRate currencyRate = new CurrencyRate();
                currencyRate.setCurrencyPair(currencyPair);
                currencyRate.setRate(exchangeRate);
                currencyRate.setDate(today);
                currencyRateRepository.save(currencyRate);

                return exchangeRate;
            }
        }

        // Если курс не найден, попробуем получить последний доступный курс
        Optional<CurrencyRate> lastAvailableRate = currencyRateRepository.findByCurrencyPairAndDate(currencyPair, today.minusDays(1));
        if (lastAvailableRate.isPresent()) {
            return lastAvailableRate.get().getRate();
        }

        // Если не удалось получить курс, выбрасываем исключение
        throw new RuntimeException("Failed to retrieve exchange rate for " + currencyPair);
    }

    public List<CurrencyRateDto> getAllCurrencyRates() {
        return currencyRateRepository.findAll().stream()
                .map(currencyRateMapper::toDto)
                .collect(Collectors.toList());
    }
}
