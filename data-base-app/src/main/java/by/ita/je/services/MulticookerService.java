package by.ita.je.services;

import by.ita.je.models.Multicooker;
import by.ita.je.repository.MulticookerCrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MulticookerService {
    private final MulticookerCrudRepository multicookerCrud;

    public MulticookerService(MulticookerCrudRepository multicookerCrud) {
        this.multicookerCrud = multicookerCrud;
    }

    public Multicooker findMulticookerByNumber(Integer number) {
        return multicookerCrud.findById(number).orElse(null);
    }

    @Transactional
    public Multicooker updateMulticooker(Multicooker multicooker) {
        Multicooker multicookerUpdate = multicookerCrud.findById(multicooker.getNumber())
                .map(l -> {
                            l.setType("Tefal");
                            l.setIsTouchScreen(true);
                            l.setPrice(BigDecimal.valueOf(1000.5));
                            return l;
                        }
                ).orElse(null);
        if (multicookerUpdate == null) {
            return multicooker;
        }

        return multicookerCrud.save(multicookerUpdate);
    }

    @Transactional
    public Multicooker deleteMulticooker(Integer number) {
        return multicookerCrud.findById(number)
                .map(l -> {
                            multicookerCrud.deleteById(number);
                            return l;
                        }
                ).orElse(null);
    }

    @Transactional
    public Multicooker insertMulticooker() {
        Multicooker multicooker = Multicooker.builder()
                .number(5)
                .type("Midea")
                .description("So so")
                .isTouchScreen(true)
                .numberModes(91)
                .price(BigDecimal.valueOf(1112))
                .energy('B')
                .registered(ZonedDateTime.parse("2024-01-01T10:23:54+02"))
                .build();
        return multicookerCrud.save(multicooker);
    }

    public List<Multicooker> readALL() {
        return StreamSupport.stream(multicookerCrud.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteALL() {
        multicookerCrud.deleteAll();
    }
}
