package by.ita.je.controllers;

import by.ita.je.dto.TVDto;
import by.ita.je.mappers.TVMapper;
import by.ita.je.models.TV;
import by.ita.je.services.TVService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
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
        TV tv = TV.builder()
                .type("TV")
                .brand("LG")
                .discount(true)
                .diagonal(15)
                .price(BigDecimal.valueOf(1000.5))
                .number(3)
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();
        TV tvNew = tvService.insertTV(tv);
        return TVMapper.toDTO(tvNew);
    }

    @GetMapping("/read")
    public TVDto read(@RequestParam Integer number){
        return TVMapper.toDTO(tvService.findTVByNumber(number));
    }

    @GetMapping("/read/all")
    public List<Map<String, Object>> readAll(){
        return tvService.readALL();
    }

    @PutMapping("/update")
    public TVDto update(){
        TV tv = TV.builder()
                .type("TV")
                .brand("Horizont")
                .discount(false)
                .diagonal(10)
                .price(BigDecimal.valueOf(100.5))
                .number(3)
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();

        return TVMapper.toDTO(tvService.updateTV(tv));
    }

    @DeleteMapping("/delete")
    public TVDto delete(Integer number){
        return TVMapper.toDTO(tvService.deleteTV(number));
    }
}
