package br.luizalabs.desafio.logistica.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Range;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FileUtilsTest {

    @InjectMocks
    private FileUtils fileUtils;


    @Test
    public void shouldReturnValueOfAStringCastToLong(){
        assertEquals(123L, fileUtils.extractLong("123 teste", Range.closed(0, 3)));
    }

    @Test
    public void shouldReturnValueOfAStringCastToBigdDecimal(){
        assertEquals(new BigDecimal(123), fileUtils.extractBigDecimal("123 teste", Range.closed(0, 3)));
    }

    @Test
    public void shouldReturnValueOfAStringCastToDate(){
        LocalDate date = LocalDate.parse("20240502", DateTimeFormatter.ofPattern("yyyyMMdd"));
        assertEquals(date, fileUtils.extractDate("20240502 teste", Range.closed(0, 8)));
    }

    @Test
    public void shouldReturnValueOfAString(){
        assertEquals("teste", fileUtils.extractString("20240502 teste", Range.closed(9, 14)));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenRangeExcedsLineSize(){
        assertThrows(IllegalArgumentException.class, () ->fileUtils.extractString("20240502 teste", Range.closed(9, 100)));
    }

}
