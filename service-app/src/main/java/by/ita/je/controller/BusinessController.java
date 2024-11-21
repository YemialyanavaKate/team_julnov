package by.ita.je.controller;

import by.ita.je.dto.to_web.FridgeWebDto;
import by.ita.je.mapper.FridgeMapper;
import by.ita.je.models.Fridge;
import by.ita.je.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/business/fridge")
public class BusinessController {
    private final BusinessService businessService;
    private final FridgeMapper fridgeMapper;

    @PostMapping("/create")
    public FridgeWebDto create(@RequestBody FridgeWebDto fridgeWebDto) {
        Fridge fridge = fridgeMapper.toEntity(fridgeWebDto);
        return fridgeMapper.toWebDto(businessService.create(fridge));
    }

    @PostMapping("/update")
    public FridgeWebDto findFridgePlusKettleAndMulticooker(@PathParam("number") Integer number, @RequestBody FridgeWebDto fridgeWebDto) {
        Fridge fridge = fridgeMapper.toEntity(fridgeWebDto);
        Fridge fridgePlusKettleAndMulticooker = businessService.findFridgePlusKettleAndMulticooker(number, fridge);
        return fridgeMapper.toWebDto(fridgePlusKettleAndMulticooker);
    }

    @PostMapping("/update_conditional")
    public FridgeWebDto findFridgePlusTVAndCountryByConditional(@PathParam("number") Integer number, @RequestParam Integer parameter) {
        return fridgeMapper.toWebDto(businessService.findFridgePlusTVAndCountryByConditional(number, parameter));
    }

    @GetMapping("/read/{number}")
    public FridgeWebDto findFridge(@PathVariable(name = "number") Integer number) {
        return fridgeMapper.toWebDto(businessService.findFridge(number));
    }

    @DeleteMapping("/delete/{number}")
    public FridgeWebDto findFridgeDelete(@PathVariable Integer number) {
        return fridgeMapper.toWebDto(businessService.findFridgeAndDelete(number));
    }
}
