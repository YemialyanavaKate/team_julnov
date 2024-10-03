package by.ita.je.services;

import by.ita.je.models.TV;
import by.ita.je.repository.TVJdbcTemp;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TVService {
    private final TVJdbcTemp tvJdbcTemp;

    public TVService(TVJdbcTemp tvJdbcTemp) {
        this.tvJdbcTemp = tvJdbcTemp;
    }

    public TV findTVByNumber(Integer number) {
        return tvJdbcTemp.findTVByNumber(number);
    }

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
        return tvJdbcTemp.insertTV(tv);
    }

    public TV updateTV(TV tv) {
        tv.setBrand("Philips");
        tv.setDiagonal(42);
        tv.setPrice(BigDecimal.valueOf(7000.01));
        return tvJdbcTemp.updateTV(tv);
    }

    public TV deleteTV(Integer number) {
        return tvJdbcTemp.deleteTV(number);
    }


    public List<TV> readALL() {
        return tvJdbcTemp.readALL();
    }

    public void deleteAll() {
        tvJdbcTemp.deleteAll();
    }
}
