package com.codechallenge.capitalgains.core.usecase;

import com.codechallenge.capitalgains.core.domain.Operation;
import com.codechallenge.capitalgains.core.domain.Tax;
import com.codechallenge.capitalgains.core.service.TaxCalculationStrategy;

import java.util.List;

public class CalculateTaxUseCase {

    private final TaxCalculationStrategy strategy;

    public CalculateTaxUseCase(TaxCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Tax> execute(List<Operation> operations) {
        return strategy.calculateTaxes(operations);
    }

}
