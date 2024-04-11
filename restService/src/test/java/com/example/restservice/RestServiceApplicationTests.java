package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@WebMvcTest
@WebAppConfiguration
public class RestServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSPSchedule() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/schedule/time/" + 1)).andReturn();
        var status = result.getResponse().getStatus();
        assertEquals(200, status);

        var content = result.getResponse().getContentAsString();
        assertEquals("Monday - 08:00 - 10:00", content);
    }

}
