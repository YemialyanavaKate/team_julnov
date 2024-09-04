package by.ita.je.controllers;

import by.ita.je.dto.FridgeDto;
import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.Fridge;
import by.ita.je.services.FridgeService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/fridge")
public class FridgeController {
    private  final FridgeService fridgeService;

    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @PostMapping("/create")
    public FridgeDto create(){
        Fridge fridge = Fridge.builder()
                .type("Samsung")
                .description("The best")
                .discount(false)
                .defect(false)
                .price(BigDecimal.valueOf(3000.99))
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();
        Fridge fridgeNew = fridgeService.insertFridge(fridge);
        return FridgeMapper.toDTO(fridgeNew);
    }

    @GetMapping("/read")
    public FridgeDto read(@RequestParam Integer number){
        return FridgeMapper.toDTO(fridgeService.findFridgeByNumber(number));
    }

    @GetMapping("/read/all")
    public List<Map<String, Object>> readAll(){
        return fridgeService.readALL();
    }

    @PutMapping("/update")
    public FridgeDto update(){
        Fridge fridge = Fridge.builder()
                .type("Samsung")
                .description("The best")
                .discount(false)
                .defect(false)
                .price(BigDecimal.valueOf(3000.99))
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();

        return FridgeMapper.toDTO(fridgeService.updateFridge(fridge));
    }

    @DeleteMapping("/delete")
    public FridgeDto delete(Integer number){
        return FridgeMapper.toDTO(fridgeService.deleteFridge(number));
    }
}
