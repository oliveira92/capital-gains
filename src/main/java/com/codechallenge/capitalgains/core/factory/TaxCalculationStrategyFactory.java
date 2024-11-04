package com.codechallenge.capitalgains.core.factory;

import com.codechallenge.capitalgains.core.service.CapitalGainsTaxCalculator;
import com.codechallenge.capitalgains.core.service.TaxCalculationStrategy;

public class TaxCalculationStrategyFactory {
    public static TaxCalculationStrategy getStrategy(String type) {
        if ("capitalGains".equalsIgnoreCase(type)) {
            return new CapitalGainsTaxCalculator();
        }
        throw new IllegalArgumentException("Tipo de estratégia desconhecido: " + type);
    }
}