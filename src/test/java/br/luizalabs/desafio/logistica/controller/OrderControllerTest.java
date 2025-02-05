package br.luizalabs.desafio.logistica.controller;

import br.luizalabs.desafio.logistica.exception.FileIsEmptyException;
import br.luizalabs.desafio.logistica.service.FileTransformationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private FileTransformationService fileTransformationService;

    @Mock
    private Logger logger;

    @Test
    public void shoudCallFileTransformationServiceToProcessFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "file", "order.txt", MediaType.TEXT_PLAIN_VALUE, new byte[0]);
        orderController.insertOrder(file);
        verify(fileTransformationService, times(1)).insertOrders(any(File.class));
    }

    @Test
    public void shoudLoggerAndFailWhenFileTransformationFails() throws IOException {
        try {
            doThrow(new FileIsEmptyException("File is Empty")).when(fileTransformationService).insertOrders(any(File.class));
            MockMultipartFile file = new MockMultipartFile(
                    "file", "order.txt", MediaType.TEXT_PLAIN_VALUE, new byte[0]);
            orderController.insertOrder(file);
        }catch(Exception e) {
            verify(logger, times(1)).error(e.getMessage(), e);
        }
    }

}
