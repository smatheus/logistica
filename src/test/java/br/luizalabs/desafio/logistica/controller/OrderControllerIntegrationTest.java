package br.luizalabs.desafio.logistica.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldTransformFileToEntities() throws Exception {
        String fileContent =
                "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308\n" +
                        "0000000075                                  Bobbie Batz00000007980000000002     1578.5720211116\n" +
                        "0000000049                               Ken Wintheiser00000005230000000003      586.7420210903\n" +
                        "0000000014                                 Clelia Hills00000001460000000001      673.4920211125\n" +
                        "0000000057                          Elidia Gulgowski IV00000006200000000000     1417.2520210919\n" +
                        "0000000080                                 Tabitha Kuhn00000008770000000003      817.1320210612\n" +
                        "0000000080                                 Tabitha Kuhn00000008780000000003      817.1320210612\n" +
                        "0000000023                                  Logan Lynch00000002530000000002      322.1220210523";

        MockMultipartFile file = new MockMultipartFile(
                "file", "order.txt", MediaType.TEXT_PLAIN_VALUE, fileContent.getBytes());

        mockMvc.perform(multipart("/order")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated()).andExpect(content().string("File processed successfully"));
    }


    @Test
    public void shouldFailTransformWhenFileIsEmpty() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "order.txt", MediaType.TEXT_PLAIN_VALUE, new byte[0]);

        mockMvc.perform(multipart("/order") // Usando multipart()
                        .file(file) // Enviando o arquivo
                        .contentType(MediaType.MULTIPART_FORM_DATA)) // Define o tipo form-data
                .andExpect(status().isInternalServerError()).andExpect(content().string("File is empty")); // Espera resposta 201 Created
    }

}
