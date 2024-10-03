package by.ita.je.services;

import by.ita.je.models.Kettle;
import by.ita.je.repository.KettleJpa;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class KettleService {

    private final KettleJpa kettleJpa;

    public KettleService(KettleJpa kettleJpa) {
        this.kettleJpa = kettleJpa;
    }

    public Kettle findKettleByNumber(Integer number) {
        return kettleJpa.getById(number);
    }

    public Kettle insertKettle(Kettle kettle) {
        return kettleJpa.save(kettle);
    }

    public Kettle createKettle(){
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
    public Kettle updateKettle(Kettle kettle) {
        if (findKettleByNumber(kettle.getNumber()) != null) {

            kettle.setType("glass");
            kettle.setColor("pink");
            kettle.setPrice(BigDecimal.valueOf(122.22));

            return kettleJpa.save(kettle);
        } else {
            return null;
        }
    }

    public Kettle deleteKettle(Integer number) {
        Kettle kettleDelete = findKettleByNumber(number);
        if (kettleDelete == null) {
            return null;
        }
        kettleJpa.deleteById(number);
        return kettleDelete;
    }

    public List<Kettle> readALL() {
        return kettleJpa.findAll();
    }

    public void deleteAll() {
        kettleJpa.deleteAll();
    }
}
