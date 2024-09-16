package by.ita.je.controllers;

import by.ita.je.dto.KettleDto;
import by.ita.je.mappers.KettleMapper;
import by.ita.je.models.Kettle;
import by.ita.je.services.KettleService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/kettle")
public class KettleController {
    private final KettleService kettleService;

    public KettleController(KettleService kettleService) {
        this.kettleService = kettleService;
    }

    @PostMapping("/create")
    public KettleDto create(){
        Kettle kettle = Kettle.builder()
                .number(5)
                .type("glass")
                .color("blue")
                .isElectric(false)
                .isInduction(false)
                .price(BigDecimal.valueOf(30.33))
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();
        Kettle kettleNew = kettleService.insertKettle(kettle);
        return KettleMapper.toDTO(kettleNew);
    }

    @GetMapping("/read")
    public KettleDto read(@RequestParam Integer number){
        return KettleMapper.toDTO(kettleService.findKettleByNumber(number));
    }

    @GetMapping("/read/all")
    public List<KettleDto> readAll(){
        return kettleService.readALL().stream().map(KettleMapper::toDTO).toList();
    }

    @PutMapping("/update")
    public KettleDto update(@RequestParam Integer number){
        Kettle kettle = kettleService.findKettleByNumber(number);
        if (kettle == null) {
            return null;
        }

        kettle.setType("glass");
        kettle.setColor("pink");
        kettle.setPrice(BigDecimal.valueOf(122.22));

        Kettle updateKettle = kettleService.updateKettle(kettle);
        return KettleMapper.toDTO(updateKettle);
    }

    @DeleteMapping("/delete")
    public KettleDto delete(@RequestParam Integer number){
        return KettleMapper.toDTO(kettleService.deleteKettle(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll(){
        kettleService.deleteAll();
    }
}
