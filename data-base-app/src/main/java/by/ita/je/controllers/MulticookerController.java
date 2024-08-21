package by.ita.je.controllers;

import by.ita.je.dto.MulticookerDto;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/multicooker")
public class MulticookerController {

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
    public List<MulticookerDto> readAll(){
        return Collections.emptyList();
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
