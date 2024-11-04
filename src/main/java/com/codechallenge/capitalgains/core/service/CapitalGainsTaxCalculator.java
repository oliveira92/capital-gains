package com.codechallenge.capitalgains.core.service;

import com.codechallenge.capitalgains.core.domain.Operation;
import com.codechallenge.capitalgains.core.domain.OperationType;
import com.codechallenge.capitalgains.core.domain.Tax;

import java.util.ArrayList;
import java.util.List;

public class CapitalGainsTaxCalculator implements TaxCalculationStrategy{

    private double averagePrice = 0.0;
    private int totalQuantity = 0;
    private double accumulatedLoss = 0.0;

    @Override
    public List<Tax> calculateTaxes(List<Operation> operations) {
        List<Tax> taxes = new ArrayList<>();

        for (Operation op : operations) {
            double tax = 0.0;

            if (OperationType.BUY.getDescription().equalsIgnoreCase(op.getOperation())) {
                updateAveragePrice(op.getUnitCost(), op.getQuantity());
                tax = 0.0;

            } else if (OperationType.SELL.getDescription().equalsIgnoreCase(op.getOperation())) {
                tax = calculateSellTax(op.getUnitCost(), op.getQuantity());
            }

            taxes.add(Tax.builder().tax(tax).build());
        }

        return taxes;
    }

    private void updateAveragePrice(double unitCost, int quantity) {
        double totalCost = (averagePrice * totalQuantity) + (unitCost * quantity);
        totalQuantity += quantity;
        averagePrice = totalCost / totalQuantity;
    }

    private double calculateSellTax(double sellPrice, int sellQuantity) {
        double totalSellValue = sellPrice * sellQuantity;

        if (totalSellValue <= 20000) {
            return 0.0;
        }

        double profit = (sellPrice - averagePrice) * sellQuantity;

        if (profit > 0) {
            double taxableProfit = Math.max(0, profit - accumulatedLoss);
            accumulatedLoss = Math.max(0, accumulatedLoss - profit);
            return taxableProfit * 0.20;
        } else {
            accumulatedLoss += Math.abs(profit);
            return 0.0;
        }
    }

}
