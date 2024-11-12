package by.ita.je.service;

import by.ita.je.webDto.FridgeWebDto;
import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.Fridge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FridgeWebService {
    private final RestTemplate serviceAppRestClient;
    private final FridgeMapper fridgeMapper;

    public Fridge createFridgeDto(FridgeWebDto fridgeWebDto) throws HttpClientErrorException, HttpServerErrorException {
        String urlFridgeCreate = "http://127.0.0.1:8111/business/fridge/create";
        return fridgeMapper.toEntity(serviceAppRestClient.postForObject(urlFridgeCreate, fridgeWebDto, FridgeWebDto.class));
    }
}
