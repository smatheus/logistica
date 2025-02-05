package br.luizalabs.desafio.logistica.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/datasets/user-orders.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetOrdersOfUsers() throws Exception {
        mockMvc.perform(get("/user/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    public void shouldGetOrdersOfUsersByOrderId() throws Exception {
        mockMvc.perform(get("/user/order").param("orderId", "753"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].userId").value(80));
    }

    @Test
    public void shouldGetOrdersOfUsersByDateRange() throws Exception {
        mockMvc.perform(get("/user/order")
                        .param("startDate", "2021-09-08")
                        .param("endDate", "2021-09-10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].userId").value(88));
    }

    @Test
    public void shouldReturnEmptyWhenHaveNoOrderInRangeDate() throws Exception {
        mockMvc.perform(get("/user/order")
                        .param("startDate", "2024-09-08")
                        .param("endDate", "2024-09-10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(0));
    }

}
