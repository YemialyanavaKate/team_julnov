package by.ita.je.mappers;

import by.ita.je.models.Fridge;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

@Component
public class RowMapperFridge {
    public Fridge mapRow(ResultSet rs) throws SQLException {
        Integer number = rs.getInt("number");
        String type = rs.getString("type");
        String description = rs.getString("description");
        boolean discount = rs.getBoolean("discount");
        boolean defect = rs.getBoolean("defect");
        BigDecimal price = rs.getBigDecimal("price");
        Character energy = rs.getString("energy").charAt(0);
        ZonedDateTime registered = rs.getObject("registered", ZonedDateTime.class);

        return new Fridge( type, description, discount, defect, price, number, energy, registered);
    }
}
