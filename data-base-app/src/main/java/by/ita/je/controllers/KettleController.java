package by.ita.je.controllers;

import by.ita.je.dto.KettleDto;
import by.ita.je.mappers.KettleMapper;
import by.ita.je.models.Kettle;
import by.ita.je.services.KettleService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
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
        Kettle kettle = Kettle.builder()
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
    public List<Map<String, Object>> readAll(){
        return kettleService.readALL();
    }

    @PutMapping("/update")
    public KettleDto update(){
        Kettle kettle = Kettle.builder()
                .type("glass")
                .color("blue")
                .isElectric(false)
                .isInduction(false)
                .price(BigDecimal.valueOf(30.33))
                .energy('A')
                .registered(ZonedDateTime.now())
                .build();
        return KettleMapper.toDTO(kettleService.updateKettle(kettle));
    }
    @DeleteMapping("/delete")
    public KettleDto delete(Integer number){
        return KettleMapper.toDTO(kettleService.deleteKettle(number));
    }
}
