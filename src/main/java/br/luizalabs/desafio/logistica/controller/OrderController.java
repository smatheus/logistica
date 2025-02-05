package br.luizalabs.desafio.logistica.controller;

import br.luizalabs.desafio.logistica.exception.FileIsEmptyException;
import br.luizalabs.desafio.logistica.service.FileTransformationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private FileTransformationService fileTransformationService;

    @PostMapping
    public ResponseEntity<String> insertOrder(@RequestBody MultipartFile file){
        try {
            File tempFile = File.createTempFile("upload-" + file.getName(), ".txt");
            file.transferTo(tempFile);

            fileTransformationService.insertOrders(tempFile);
            return ResponseEntity.created(URI.create("/order")).body("File processed successfully");
        } catch (IOException | FileIsEmptyException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
