package by.ita.je.services;

import by.ita.je.models.Multicooker;
import by.ita.je.repository.MulticookerRepoEM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MulticookerService {
    private final MulticookerRepoEM multicookerRepoEM;

    public MulticookerService(MulticookerRepoEM multicookerRepoEM) {
        this.multicookerRepoEM = multicookerRepoEM;
    }

    public Multicooker findMulticookerByNumber(Integer number) {
        return multicookerRepoEM.findMulticookerByNumber(number);
    }

    @Transactional
    public Multicooker createMulticooker() {
        return Multicooker.builder()
                .number(5)
                .type("Midea")
                .description("So so")
                .isTouchScreen(true)
                .numberModes(91)
                .price(BigDecimal.valueOf(1112))
                .energy('B')
                .registered(ZonedDateTime.parse("2024-01-01T10:23:54+02"))
                .build();
    }

    @Transactional
    public Multicooker updateMulticooker(Multicooker multicooker) {
        multicooker.setType("Tefal");
        multicooker.setIsTouchScreen(true);
        multicooker.setPrice(BigDecimal.valueOf(1000.5));

        return multicookerRepoEM.updateMulticooker(multicooker);
    }

    @Transactional
    public Multicooker deleteMulticooker(Integer number) {
        return multicookerRepoEM.deleteMulticooker(number);
    }

    @Transactional
    public Multicooker insertMulticooker(Multicooker multicooker) {
        return multicookerRepoEM.insertMulticooker(multicooker);
    }

    public List<Multicooker> readALL() {
        return multicookerRepoEM.readAll();
    }

    @Transactional
    public void deleteALL() {
        multicookerRepoEM.deleteALL();
    }
}
