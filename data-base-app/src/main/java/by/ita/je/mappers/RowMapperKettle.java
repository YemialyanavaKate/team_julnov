package by.ita.je.mappers;

import by.ita.je.models.Kettle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

@Component
public class RowMapperKettle {
    public Kettle mapRow(ResultSet resultSet) throws SQLException {
        Integer number = resultSet.getInt("number");
        String type = resultSet.getString("type");
        String color = resultSet.getString("color");
        boolean isElectric = resultSet.getBoolean("isElectric");
        boolean isInduction = resultSet.getBoolean("isInduction");
        BigDecimal price = resultSet.getBigDecimal("price");
        Character energy = resultSet.getString("energy").charAt(0);
        ZonedDateTime registered = resultSet.getObject("registered", ZonedDateTime.class);

        return new Kettle( type, color, isElectric, isInduction, price, number, energy, registered);
    }
}
