package com.codechallenge.capitalgains.core.service;

import com.codechallenge.capitalgains.core.domain.Operation;
import com.codechallenge.capitalgains.core.domain.Tax;

import java.util.List;

public interface TaxCalculationStrategy {

    List<Tax> calculateTaxes(List<Operation> operations);

}
