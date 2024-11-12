package by.ita.je.controllers;

import by.ita.je.dto.FridgeDto;
import by.ita.je.mappers.FridgeMapperToDto;
import by.ita.je.mappers.MapperDecorator;
import by.ita.je.models.Fridge;
import by.ita.je.services.FridgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/fridge")
public class FridgeController {
    private final FridgeService fridgeService;
    private final FridgeMapperToDto fridgeMapper;
    private final MapperDecorator mapperDecorator;

    @PostMapping("/create")
    public FridgeDto create() {
        Fridge fridgeSave = fridgeService.insertFridge();
        return fridgeMapper.toDTO(fridgeSave);
    }

    @PostMapping("/save")
    public FridgeDto save(@RequestBody FridgeDto fridgeDto) {
        Fridge fridge = fridgeMapper.toEntity(fridgeDto);
        Fridge fridgeSave = fridgeService.saveFridge(fridge);
        return fridgeMapper.toDTO(fridgeSave);
    }


    @GetMapping("/read/{number}")
    public FridgeDto read(@PathVariable(name = "number") Integer number) {
        return mapperDecorator.map(fridgeService.findFridgeByNumber(number));
    }

    @GetMapping("/read/all")
    public List<FridgeDto> readAll() {
        return fridgeService.readALL().stream().map(fridgeMapper::toDTO).collect(Collectors.toList());
    }

    @PutMapping("/update")
    public FridgeDto update(@RequestParam Integer number) {
        Fridge fridge = fridgeService.findFridgeByNumber(number);
        if (fridge == null) {
            return null;
        }
        return fridgeMapper.toDTO(fridgeService.updateFridge(fridge));
    }

    @DeleteMapping("/delete")
    public FridgeDto delete(@RequestParam Integer number) {
        return fridgeMapper.toDTO(fridgeService.deleteFridge(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll() {
        fridgeService.deleteALL();
    }
}
