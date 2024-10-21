package by.ita.je.services;

import by.ita.je.models.Kettle;
import by.ita.je.repository.KettleCrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KettleService {

    private final KettleCrudRepository kettleCrudRepository;

    public KettleService(KettleCrudRepository kettleCrudRepository) {
        this.kettleCrudRepository = kettleCrudRepository;
    }

    @Transactional
    public Kettle findKettleByNumber(Integer number) {
        return kettleCrudRepository.findById(number).orElse(null);
    }

    @Transactional
    public Kettle insertKettle(Kettle kettle) {
        return kettleCrudRepository.save(kettle);
    }

    @Transactional
    public Kettle createKettle() {
        return Kettle.builder()
                .number(5)
                .type("glass")
                .color("blue")
                .isElectric(false)
                .isInduction(false)
                .price(BigDecimal.valueOf(30.33))
                .energy('A')
                .registered(ZonedDateTime.parse("2023-12-26T20:28:33.213+02"))
                .build();
    }

    @Transactional
    public Kettle updateKettle(Kettle kettle) {
        if (findKettleByNumber(kettle.getNumber()) != null) {

            kettle.setType("glass");
            kettle.setColor("pink");
            kettle.setPrice(BigDecimal.valueOf(122.22));

            return kettleCrudRepository.save(kettle);
        } else {
            return null;
        }
    }

    @Transactional
    public Kettle deleteKettle(Integer number) {
        Kettle kettleDelete = findKettleByNumber(number);
        if (kettleDelete == null) {
            return null;
        }
        kettleCrudRepository.deleteById(number);
        return kettleDelete;
    }

    public List<Kettle> readALL() {
        return StreamSupport.stream(kettleCrudRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        kettleCrudRepository.deleteAll();
    }
}
