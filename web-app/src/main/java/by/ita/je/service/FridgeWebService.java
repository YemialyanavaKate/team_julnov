package by.ita.je.service;

import by.ita.je.webDto.FridgeWebDto;
import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.Fridge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FridgeWebService {
    private final RestTemplate serviceAppRestClient;
    private final FridgeMapper fridgeMapper;

    public static final String HOST_URL = "http://service-app:8111";
    public static final String ROOT_FRIDGE = "/business/fridge";
    public static final String METOD_READ = "/read/";
    public static final String METOD_CREATE = "/create";
    public static final String METOD_UPDATE_CONDITIONAL = "/update_conditional?number={number}&parameter={parameter}";
    public static final String METOD_UPDATE = "/update?number=";
    public static final String METOD_DELETE = "/delete/";


    public Fridge createFridgeDto(Fridge fridge) {
        FridgeWebDto fridgeWebDto = fridgeMapper.toWebDto(fridge);
        String urlFridgeCreate = String.format("%s%s%s", HOST_URL, ROOT_FRIDGE, METOD_CREATE);
        return fridgeMapper.toEntity(serviceAppRestClient.postForObject(urlFridgeCreate, fridgeWebDto, FridgeWebDto.class));
    }

    public Fridge readFridge(Integer number) {
        String urlFridgeRead = String.format("%s%s%s%d", HOST_URL, ROOT_FRIDGE, METOD_READ, number);
        FridgeWebDto fridgeWebDto = serviceAppRestClient.getForObject(urlFridgeRead, FridgeWebDto.class);
        return fridgeMapper.toEntity(fridgeWebDto);
    }

    public Fridge addKettleAndMulticooker(Integer number, Fridge fridge) {
        FridgeWebDto fridgeWebDto = fridgeMapper.toWebDto(fridge);
        String urlFridgeUpdate = String.format("%s%s%s%d", HOST_URL, ROOT_FRIDGE, METOD_UPDATE, number);
        FridgeWebDto fridgeWebDtoWithEntity =
                serviceAppRestClient.postForObject(urlFridgeUpdate, fridgeWebDto, FridgeWebDto.class);
        return fridgeMapper.toEntity(fridgeWebDtoWithEntity);
    }

    public Fridge findFridgeAndUpdateByConditional(Integer number, Integer parameter) {
        String urlFridgeRead = String.format("%s%s%s%d", HOST_URL, ROOT_FRIDGE, METOD_READ, number);
        FridgeWebDto fridgeWebDto = serviceAppRestClient.getForObject(urlFridgeRead, FridgeWebDto.class);

        String urlFridgeUpdate = String.format("%s%s%s", HOST_URL, ROOT_FRIDGE, METOD_UPDATE_CONDITIONAL);

        FridgeWebDto fridgeWebDtoUpdate = serviceAppRestClient.postForObject(urlFridgeUpdate, fridgeWebDto, FridgeWebDto.class, number, parameter);

        return fridgeMapper.toEntity(fridgeWebDtoUpdate);
    }

    public void deleteFridge(Integer number) {
        String urlFridgeDelete = String.format("%s%s%s%d", HOST_URL, ROOT_FRIDGE, METOD_DELETE, number);
        serviceAppRestClient.delete(urlFridgeDelete);
    }
}
