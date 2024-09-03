package by.ita.je.controllers;

import by.ita.je.dto.MulticookerDto;
import by.ita.je.services.MulticookerService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/multicooker")
public class MulticookerController {
    private final MulticookerService multicookerService;

    public MulticookerController(MulticookerService multicookerService) {
        this.multicookerService = multicookerService;
    }


    @PostMapping("/create")
    public MulticookerDto create(){
        return MulticookerDto.builder()
                .type("Tefal")
                .description("бла-бла-бла")
                .isTouchScreen(true)
                .numberModes(10)
                .price(BigDecimal.valueOf(1000.5))
                .number(3)
                .build();
    }

    @GetMapping("/read")
    public MulticookerDto read(){
        return MulticookerDto.builder()
                .type("Tefal")
                .description("бла-бла-бла")
                .isTouchScreen(true)
                .numberModes(10)
                .price(BigDecimal.valueOf(1000.5))
                .number(3)
                .build();
    }

    @GetMapping("/read/all")
    public List<Map<String, Object>> readAll(){
        return multicookerService.readALL();
    }

    @PutMapping("/update")
    public MulticookerDto update(){
        return MulticookerDto.builder().
                type("Redmond")
                .description("Beteer")
                .isTouchScreen(false)
                .numberModes(222)
                .price(BigDecimal.valueOf(1350.5))
                .number(3)
                .build();
    }

    @DeleteMapping("/delete")
    public MulticookerDto delete(){
        return MulticookerDto.builder().build();
    }

    @DeleteMapping("/delete/all")
    public List<MulticookerDto> deleteAll(){
        return Collections.emptyList();
    }
}
