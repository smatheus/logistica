package br.luizalabs.desafio.logistica.utils;

import org.springframework.data.domain.Range;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static String extractSubstring(String line, Range<Integer> range) {
        int start = range.getLowerBound().getValue().orElse(0);
        int end = range.getUpperBound().getValue().orElse(line.length());

        if (start < 0 || end > line.length() || start >= end) {
            throw new IllegalArgumentException("Invalid range: [" + start + ", " + end + "]");
        }

        return line.substring(start, end).trim();
    }

    public Long extractLong(String line, Range<Integer> range) {
        return Long.parseLong(extractSubstring(line, range));
    }

    public String extractString(String line, Range<Integer> range) {
        return extractSubstring(line, range);
    }

    public BigDecimal extractBigDecimal(String line, Range<Integer> range) {
        return new BigDecimal(extractSubstring(line, range));
    }

    public LocalDate extractDate(String line, Range<Integer> range) {
        return LocalDate.parse(extractSubstring(line, range), DATE_FORMATTER);
    }
}
