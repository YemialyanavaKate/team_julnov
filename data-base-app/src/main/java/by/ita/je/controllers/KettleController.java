package by.ita.je.controllers;

import by.ita.je.dto.KettleDto;
import by.ita.je.services.KettleService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/kettle")
public class KettleController {
    private final KettleService kettleService;

    public KettleController(KettleService kettleService) {
        this.kettleService = kettleService;
    }

    @PostMapping("/create")
    public KettleDto create(){
        return KettleDto.builder()
                .type("Kettle")
                .color("black")
                .isElectric(false)
                .isInduction(true)
                .price(BigDecimal.valueOf(500.5))
                .number(2)
                .build();
    }

    @GetMapping("/read")
    public KettleDto read(){
        return KettleDto.builder()
                .type("Kettle")
                .color("black")
                .isElectric(false)
                .isInduction(true)
                .price(BigDecimal.valueOf(500.5))
                .number(2)
                .build();
    }

    @GetMapping("/read/all")
    public List<Map<String, Object>> readAll(){
        return kettleService.readALL();
    }

    @PutMapping("/update")
    public KettleDto update(){
        return KettleDto.builder()
                .type("Kettle")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(1100.99))
                .number(2)
                .build();
    }
    @DeleteMapping("/delete")
    public KettleDto delete(){
        return KettleDto.builder().build();
    }

    @DeleteMapping("/delete/all")
    public List<KettleDto> deleteAll(){
        return Collections.emptyList();
    }
}
