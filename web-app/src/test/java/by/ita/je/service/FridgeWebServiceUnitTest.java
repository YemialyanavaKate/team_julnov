package by.ita.je.service;

import by.ita.je.mappers.FridgeMapper;
import by.ita.je.webDto.FridgeWebDto;
import by.ita.je.models.Fridge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FridgeWebServiceUnitTest {
    @Mock
    private RestTemplate serviceAppRestClient;
    @Mock
    private FridgeMapper fridgeMapper;
    @InjectMocks
    FridgeWebService fridgeWebService;

    @Test
    void createFridgeDto() {
        String url = "http://127.0.0.1:8111/business/fridge/create";

        FridgeWebDto testFridgeWebDto = FridgeWebDto.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .multicookerWebDtos(Collections.emptyList())
                .kettleWebDto(null)
                .build();

        Fridge testFridge = Fridge.builder()
                .type(testFridgeWebDto.getType())
                .description(testFridgeWebDto.getDescription())
                .discount(testFridgeWebDto.getDiscount())
                .defect(testFridgeWebDto.getDefect())
                .price(testFridgeWebDto.getPrice())
                .Multicookers(Collections.emptyList())
                .kettle(null)
                .build();
        when(fridgeMapper.toEntity(serviceAppRestClient.postForObject(
                eq(url),
                eq(testFridgeWebDto),
                eq(FridgeWebDto.class)
        ))).thenReturn(testFridge);

        Fridge actual = fridgeWebService.createFridgeDto(testFridgeWebDto);
        assertEquals(actual, testFridge);

        verify(serviceAppRestClient, new Times(1)).postForObject(
                eq(url),
                eq(testFridgeWebDto),
                eq(FridgeWebDto.class)
        );
    }
}