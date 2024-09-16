package by.ita.je.controllers;

import by.ita.je.dto.MulticookerDto;
import by.ita.je.mappers.MulticookerMapper;
import by.ita.je.models.Multicooker;
import by.ita.je.services.MulticookerService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

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
                .number(5)
                .type("Tefal")
                .description("бла-бла-бла")
                .isTouchScreen(true)
                .numberModes(10)
                .price(BigDecimal.valueOf(1000.5))
                .number(5)
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
    public List<MulticookerDto> readAll(){
        return multicookerService.readALL().stream().map(MulticookerMapper::toDTO).toList();
    }

    @PutMapping("/update")
    public MulticookerDto update(@RequestParam Integer number){
        Multicooker multicooker = multicookerService.findMulticookerByNumber(number);
        if (multicooker == null) {
            return null;
        }

        multicooker.setType("NonRemovablePanels");
        multicooker.setDescription("Grill");
        multicooker.setPrice(BigDecimal.valueOf(100.99));

        Multicooker multicookerNew = multicookerService.updateMulticooker(multicooker);
        return MulticookerMapper.toDTO(multicookerNew);
    }

    @DeleteMapping("/delete")
    public MulticookerDto delete(Integer number){
        return MulticookerMapper.toDTO(multicookerService.deleteMulticooker(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll(){
        multicookerService.deleteALL();
    }
}


