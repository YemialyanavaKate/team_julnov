package by.ita.je.controllers;

import  by.ita.je.dto.FridgeDto;
import by.ita.je.services.FridgeService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Collections;
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
        return FridgeDto.builder()
                .type("Ariston")
                .description("бла-бла")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2200.5))
                .number(5)
                .build();
    }

    @GetMapping("/read")
    public FridgeDto read(){
        return FridgeDto.builder()
                .type("Ariston")
                .description("бла-бла")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2200.5))
                .number(5)
                .build();
    }

    @GetMapping("/read/all")
    public List<Map<String, Object>> readAll(){
        return fridgeService.readALL();
    }

    @PutMapping("/update")
    public FridgeDto update(){
        return FridgeDto.builder()
                .type("Samsung")
                .description("The best")
                .discount(false)
                .defect(false)
                .price(BigDecimal.valueOf(3000.99))
                .number(1)
                .build();
    }

    @DeleteMapping("/delete")
    public FridgeDto delete(){
        return FridgeDto.builder().build();
    }

    @DeleteMapping("/delete/all")
    public List<FridgeDto> deleteAll(){
        return Collections.emptyList();
    }
}
