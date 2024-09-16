package by.ita.je.controllers;

import by.ita.je.dto.TVDto;
import by.ita.je.mappers.TVMapper;
import by.ita.je.models.TV;
import by.ita.je.services.TVService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

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
                .number(6)
                .type("TV")
                .brand("LG")
                .discount(true)
                .diagonal(15)
                .price(BigDecimal.valueOf(1000.5))
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
    public List<TVDto> readAll(){
        return tvService.readALL().stream().map(TVMapper::toDTO).toList();
    }

    @PutMapping("/update")
    public TVDto update(@RequestParam Integer number){
        TV tv = tvService.findTVByNumber(number);
        if (tv == null){
            return null;
        }

        tv.setBrand("Philips");
        tv.setDiagonal(42);
        tv.setPrice(BigDecimal.valueOf(7000.01));

        return TVMapper.toDTO(tvService.updateTV(tv));
    }

    @DeleteMapping("/delete")
    public TVDto delete(@RequestParam Integer number){
        return TVMapper.toDTO(tvService.deleteTV(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll(){
        tvService.deleteAll();
    }
}
