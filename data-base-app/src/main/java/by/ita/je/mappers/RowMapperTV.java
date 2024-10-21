package by.ita.je.mappers;

import by.ita.je.models.Country;
import by.ita.je.models.Kettle;
import by.ita.je.models.TV;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class RowMapperTV {
    public TV mapRow(ResultSet rs) throws SQLException {
        Integer number = rs.getInt("number");
        String type = rs.getString("type");
        String brand = rs.getString("brand");
        Boolean discount = rs.getBoolean("discount");
        Integer diagonal = rs.getInt("diagonal");
        BigDecimal price = rs.getBigDecimal("price");
        Character energy = rs.getString("energy").charAt(0);
        ZonedDateTime registered = rs.getObject("registered", ZonedDateTime.class);
        List<Kettle> kettles = rs.getObject("kettles", List.class);
        Country country = rs.getObject("country", Country.class);

        return new TV(type, brand, discount, diagonal, price, number, energy, registered, kettles, country);
    }
}
