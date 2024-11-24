package by.ita.je.controllers;

import by.ita.je.dto.MulticookerDto;
import by.ita.je.mappers.MulticookerMapperToDto;
import by.ita.je.models.Multicooker;
import by.ita.je.services.MulticookerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/database/multicooker")
public class MulticookerController {
    private final MulticookerService multicookerService;
    private final MulticookerMapperToDto multicookerMapper;

    public MulticookerController(MulticookerService multicookerService, MulticookerMapperToDto multicookerMapper) {
        this.multicookerService = multicookerService;
        this.multicookerMapper = multicookerMapper;
    }

    @PostMapping("/create")
    public MulticookerDto create() {
        Multicooker multicookerInsert = multicookerService.insertMulticooker();
        return multicookerMapper.toDTO(multicookerInsert);
    }

    @GetMapping("/read")
    public MulticookerDto read(@RequestParam Integer number) {
        return multicookerMapper.toDTO(multicookerService.findMulticookerByNumber(number));
    }

    @GetMapping("/read/all")
    public List<MulticookerDto> readAll() {
        return multicookerService.readALL().stream().map(multicookerMapper::toDTO).collect(Collectors.toList());
    }

    @PutMapping("/update")
    public MulticookerDto update(@RequestParam Integer number) {
        Multicooker multicooker = multicookerService.findMulticookerByNumber(number);
        if (multicooker == null) {
            return null;
        }
        Multicooker multicookerNew = multicookerService.updateMulticooker(multicooker);
        return multicookerMapper.toDTO(multicookerNew);
    }

    @DeleteMapping("/delete")
    public MulticookerDto delete(Integer number) {
        return multicookerMapper.toDTO(multicookerService.deleteMulticooker(number));
    }

    @DeleteMapping("/delete/all")
    public void deleteAll() {
        multicookerService.deleteALL();
    }
}


