package by.ita.je.services;

import by.ita.je.models.TV;
import by.ita.je.repository.TVCrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TVService {
    private final TVCrudRepository tvCrudRepository;

    public TVService(TVCrudRepository tvCrudRepository) {
        this.tvCrudRepository = tvCrudRepository;
    }

    public TV findTVByNumber(Integer number) {
        return tvCrudRepository.findById(number).orElse(null);
    }

    @Transactional
    public TV insertTV() {
        TV tv = TV.builder()
                .number(6)
                .type("TV")
                .brand("LG")
                .discount(true)
                .diagonal(15)
                .price(BigDecimal.valueOf(1000.5))
                .energy('A')
                .registered(ZonedDateTime.parse("2024-08-08T08:08:54+02"))
                .build();
        return tvCrudRepository.save(tv);
    }

    @Transactional
    public TV updateTV(TV tv) {
        TV tvUpdate = tvCrudRepository.findById(tv.getNumber())
                .map(l -> {
                    l.setBrand("Philips");
                    l.setDiagonal(42);
                    l.setPrice(BigDecimal.valueOf(7000.01));
                    return l;
                }).orElse(null);
        if (tvUpdate == null) {
            return tv;
        }
        return tvCrudRepository.save(tv);
    }


    public TV deleteTV(Integer number) {
        return tvCrudRepository.findById(number)
                .map(l -> {
                            tvCrudRepository.deleteById(number);
                            return l;
                        }
                ).orElse(null);
    }


    public List<TV> readALL() {
        return StreamSupport.stream(tvCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        tvCrudRepository.deleteAll();
    }
}
