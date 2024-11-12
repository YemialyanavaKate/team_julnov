package by.ita.je.controller;

import by.ita.je.dto.to_web.FridgeWebDto;
import by.ita.je.dto.to_web.KettleWebDto;
import by.ita.je.dto.to_web.MulticookerWebDto;
import by.ita.je.mapper.FridgeMapper;
import by.ita.je.mapper.KettleMapper;
import by.ita.je.mapper.MulticookerMapper;
import by.ita.je.models.Fridge;
import by.ita.je.models.Kettle;
import by.ita.je.models.Multicooker;
import by.ita.je.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/business/fridge")
public class BusinessController {
    private final BusinessService businessService;
    private final FridgeMapper fridgeMapper;
    private final KettleMapper kettleMapper;
    private final MulticookerMapper multicookerMapper;

    @PostMapping("/create")
    public FridgeWebDto create(@Valid @RequestBody FridgeWebDto fridgeWebDto) {
        Fridge fridge = fridgeMapper.toEntity(fridgeWebDto);
        return fridgeMapper.toWebDto(businessService.create(fridge));
    }

    @PutMapping("/update")
    public FridgeWebDto findFridgePlusKettleAndMulticooker(Integer number, KettleWebDto kettleWebDto, MulticookerWebDto multicookerWebDto) {
        Kettle kettle = kettleMapper.toEntity(kettleWebDto);
        Multicooker multicooker = multicookerMapper.toEntity(multicookerWebDto);
        Fridge fridgePlusKettleAndMulticooker = businessService.findFridgePlusKettleAndMulticooker(number, kettle, multicooker);
        return fridgeMapper.toWebDto(fridgePlusKettleAndMulticooker);
    }

    @PutMapping("/update_conditional")
    public FridgeWebDto findFridgePlusTVAndCountryByConditional(Integer number, @RequestParam Integer parameter) {
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
