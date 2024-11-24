package by.ita.je.services;

import by.ita.je.dto.to_data_base.FridgeDto;
import by.ita.je.mapper.FridgeMapper;
import by.ita.je.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class BusinessService {
    public static final String HOST_URL = "http://nginx/database";
    public static final String ROOT_FRIDGE = "/fridge";
    public static final String METOD_READ = "/read";
    public static final String METOD_SAVE = "/save";
    public static final String METOD_UPDATE = "/update";
    public static final String METOD_DELETE = "/delete";

    private final RestTemplate restTemplate;
    private final FridgeMapper fridgeMapper;

    public Fridge create(Fridge fridge) {
        String urlFridgeCreate = buildUrl(ROOT_FRIDGE, METOD_SAVE);
        FridgeDto fridgeDto = fridgeMapper.toDataBaseDto(fridge);

        return fridgeMapper.toEntityFromDataBase(restTemplate.postForObject(urlFridgeCreate, fridgeDto, FridgeDto.class));
    }

    public Fridge findFridgePlusKettleAndMulticooker(Integer number, Fridge fridge) {
        Kettle kettle = fridge.getKettle();
        List<Multicooker> multicookers = fridge.getMulticookers()
                .stream()
                .toList();

        String url = buildUrl(ROOT_FRIDGE, METOD_READ) + "/" + number;

        Fridge fridgeFromDataBase = fridgeMapper.toEntityFromDataBase(restTemplate.getForObject(url, FridgeDto.class));

        fridgeFromDataBase.setKettle(kettle);
        fridgeFromDataBase.setMulticookers(multicookers);

        HttpEntity<Fridge> httpEntity = new HttpEntity<>(fridgeFromDataBase);
        restTemplate.exchange(buildUrl(ROOT_FRIDGE, METOD_UPDATE) + "/?number=" + number,
                HttpMethod.PUT,
                httpEntity,
                Fridge.class);
        return fridgeFromDataBase;
    }

    public Fridge findFridgePlusTVAndCountryByConditional(Integer number, Integer parameter) {

        Predicate<Integer> predicate = i -> (i < parameter);
        String url = buildUrl(ROOT_FRIDGE, METOD_READ) + "/" + number;

        Fridge fridge = fridgeMapper.toEntityFromDataBase(restTemplate.getForObject(url, FridgeDto.class));

        if (fridge != null && predicate.test(fridge.getNumber())) {
            TV tv = TV.builder()
                    .price(fridge.getPrice())
                    .build();
            List<TV> tvList = new ArrayList<>();
            tvList.add(tv);
            fridge.getKettle().setListTV(tvList);
            fridge.getKettle().getListTV().get(0).setCountry(Country.BELARUS);

            HttpEntity<Fridge> httpEntity = new HttpEntity<>(fridge);
            restTemplate.exchange(buildUrl(ROOT_FRIDGE, METOD_UPDATE) + "/?number=" + number, HttpMethod.PUT, httpEntity, Fridge.class);
        }
        return fridge;
    }

    public Fridge findFridge(Integer number) throws HttpServerErrorException.InternalServerError {

        String url = buildUrl(ROOT_FRIDGE, METOD_READ) + "/" + number;

        return fridgeMapper.toEntityFromDataBase(restTemplate.getForObject(url, FridgeDto.class));

    }

    public Fridge findFridgeAndDelete(Integer number) {
        String url = buildUrl(ROOT_FRIDGE, METOD_READ) + "/" + number;

        FridgeDto fridgeDto = restTemplate.getForObject(url, FridgeDto.class);

        if (fridgeDto != null) {
            restTemplate.delete(buildUrl(ROOT_FRIDGE, METOD_DELETE) + "/?number=" + number);
        }
        return fridgeMapper.toEntityFromDataBase(fridgeDto);
    }

    private String buildUrl(String root, String method) {
        return HOST_URL.concat(root).concat(method);
    }
}
