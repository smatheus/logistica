package br.luizalabs.desafio.logistica.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Long extractLong(String line, int start, int end) {
        return Long.parseLong(line.substring(start, end).trim());
    }

    public String extractString(String line, int start, int end) {
        return line.substring(start, end).trim();
    }

    public BigDecimal extractBigDecimal(String line, int start, int end) {
        return new BigDecimal(line.substring(start, end).trim());
    }

    public LocalDate extractDate(String line, int start, int end) {
        return LocalDate.parse(line.substring(start, end).trim(), DATE_FORMATTER);
    }
}
