package by.ita.je.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FridgeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/fridge/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create.html"))
                .andExpect(model().attributeExists("fridge"));
    }

    @Test
    public void testCreateFridge() throws Exception {
        mockMvc.perform(post("/fridge/create")
                        .param("type", "Test EntityFridge")
                        .param("description", "Test Description"))
                .andExpect(status().isOk())
                .andExpect(view().name("success.html"))
                .andExpect(model().attributeExists("fridge"));
    }
}
