package by.ita.je.service;

import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.Fridge;
import by.ita.je.webDto.FridgeWebDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FridgeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FridgeMapper fridgeMapper;

    @MockBean
    FridgeWebService fridgeWebService;

    @Test
    public void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/fridge/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create.html"))
                .andExpect(model().attributeExists("fridge"));
    }

    @Test
    public void testCreateFridge() throws Exception {
        Mockito.when(fridgeMapper.toWebDto(any())).thenReturn(FridgeWebDto.builder().build());
        when(fridgeWebService.createFridgeDto(Fridge.builder().build())).thenReturn(Fridge.builder().build());
        mockMvc.perform(post("/fridge/create")
                        .param("type", "Test EntityFridge")
                        .param("description", "Test Description"))
                .andExpect(status().isOk())
                .andExpect(view().name("success.html"))
                .andExpect(model().attributeExists("fridge"));
    }

    @Test
    public void testShowSearchForm() throws Exception {
        mockMvc.perform(get("/fridge/read"))
                .andExpect(status().isOk())
                .andExpect(view().name("search.html"));
    }

    @Test
    public void testReadFridge() throws Exception {
        Mockito.when(fridgeMapper.toWebDto(any())).thenReturn(FridgeWebDto.builder().build());
        Mockito.when(fridgeWebService.readFridge(any())).thenReturn(Fridge.builder().build());
        mockMvc.perform(post("/fridge/read")
                        .param("number", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("searchResults.html"))
                .andExpect(model().attributeExists("fridge"));
    }

    @Test
    public void testShowAddForm() throws Exception {
        mockMvc.perform(get("/fridge/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add.html"));
    }

    @Test
    public void testAddFridge() throws Exception {
        Mockito.when(fridgeMapper.toWebDto(any())).thenReturn(FridgeWebDto.builder().build());
        Mockito.when(fridgeWebService.addKettleAndMulticooker(any(), eq(Fridge.builder().build()))).thenReturn(Fridge.builder().build());
        String requestBody = "{\\\"kettleWebDto\\\": {\\\"type\\\": \\\"steel\\\"}";
        mockMvc.perform(post("/fridge/add")
                        .param("number", "1")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(view().name("addSuccess.html"))
                .andExpect(model().attributeExists("fridge"));

    }

    @Test
    public void testShowConditionalForm() throws Exception {
        mockMvc.perform(get("/fridge/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("conditional.html"));
    }

    @Test
    public void testUpdateByConditionalFridge() throws Exception {
        Mockito.when(fridgeMapper.toWebDto(any())).thenReturn(FridgeWebDto.builder().build());
        Mockito.when(fridgeWebService.findFridgeAndUpdateByConditional(any(),any())).thenReturn(Fridge.builder().build());
        String requestBody = "{{\n" +
                "    \"number\": 1,\n" +
                "    \"parameter\": 3\n" +
                " \n" +
                "}}";
        mockMvc.perform(post("/fridge/update")
                        .param("number", "1")
                        .param("parameter", "3")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(view().name("conditionalresults.html"))
                .andExpect(model().attributeExists("fridge"));
    }

}
