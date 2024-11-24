package by.ita.je.controllers;

import by.ita.je.dto.KettleDto;
import by.ita.je.mappers.KettleMapperToDto;
import by.ita.je.models.Kettle;
import by.ita.je.services.KettleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/database/kettle")
public class KettleController {
    private final KettleService kettleService;
    private final KettleMapperToDto kettleMapper;
    public KettleController(KettleService kettleService, KettleMapperToDto kettleMapper) {
        this.kettleService = kettleService;

        this.kettleMapper = kettleMapper;
    }

    @PostMapping("/create")
    public KettleDto create(){
        Kettle kettleNew = kettleService.createKettle();
        Kettle kettleInsert = kettleService.insertKettle(kettleNew);
        return kettleMapper.toDTO(kettleInsert);
    }

    @GetMapping("/read")
    public KettleDto read(@RequestParam Integer number){
        return kettleMapper.toDTO(kettleService.findKettleByNumber(number));
    }

    @GetMapping("/read/all")
    public List<KettleDto> readAll(){
        return kettleService.readALL().stream().map(kettleMapper::toDTO).collect(Collectors.toList());
    }

    @PutMapping("/update")
    public KettleDto update(@RequestParam Integer number){
        Kettle kettle = kettleService.findKettleByNumber(number);
        if (kettle == null) {
            return null;
        }
        Kettle updateKettle = kettleService.updateKettle(kettle);
        return kettleMapper.toDTO(updateKettle);
    }

    @DeleteMapping("/delete")
    public KettleDto delete(@RequestParam Integer number){
        return kettleMapper.toDTO(kettleService.deleteKettle(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll(){
        kettleService.deleteAll();
    }
}
