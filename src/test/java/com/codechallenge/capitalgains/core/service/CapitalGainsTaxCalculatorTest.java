package com.codechallenge.capitalgains.core.service;

import com.codechallenge.capitalgains.core.domain.Operation;
import com.codechallenge.capitalgains.core.domain.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalGainsTaxCalculatorTest {

    private CapitalGainsTaxCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new CapitalGainsTaxCalculator();
    }

    @Test
    public void testBuyOperation() {
        List<Operation> operations = List.of(
                Operation.builder().operation("buy").unitCost(10.0).quantity(10000).build()
        );
        List<Tax> taxes = calculator.calculateTaxes(operations);
        assertEquals(1, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax(), "No tax should be applied on buy operations");
    }

    @Test
    public void testSellOperationWithProfit() {
        List<Operation> operations = List.of(
                Operation.builder().operation("buy").unitCost(10.0).quantity(10000).build(),
                Operation.builder().operation("sell").unitCost(20.0).quantity(5000).build()
        );
        List<Tax> taxes = calculator.calculateTaxes(operations);
        assertEquals(2, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax(), "No tax on buy operation");
        assertEquals(10000.0, taxes.get(1).getTax(), "Tax should be 20% on profit of 50000");
    }

    @Test
    public void testSellOperationWithLoss() {
        List<Operation> operations = List.of(
                Operation.builder().operation("buy").unitCost(10.0).quantity(1000).build(),
                Operation.builder().operation("sell").unitCost(5.0).quantity(5000).build()
        );
        List<Tax> taxes = calculator.calculateTaxes(operations);
        assertEquals(2, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax(), "No tax on buy operation");
        assertEquals(0.0, taxes.get(1).getTax(), "No tax on loss; should accumulate loss of 25000");
    }

    @Test
    public void testSellOperationWithTotalSellValueBelow20000() {
        List<Operation> operations = List.of(
                Operation.builder().operation("buy").unitCost(10.0).quantity(1000).build(),
                Operation.builder().operation("sell").unitCost(15.0).quantity(1000).build()
        );
        List<Tax> taxes = calculator.calculateTaxes(operations);
        assertEquals(2, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax(), "No tax on buy operation");
        assertEquals(0.0, taxes.get(1).getTax(), "No tax for sell operations with total sell value below 20000");
    }

    @Test
    public void testAccumulatedLossDeduction() {
        List<Operation> operations = List.of(
                Operation.builder().operation("buy").unitCost(10.0).quantity(10000).build(),
                Operation.builder().operation("sell").unitCost(5.0).quantity(5000).build(),
                Operation.builder().operation("sell").unitCost(20.0).quantity(3000).build()
        );
        List<Tax> taxes = calculator.calculateTaxes(operations);
        assertEquals(3, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax(), "No tax on buy operation");
        assertEquals(0.0, taxes.get(1).getTax(), "No tax on loss operation");
        assertEquals(1000.0, taxes.get(2).getTax(), "Tax should be 20% on remaining profit of 5000 after loss deduction");
    }

}