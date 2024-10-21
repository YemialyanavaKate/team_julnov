package by.ita.je.services;

import by.ita.je.models.Fridge;
import by.ita.je.repository.FridgeRepoCrud;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FridgeService {

    private final FridgeRepoCrud fridgeCrud;

    public FridgeService(FridgeRepoCrud fridgeCrud) {
        this.fridgeCrud = fridgeCrud;
    }


    public Fridge findFridgeByNumber(Integer number) {
        return fridgeCrud.findById(number).orElse(null);
    }

    public List<Fridge> readALL() {
        List<Fridge> list = new ArrayList<>();
        for (Fridge fridge : fridgeCrud.findAll()) {
            list.add(fridge);
        }
        return list;
    }

    @Transactional
    public Fridge updateFridge(Fridge fridge) {
        Fridge fridgeUpdate = fridgeCrud.findById(fridge.getNumber())
                .map(l -> {
                            l.setType("slim");
                            l.setDescription("So so");
                            l.setDiscount(true);
                            l.setDefect(true);
                            l.setPrice(BigDecimal.valueOf(1000.8));
                            l.setEnergy('A');
                            l.setRegistered(ZonedDateTime.parse("2019-11-19T19:19:54+02"));
                            return l;
                        }
                ).orElse(null);
        if(fridgeUpdate == null){
            return fridge;
        }
        return fridgeCrud.save(fridgeUpdate);
    }

    @Transactional
    public Fridge deleteFridge(Integer number) {
        return fridgeCrud.findById(number)
                .map(l -> {
                            fridgeCrud.deleteById(number);
                            return l;
                        }
                ).orElse(null);
    }

    public void deleteALL() {
        fridgeCrud.deleteAll();
    }

    @Transactional
    public Fridge insertFridge() {
        Fridge fridge = Fridge.builder()
                .number(6)
                .type("Samsung")
                .description("The best")
                .discount(false)
                .defect(false)
                .price(BigDecimal.valueOf(3000.99))
                .energy('A')
                .registered(ZonedDateTime.parse("2023-05-13T07:50:54+02"))
                .build();
        return fridgeCrud.save(fridge);
    }
}
