package by.ita.je.mappers;

import by.ita.je.models.Multicooker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

@Component
public class RowMapperMulticooker {
    public Multicooker mapRow(ResultSet rs) throws SQLException {
        Integer number = rs.getInt("number");
        String type = rs.getString("type");
        String description = rs.getString("description");
        boolean isTouchScreen = rs.getBoolean("isTouchScreen");
        Integer numberModes = rs.getInt("numberModes");
        BigDecimal price = rs.getBigDecimal("price");
        Character energy = rs.getString("energy").charAt(0);
        ZonedDateTime registered = rs.getObject("registered", ZonedDateTime.class);

        return new Multicooker( type, description, isTouchScreen, numberModes, price, number, energy, registered);
    }
}
