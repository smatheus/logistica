package br.luizalabs.desafio.logistica.controller;

import br.luizalabs.desafio.logistica.service.FileTransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private FileTransformationService fileTransformationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void insertOrder(@RequestBody MultipartFile file){
        try {
            File tempFile = File.createTempFile("upload-" + file.getName(), ".txt");
            file.transferTo(tempFile);

            fileTransformationService.insertOrders(tempFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
