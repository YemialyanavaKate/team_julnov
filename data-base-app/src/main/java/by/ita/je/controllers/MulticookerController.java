package by.ita.je.controllers;

import by.ita.je.dto.MulticookerDto;
import by.ita.je.mappers.MulticookerMapper;
import by.ita.je.models.Multicooker;
import by.ita.je.services.MulticookerService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
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
        Multicooker multicooker = Multicooker.builder()
                .type("Tefal")
                .description("бла-бла-бла")
                .isTouchScreen(true)
                .numberModes(10)
                .price(BigDecimal.valueOf(1000.5))
                .number(3)
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();
        Multicooker multicookerNew = multicookerService.insertMulticooker(multicooker);
        return MulticookerMapper.toDTO(multicookerNew);
    }

    @GetMapping("/read")
    public MulticookerDto read(@RequestParam Integer number){
        return MulticookerMapper.toDTO(multicookerService.findMulticookerByNumber(number));
    }

    @GetMapping("/read/all")
    public List<Map<String, Object>> readAll(){
        return multicookerService.readALL();
    }

    @PutMapping("/update")
    public MulticookerDto update(){
        Multicooker multicooker = Multicooker.builder().
                type("Redmond")
                .description("Beteer")
                .isTouchScreen(false)
                .numberModes(222)
                .price(BigDecimal.valueOf(1350.5))
                .number(3)
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();
        Multicooker multicookerNew = multicookerService.updateMulticooker(multicooker);
        return MulticookerMapper.toDTO(multicookerNew);
    }

    @DeleteMapping("/delete")
    public MulticookerDto delete(Integer number){
        return MulticookerMapper.toDTO(multicookerService.deleteMulticooker(number));
    }
}
