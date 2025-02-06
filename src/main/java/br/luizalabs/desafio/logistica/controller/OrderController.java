package br.luizalabs.desafio.logistica.controller;

import br.luizalabs.desafio.logistica.exception.FileIsEmptyException;
import br.luizalabs.desafio.logistica.service.FileTransformationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> insertOrder(@RequestBody(description = "Arquivo a ser enviado", content = @Content(mediaType = "multipart/form-data", schema = @Schema(type = "string", format = "binary")))
                                              @RequestParam("file") MultipartFile file) {
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
