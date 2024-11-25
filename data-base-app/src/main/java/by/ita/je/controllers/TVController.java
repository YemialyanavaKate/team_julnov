package by.ita.je.controllers;

import by.ita.je.dto.TVDto;
import by.ita.je.mappers.TVMapperToDto;
import by.ita.je.models.TV;
import by.ita.je.services.TVService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/database/tv")
public class TVController {

    private final TVService tvService;
    private final TVMapperToDto tvMapper;

    public TVController(TVService tvService, TVMapperToDto tvMapper) {
        this.tvService = tvService;
        this.tvMapper = tvMapper;
    }


    @PostMapping("/create")
    public TVDto create() {
        TV tvNew = tvService.insertTV();
        return tvMapper.toDTO(tvNew);
    }

    @GetMapping("/read")
    public TVDto read(@RequestParam Integer number) {
        return tvMapper.toDTO(tvService.findTVByNumber(number));
    }

    @GetMapping("/read/all")
    public List<TVDto> readAll() {
        return tvService.readALL().stream().map(tvMapper::toDTO).collect(Collectors.toList());
    }

    @PutMapping("/update")
    public TVDto update(@RequestParam Integer number) {
        TV tv = tvService.findTVByNumber(number);
        if (tv == null) {
            return null;
        }
        return tvMapper.toDTO(tvService.updateTV(tv));
    }

    @DeleteMapping("/delete")
    public TVDto delete(@RequestParam Integer number) {
        return tvMapper.toDTO(tvService.deleteTV(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll() {
        tvService.deleteAll();
    }
}
