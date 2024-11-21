package by.ita.je.controller;

import by.ita.je.webDto.FridgeWebDto;
import by.ita.je.mappers.FridgeMapper;
import by.ita.je.models.Fridge;
import by.ita.je.service.FridgeWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/fridge")
@RequiredArgsConstructor
public class FridgeWebController {
    private final FridgeWebService fridgeWebService;
    private final FridgeMapper fridgeMapper;

    @GetMapping("/home")
    public String showHomePage() {
        return "home.html";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("fridge", new FridgeWebDto());
        return "create.html";
    }

    @PostMapping("/create")
    public String createFridge(@ModelAttribute FridgeWebDto fridgeWebDto, Model model) {
        Fridge fridge = fridgeMapper.toEntity(fridgeWebDto);
        Fridge createdFridge = fridgeWebService.createFridgeDto(fridge);
        FridgeWebDto fridgewebDto = fridgeMapper.toWebDto(createdFridge);
        model.addAttribute("fridge", fridgewebDto);
        return "success.html";
    }

    @GetMapping("/read")
    public String showSearchForm() {
        return "search.html";
    }

    @PostMapping("/read")
    public String readFridge(Integer number, Model model) {
        Fridge fridge = fridgeWebService.readFridge(number);
        FridgeWebDto fridgeWebDto = fridgeMapper.toWebDto(fridge);
        model.addAttribute("fridge", fridgeWebDto);
        return "searchResults.html";
    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute FridgeWebDto fridgeWebDto, Model model) {
        model.addAttribute("fridge", fridgeWebDto);
        return "add.html";
    }

    @PostMapping("/add")
    public String addEntity(Integer number, FridgeWebDto fridgeWebDto, Model model) {
        Fridge fridge = fridgeMapper.toEntity(fridgeWebDto);
        Fridge fridgeWithEntity = fridgeWebService.addKettleAndMulticooker(number, fridge);
        FridgeWebDto fridgeWithEntityWebDto = fridgeMapper.toWebDto(fridgeWithEntity);
        model.addAttribute("fridge", fridgeWithEntityWebDto);
        return "addSuccess.html";
    }

    @GetMapping("/update")
    public String showUpdateByConditionalForm() {
        return "conditional.html";
    }

    @PostMapping("/update")
    public String findFridgeAndUpdateByConditional(Integer number, Integer parameter, Model model) {
        Fridge fridge = fridgeWebService.findFridgeAndUpdateByConditional(number, parameter);
        FridgeWebDto fridgeWebDto = fridgeMapper.toWebDto(fridge);
        model.addAttribute("fridge", fridgeWebDto);
        return "conditionalresults.html";
    }

    @GetMapping("/delete")
    public String showDeleteForm() {
        return "delete.html";
    }

    @PostMapping("/delete")
    public String showDeleteResult(Integer number) {
        fridgeWebService.deleteFridge(number);
        return "deleteSuccess.html";
    }

}
