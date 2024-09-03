package by.ita.je.controllers;

import by.ita.je.dto.TVDto;
import by.ita.je.services.TVService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/tv")
public class TVController {

    private final TVService tvService;

    public TVController(TVService tvService) {
        this.tvService = tvService;
    }


    @PostMapping("/create")
    public TVDto create(){
        return TVDto.builder()
                .type("TV")
                .brand("LG")
                .discount(true)
                .diagonal(15)
                .price(BigDecimal.valueOf(1000.5))
                .number(3)
                .build();
    }

    @GetMapping("/read")
    public TVDto read(){
        return TVDto.builder()
                .type("TV")
                .brand("LG")
                .discount(true)
                .diagonal(15)
                .price(BigDecimal.valueOf(1000.5))
                .number(3)
                .build();
    }

    @GetMapping("/read/all")
    public List<Map<String, Object>> readAll(){
        return tvService.readALL();
    }

    @PutMapping("/update")
    public TVDto update(){
        return TVDto.builder()
                .type("TV")
                .brand("Horizont")
                .discount(false)
                .diagonal(10)
                .price(BigDecimal.valueOf(100.5))
                .number(3)
                .build();
    }

    @DeleteMapping("/delete")
    public TVDto delete(){
        return TVDto.builder().build();
    }

    @DeleteMapping("/delete/all")
    public List<TVDto> deleteAll(){
        return Collections.emptyList();
    }
}
