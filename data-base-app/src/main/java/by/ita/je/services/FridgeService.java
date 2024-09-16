package by.ita.je.services;

import by.ita.je.models.Fridge;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class FridgeService {

    private final JdbcTemplate jdbcTemplate;
    public FridgeService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Fridge findFridgeByNumber(Integer number){
        String sql = "SELECT * FROM FRIDGE WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> mapRow(resultSet), number);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Fridge updateFridge(Fridge fridge){
        String sql = "UPDATE FRIDGE SET type = ?, description = ?, discount = ?, defect = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
        jdbcTemplate.update(sql, fridge.getType(), fridge.getDescription(), fridge.getDiscount(), fridge.getDefect(), fridge.getPrice(), fridge.getEnergy(), fridge.getRegistered(), fridge.getNumber());
        return findFridgeByNumber(fridge.getNumber());
    }

    public Fridge deleteFridge(Integer number) {
        Fridge fridgeDelete = findFridgeByNumber(number);
        String sql = "DELETE FROM FRIDGE WHERE number = ?";
        jdbcTemplate.update(sql, fridgeDelete.getNumber());
        return fridgeDelete;
    }

    public Fridge insertFridge(Fridge fridge){
        String sql = "INSERT INTO FRIDGE(number, type, description, discount, defect, price, energy, registered) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, fridge.getNumber(), fridge.getType(), fridge.getDescription(), fridge.getDiscount(), fridge.getDefect(), fridge.getPrice(), fridge.getEnergy(), fridge.getRegistered());
        return findFridgeByNumber(fridge.getNumber());
    }

    public List<Fridge> readALL() {
        return jdbcTemplate.query("SELECT * FROM FRIDGE",(resultSet, rowNum) -> mapRow(resultSet));
    }

    public void deleteALL() {
        String sql = "DELETE FROM FRIDGE";
        jdbcTemplate.update(sql);
    }

    private Fridge mapRow(ResultSet rs) throws SQLException {
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
