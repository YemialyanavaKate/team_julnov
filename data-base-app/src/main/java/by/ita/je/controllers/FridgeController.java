package by.ita.je.controllers;

import by.ita.je.dto.FridgeDto;
import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.Fridge;
import by.ita.je.services.FridgeService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/fridge")
public class FridgeController {

    private  final FridgeService fridgeService;
    private final FridgeMapper fridgeMapper;

    public FridgeController(FridgeService fridgeService, FridgeMapper fridgeMapper) {
        this.fridgeService = fridgeService;
        this.fridgeMapper = fridgeMapper;
    }

    @PostMapping("/create")
    public FridgeDto create(){
        Fridge fridge = Fridge.builder()
                .number(5)
                .type("Samsung")
                .description("The best")
                .discount(false)
                .defect(false)
                .price(BigDecimal.valueOf(3000.99))
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();
        Fridge fridgeNew = fridgeService.insertFridge(fridge);
        return fridgeMapper.toDTO(fridgeNew);
    }

    @GetMapping("/read")
    public FridgeDto read(@RequestParam Integer number){
        return fridgeMapper.toDTO(fridgeService.findFridgeByNumber(number));
    }

    @GetMapping("/read/all")
    public List<FridgeDto> readAll(){
        return fridgeService.readALL().stream().map(fridgeMapper::toDTO).toList();
    }

    @PutMapping("/update")
    public FridgeDto update(@RequestParam Integer number){
        Fridge fridge = fridgeService.findFridgeByNumber(number);
        if (fridge == null) {
            return null;
        }

        fridge.setType("slim");
        fridge.setDefect(false);
        fridge.setPrice(BigDecimal.valueOf(1000.8));

        return fridgeMapper.toDTO(fridgeService.updateFridge(fridge));
    }

    @DeleteMapping("/delete")
    public FridgeDto delete(@RequestParam Integer number){
        return fridgeMapper.toDTO(fridgeService.deleteFridge(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll(){
        fridgeService.deleteALL();
    }
}
