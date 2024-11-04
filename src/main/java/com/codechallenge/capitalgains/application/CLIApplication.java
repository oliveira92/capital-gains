package com.codechallenge.capitalgains.application;

import com.codechallenge.capitalgains.core.domain.Operation;
import com.codechallenge.capitalgains.core.domain.Tax;
import com.codechallenge.capitalgains.core.service.CapitalGainsTaxCalculator;
import com.codechallenge.capitalgains.core.usecase.CalculateTaxUseCase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@SpringBootApplication
public class CLIApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CLIApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ObjectMapper mapper = new ObjectMapper();

        boolean insideBrackets = false;
        StringBuilder jsonBlock = new StringBuilder();
        List<String> allResults = new ArrayList<>();

        System.out.println("Insira as operações de ganho de capital (pressione Enter duas vezes para processar):");

        String line;
        while (true) {
            line = reader.readLine();

            // Se o usuário inserir uma linha em branco, processa todos os blocos acumulados
            if (line == null || line.trim().isEmpty()) {
                // Se há um bloco acumulado, processa o JSON
                if (jsonBlock.length() > 0) {
                    String result = processJsonBlock(jsonBlock.toString().trim(), mapper);
                    allResults.add(result);
                    jsonBlock.setLength(0);
                }

                // Exibe todos os resultados processados de uma vez
                System.out.println("\nResultados:");
                for (String result : allResults) {
                    System.out.println(result);
                }
                allResults.clear(); // Limpa os resultados após exibi-los

                System.out.println("\nInsira o próximo conjunto de operações ou pressione Ctrl+C para sair:");
                continue;
            }

            // Detecta o início e fim de um bloco JSON
            if (line.contains("[")) {
                insideBrackets = true;
            }

            if (insideBrackets) {
                jsonBlock.append(line).append(" ");
            }

            if (line.contains("]")) {
                insideBrackets = false;
                // Processa o bloco JSON completo
                String result = processJsonBlock(jsonBlock.toString().trim(), mapper);
                allResults.add(result);
                jsonBlock.setLength(0);
            }
        }
    }

    private String processJsonBlock(String jsonBlock, ObjectMapper mapper) {
        try {
            String cleanedJson = removeNewLinesInsideBrackets(jsonBlock);

            CalculateTaxUseCase calculateTaxUseCase = new CalculateTaxUseCase(new CapitalGainsTaxCalculator());

            List<Operation> operations = mapper.readValue(cleanedJson, new TypeReference<List<Operation>>() {});

            List<Tax> taxes = calculateTaxUseCase.execute(operations);

            // Retorna o resultado formatado em JSON para acumular no output final
            return mapper.writeValueAsString(taxes);
        } catch (Exception e) {
            System.err.println("Erro ao processar o bloco JSON: " + e.getMessage());
            return "Erro ao processar o bloco JSON";
        }
    }

    private String removeNewLinesInsideBrackets(String json) {
        var pattern = Pattern.compile("\\[(.*?)\\]", Pattern.DOTALL);
        var matcher = pattern.matcher(json);

        var result = new StringBuffer();

        while (matcher.find()) {
            var cleanedContent = matcher.group(1)
                    .replaceAll("\\r?\\n+", " ")
                    .replaceAll("\\s+", " ")
                    .trim();
            matcher.appendReplacement(result, "[" + cleanedContent + "]");
        }
        matcher.appendTail(result);

        return result.toString();
    }

}
