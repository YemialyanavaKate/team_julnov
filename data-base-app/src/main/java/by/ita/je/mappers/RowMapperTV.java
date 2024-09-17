package by.ita.je.mappers;

import by.ita.je.models.TV;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

@Component
public class RowMapperTV {
    public TV mapRow(ResultSet rs) throws SQLException {
        Integer number = rs.getInt("number");
        String type = rs.getString("type");
        String brand = rs.getString("brand");
        boolean discount = rs.getBoolean("discount");
        Integer diagonal = rs.getInt("diagonal");
        BigDecimal price = rs.getBigDecimal("price");
        Character energy = rs.getString("energy").charAt(0);
        ZonedDateTime registered = rs.getObject("registered", ZonedDateTime.class);

        return new TV( type, brand, discount, diagonal, price, number, energy, registered);
    }
}
