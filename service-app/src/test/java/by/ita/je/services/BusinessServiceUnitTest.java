package by.ita.je.services;

import by.ita.je.dto.to_data_base.FridgeDto;
import by.ita.je.mapper.FridgeMapper;
import by.ita.je.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceUnitTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FridgeMapper fridgeMapper;

    @InjectMocks
    private BusinessService businessService;


    @Test
    void create() {
        String urlFridgeCreate = "http://nginx/database/fridge/save";

        Fridge testfridge = Fridge.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .energy(null)
                .registered(null)
                .Multicookers(Collections.emptyList())
                .kettle(null)
                .build();

        FridgeDto testfridgeDto = FridgeDto.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .multicookerDtos(Collections.emptyList())
                .kettleDto(null)
                .build();
        when(fridgeMapper.toDataBaseDto(testfridge)).thenReturn(testfridgeDto);

        when(fridgeMapper.toEntityFromDataBase(restTemplate.postForObject(
                        eq(urlFridgeCreate),
                        eq(testfridgeDto),
                        eq(FridgeDto.class)
                ))
        ).thenReturn(testfridge);

        Fridge actual = businessService.create(testfridge);
        assertEquals(actual, testfridge);

        verify(fridgeMapper, new Times(1)).toDataBaseDto(testfridge);

        verify(restTemplate, new Times(1)).postForObject(
                eq(urlFridgeCreate),
                eq(testfridgeDto),
                eq(FridgeDto.class)
        );
    }

    @Test
    void create_then_trows_RestClientException() {
        String urlFridgeCreate = "http://nginx/database/fridge/save";

        Fridge testfridge = Fridge.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .energy(null)
                .registered(null)
                .Multicookers(Collections.emptyList())
                .kettle(null)
                .build();

        FridgeDto testfridgeDto = FridgeDto.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .multicookerDtos(Collections.emptyList())
                .kettleDto(null)
                .build();

        when(fridgeMapper.toDataBaseDto(testfridge)).thenReturn(testfridgeDto);

        when(fridgeMapper.toEntityFromDataBase(restTemplate.postForObject(
                        eq(urlFridgeCreate),
                        eq(testfridgeDto),
                        eq(FridgeDto.class)
                ))
        ).thenThrow(new RestClientException("") {
        });

        Assertions.assertThrows(RestClientException.class, () -> businessService.create(testfridge));

        verify(fridgeMapper, new Times(1)).toDataBaseDto(testfridge);
        verify(restTemplate, new Times(1)).postForObject(
                eq(urlFridgeCreate),
                eq(testfridgeDto),
                eq(FridgeDto.class)
        );
    }

    @Test
    void findFridgePlusKettleAndMulticooker() {
        String urlFridgeReade = "http://nginx/database/fridge/read/1";
        String urlFridgeUpdate = "http://nginx/database/fridge/update/?number=1";

        Integer number = 1;
        Kettle kettle = Kettle.builder()
                .type("glass")
                .color("blue")
                .isElectric(false)
                .isInduction(false)
                .price(BigDecimal.valueOf(30.33))
                .energy('B')
                .registered(ZonedDateTime.parse("2023-12-31T14:11:54+02"))
                .build();

        Multicooker multicooker = Multicooker.builder()
                .type("Midea")
                .description("So so")
                .isTouchScreen(true)
                .numberModes(91)
                .price(BigDecimal.valueOf(1112))
                .energy('A')
                .registered(ZonedDateTime.parse("2019-11-29T19:19:54+02"))
                .build();
        List<Multicooker> multicookers = new ArrayList<>();
        multicookers.add(multicooker);

        Fridge testfridge = Fridge.builder()
                .Multicookers(multicookers)
                .kettle(kettle)
                .build();

        Fridge testFridgeFromDataBase = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .number(1)
                .Multicookers(Collections.emptyList())
                .kettle(null)
                .build();

        Fridge testFridgeUpdate = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .number(1)
                .Multicookers(multicookers)
                .kettle(kettle)
                .build();

        when(fridgeMapper.toEntityFromDataBase(restTemplate.getForObject(
                        eq(urlFridgeReade),
                        eq(FridgeDto.class)
                ))
        ).thenReturn(testFridgeFromDataBase);

        Fridge actual = businessService.findFridgePlusKettleAndMulticooker(number, testfridge);
        assertEquals(actual, testFridgeUpdate);

        verify(restTemplate, new Times(1)).getForObject(
                eq(urlFridgeReade),
                eq(FridgeDto.class)
        );

        verify(restTemplate, new Times(1))
                .exchange(urlFridgeUpdate,
                        HttpMethod.PUT,
                        new HttpEntity<>(testFridgeFromDataBase),
                        Fridge.class
                );
    }

    @Test
    void findFridgePlusKettleAndMulticooker_then_trows_RestClientException() {

        String urlFridgeReade = "http://nginx/database/fridge/read/1";

        Kettle kettle = Kettle.builder()
                .type("glass")
                .color("blue")
                .isElectric(false)
                .isInduction(false)
                .price(BigDecimal.valueOf(30.33))
                .energy('B')
                .registered(ZonedDateTime.parse("2023-12-31T14:11:54+02"))
                .build();

        Multicooker multicooker = Multicooker.builder()
                .type("Midea")
                .description("So so")
                .isTouchScreen(true)
                .numberModes(91)
                .price(BigDecimal.valueOf(1112))
                .energy('A')
                .registered(ZonedDateTime.parse("2019-11-29T19:19:54+02"))
                .build();
        List<Multicooker> multicookers = new ArrayList<>();
        multicookers.add(multicooker);

        Fridge testfridge = Fridge.builder()
                .Multicookers(multicookers)
                .kettle(kettle)
                .build();

        when(restTemplate.getForObject(
                        eq(urlFridgeReade),
                        eq(FridgeDto.class)
                )
        ).thenThrow(new RestClientException(""));

        Assertions.assertThrows(RestClientException.class, () -> businessService.findFridgePlusKettleAndMulticooker(1, testfridge));

        verify(restTemplate, new Times(1)).getForObject(
                eq(urlFridgeReade),
                eq(FridgeDto.class)
        );

        verify(restTemplate, new Times(0))
                .exchange("http://nginx/database/fridge/update/?number=" + testfridge.getNumber(),
                        HttpMethod.PUT,
                        new HttpEntity<>(testfridge),
                        Fridge.class
                );
    }
    @Test
    void findFridgePlusTVAndCountryByConditional() {
        String urlFridgeReade = "http://nginx/database/fridge/read/1";
        String urlFridgeUpdate = "http://nginx/database/fridge/update/?number=1";


        Fridge testfridge = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .kettle(new Kettle())
                .Multicookers(null)
                .number(1)
                .build();
        TV tv = TV.builder()
                .price(testfridge.getPrice())
                .build();
        List<TV> tvList = new ArrayList<>();
        tvList.add(tv);
        Country country = Country.BELARUS;

        testfridge.getKettle().setListTV(tvList);
        testfridge.getKettle().getListTV().get(0).setCountry(country);

        when(fridgeMapper.toEntityFromDataBase(restTemplate.getForObject(
                        eq(urlFridgeReade),
                        eq(FridgeDto.class)
                ))
        ).thenReturn(testfridge);

        Fridge actual = businessService.findFridgePlusTVAndCountryByConditional(testfridge.getNumber(), 54);
        assertNotNull(actual.getKettle().getListTV());
        assertNotNull(actual.getKettle().getListTV().get(0).getCountry());

        assertEquals(actual.getKettle().getListTV().get(0).getCountry(), testfridge.getKettle().getListTV().get(0).getCountry());

        verify(restTemplate, new Times(1)).getForObject(
                eq(urlFridgeReade),
                eq(FridgeDto.class)
        );

        verify(restTemplate, new Times(1))
                .exchange(urlFridgeUpdate,
                        HttpMethod.PUT,
                        new HttpEntity<>(testfridge),
                        Fridge.class
                );
    }

    @Test
    void findFridgePlusTVAndCountryByConditional_then_trows_RestClientException() {
        String urlFridgeReade = "http://nginx/database/fridge/read/1";
        String urlFridgeUpdate = "http://nginx/database/fridge/update/?number=1";

        Fridge testfridge = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .kettle(new Kettle())
                .Multicookers(null)
                .number(1)
                .build();
        TV tv = TV.builder()
                .price(testfridge.getPrice())
                .build();
        List<TV> tvList = new ArrayList<>();
        tvList.add(tv);
        Country country = Country.BELARUS;

        testfridge.getKettle().setListTV(tvList);
        testfridge.getKettle().getListTV().get(0).setCountry(country);

        when(restTemplate.getForObject(
                        eq(urlFridgeReade),
                        eq(FridgeDto.class)
                )
        ).thenThrow(new RestClientException(""));

        assertThrows(RestClientException.class, () -> businessService.findFridgePlusTVAndCountryByConditional(testfridge.getNumber(), 98));

        verify(restTemplate, new Times(1)).getForObject(
                eq(urlFridgeReade),
                eq(FridgeDto.class)
        );

        verify(restTemplate, new Times(0))
                .exchange(urlFridgeUpdate,
                        HttpMethod.PUT,
                        new HttpEntity<>(testfridge),
                        Fridge.class
                );
    }

    @Test
    void findFridge() {
        String urlFridgeReade = "http://nginx/database/fridge/read/1";

        Kettle kettle = Kettle.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150.1))
                .energy('B')
                .registered(ZonedDateTime.parse("2024-01-01T20:23:54+02"))
                .build();

        Fridge testfridge = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .kettle(kettle)
                .Multicookers(Collections.emptyList())
                .number(1)
                .build();

        when(fridgeMapper.toEntityFromDataBase(restTemplate.getForObject(
                        eq(urlFridgeReade),
                        eq(FridgeDto.class)
                ))
        ).thenReturn(testfridge);

        Fridge actual = businessService.findFridge(1);
        assertEquals(actual, testfridge);

        verify(restTemplate, new Times(1)).getForObject(
                eq(urlFridgeReade),
                eq(FridgeDto.class)
        );
    }

    @Test
    void findFridgeAndDelete() {
        String urlFridgeReade = "http://nginx/database/fridge/read/1";

        String urlFridgeDelete = "http://nginx/database/fridge/delete/?number=1";

        Fridge testfridge = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .number(1)
                .build();

        when(fridgeMapper.toEntityFromDataBase(restTemplate.getForObject(
                        eq(urlFridgeReade),
                        eq(FridgeDto.class)
                ))
        ).thenReturn(testfridge);

        Fridge actual = businessService.findFridgeAndDelete(testfridge.getNumber());
        assertEquals(actual, testfridge);

        verify(restTemplate, new Times(1)).getForObject(
                eq(urlFridgeReade),
                eq(FridgeDto.class)
        );
        verify(restTemplate, new Times(0))
                .delete(urlFridgeDelete);
    }
}