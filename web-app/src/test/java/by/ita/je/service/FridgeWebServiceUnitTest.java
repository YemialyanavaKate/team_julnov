package by.ita.je.service;

import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.*;
import by.ita.je.webDto.FridgeWebDto;
import by.ita.je.webDto.KettleWebDto;
import by.ita.je.webDto.MulticookerWebDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
        String url = "http://service-app:8111/business/fridge/create";

        Fridge testFridge = Fridge.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .build();

        FridgeWebDto testFridgeWebDto = FridgeWebDto.builder()
                .type(testFridge.getType())
                .description(testFridge.getDescription())
                .discount(testFridge.getDiscount())
                .defect(testFridge.getDefect())
                .price(testFridge.getPrice())
                .build();
        when(fridgeMapper.toWebDto(testFridge)).thenReturn(testFridgeWebDto);

        when(serviceAppRestClient.postForObject(
                eq(url),
                eq(testFridgeWebDto),
                eq(FridgeWebDto.class)
        )).thenReturn(testFridgeWebDto);

        when(fridgeMapper.toEntity(testFridgeWebDto)).thenReturn(testFridge);

        Fridge actual = fridgeWebService.createFridgeDto(testFridge);
        assertEquals(actual, testFridge);

        verify(serviceAppRestClient, new Times(1)).postForObject(
                eq(url),
                eq(testFridgeWebDto),
                eq(FridgeWebDto.class)
        );
    }

    @Test
    void readFridge() {
        String url = "http://service-app:8111/business/fridge/read/1";
        KettleWebDto kettleWebDto = KettleWebDto.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150.1))
                .build();

        Kettle kettle = Kettle.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150.1))
                .build();

        FridgeWebDto testFridgeWebDto = FridgeWebDto.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2000.5))
                .multicookerWebDtos(Collections.emptyList())
                .kettleWebDto(kettleWebDto)
                .build();

        Fridge testFridge = Fridge.builder()
                .type(testFridgeWebDto.getType())
                .description(testFridgeWebDto.getDescription())
                .discount(testFridgeWebDto.getDiscount())
                .defect(testFridgeWebDto.getDefect())
                .price(testFridgeWebDto.getPrice())
                .Multicookers(Collections.emptyList())
                .kettle(kettle)
                .build();

        when(serviceAppRestClient.getForObject(
                eq(url),
                eq(FridgeWebDto.class))
        ).thenReturn(testFridgeWebDto);

        when(fridgeMapper.toEntity(testFridgeWebDto)).thenReturn(testFridge);

        Fridge actual = fridgeWebService.readFridge(1);
        assertEquals(actual, testFridge);

        verify(serviceAppRestClient, new Times(1)).getForObject(
                eq(url),
                eq(FridgeWebDto.class)
        );
    }

    @Test
    void addKettleAndMulticooker() {
        String urlFridgeUpdate = "http://service-app:8111/business/fridge/update?number=1";
        Integer number = 1;
        List<Multicooker> multicookers = new ArrayList<>();
        Multicooker multicooker = Multicooker.builder().type("NewMulticooker").build();
        multicookers.add(multicooker);

        Fridge fridge = Fridge.builder()
                .kettle(Kettle.builder().type("NewKettle").build())
                .Multicookers(multicookers).build();

        Fridge testFridgeWithEntity = Fridge.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .Multicookers(fridge.getMulticookers())
                .kettle(fridge.getKettle())
                .build();

        List<MulticookerWebDto> multicookersWebDto = new ArrayList<>();
        MulticookerWebDto multicookerWebDto = MulticookerWebDto.builder().type("NewMulticooker").build();
        multicookersWebDto.add(multicookerWebDto);

        KettleWebDto kettleWebDto = KettleWebDto.builder().type("NewKettle").build();

        FridgeWebDto testFridgeWithEntityWebDto = FridgeWebDto.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .multicookerWebDtos(multicookersWebDto)
                .kettleWebDto(kettleWebDto)
                .build();

        FridgeWebDto fridgeWebDto = FridgeWebDto.builder()
                .kettleWebDto(KettleWebDto.builder().type("NewKettle").build())
                .multicookerWebDtos(multicookersWebDto).build();

        when(fridgeMapper.toWebDto(fridge)).thenReturn(fridgeWebDto);
        when(serviceAppRestClient.postForObject(
                eq(urlFridgeUpdate),
                eq(fridgeWebDto),
                eq(FridgeWebDto.class)
        )).thenReturn(testFridgeWithEntityWebDto);
        when(fridgeMapper.toEntity(testFridgeWithEntityWebDto)).thenReturn(testFridgeWithEntity);

        Fridge actual =
                fridgeWebService.addKettleAndMulticooker(number, fridge);
        assertEquals(actual, testFridgeWithEntity);
        verify(serviceAppRestClient, new Times(1)).postForObject(
                eq(urlFridgeUpdate),
                eq(fridgeWebDto),
                eq(FridgeWebDto.class)
        );
    }

    @Test
    void findFridgeAndUpdateByConditional() {
        String urlFridgeUpdate = "http://service-app:8111/business/fridge/update_conditional?number={number}&parameter={parameter}";

        TV tv = TV.builder()
                .type("LED")
                .brand("Samsung")
                .discount(false)
                .diagonal(38)
                .price(BigDecimal.valueOf(4000.99))
                .country(Country.CYPRUS)
                .build();
        List<TV> tvList = new ArrayList<>();
        tvList.add(tv);

        Kettle kettle = Kettle.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150.1))
                .listTV(tvList)
                .build();

        Fridge testFridgeFromDataBase = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2000.5))
                .Multicookers(null)
                .kettle(kettle)
                .build();

        KettleWebDto kettleWebDto = KettleWebDto.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150.1))
                .build();

        FridgeWebDto testFridgeFromDataBaseWebDto = FridgeWebDto.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2000.5))
                .multicookerWebDtos(null)
                .kettleWebDto(kettleWebDto)
                .build();

        TV tvByConditional = TV.builder()
                .type("LED")
                .brand("Samsung")
                .discount(false)
                .diagonal(38)
                .price(testFridgeFromDataBase.getPrice())
                .country(Country.BELARUS)
                .build();
        List<TV> tvListByConditional = new ArrayList<>();
        tvList.add(tvByConditional);

        Fridge testFridgeByConditional = Fridge.builder()
                .type(testFridgeFromDataBase.getType())
                .description(testFridgeFromDataBase.getDescription())
                .discount(testFridgeFromDataBase.getDiscount())
                .defect(testFridgeFromDataBase.getDefect())
                .price(testFridgeFromDataBase.getPrice())
                .Multicookers(testFridgeFromDataBase.getMulticookers())
                .kettle(testFridgeFromDataBase.getKettle())
                .build();
        testFridgeByConditional.getKettle().setListTV(tvListByConditional);

        FridgeWebDto testFridgeByConditionalWebDto = FridgeWebDto.builder()
                .type(testFridgeFromDataBase.getType())
                .description(testFridgeFromDataBase.getDescription())
                .discount(testFridgeFromDataBase.getDiscount())
                .defect(testFridgeFromDataBase.getDefect())
                .price(testFridgeFromDataBase.getPrice())
                .multicookerWebDtos(null)
                .kettleWebDto(kettleWebDto)
                .build();

        String url = "http://service-app:8111/business/fridge/read/1";

        when(serviceAppRestClient.getForObject(
                eq(url),
                eq(FridgeWebDto.class))
        ).thenReturn(testFridgeFromDataBaseWebDto);
        when(serviceAppRestClient.postForObject(
                eq(urlFridgeUpdate),
                eq(testFridgeFromDataBaseWebDto),
                eq(FridgeWebDto.class),
                eq(1),
                eq(3)
        )).thenReturn(testFridgeByConditionalWebDto);

        when(fridgeMapper.toEntity(testFridgeByConditionalWebDto)).thenReturn(testFridgeByConditional);

        Fridge actual = fridgeWebService.findFridgeAndUpdateByConditional(1, 3);
        assertEquals(actual, testFridgeByConditional);
        verify(serviceAppRestClient, new Times(1)).postForObject(
                eq(urlFridgeUpdate),
                eq(testFridgeFromDataBaseWebDto),
                eq(FridgeWebDto.class),
                eq(1),
                eq(3)
        );
    }

    @Test
    void deleteFridge() {
        String url = "http://service-app:8111/business/fridge/delete/1";

        fridgeWebService.deleteFridge(1);
        assertNull(fridgeWebService.readFridge(1));

        verify(serviceAppRestClient, new Times(1)).delete(eq(url));
    }
}