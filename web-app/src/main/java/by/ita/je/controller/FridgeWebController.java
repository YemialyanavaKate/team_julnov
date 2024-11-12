package by.ita.je.controller;

import by.ita.je.webDto.FridgeWebDto;
import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.Fridge;
import by.ita.je.service.FridgeWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/fridge")
@RequiredArgsConstructor
public class FridgeWebController {
    private final FridgeWebService fridgeWebService;
    private final FridgeMapper fridgeMapperToWebDto;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("fridge", new FridgeWebDto());
        return "create.html";
    }

    @PostMapping("/create")
    public String createFridge(@ModelAttribute FridgeWebDto fridgeWebDto, Model model) {
        Fridge createdFridge = fridgeWebService.createFridgeDto(fridgeWebDto);
        FridgeWebDto fridgewebDto = fridgeMapperToWebDto.toWebDto(createdFridge);
        model.addAttribute("fridge", fridgewebDto);
        return "success.html";
    }
}
