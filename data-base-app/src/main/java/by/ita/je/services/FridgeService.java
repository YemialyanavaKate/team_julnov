package by.ita.je.services;

import by.ita.je.models.Fridge;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.List;
import java.util.Map;

@Service
public class FridgeService {

    private final JdbcTemplate jdbcTemplate;
    public FridgeService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public Fridge findFridgeByNumber(Integer number){
        String sql = "SELECT * FROM FRIDGE WHERE number = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Fridge>() {
            @Override
            public Fridge mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        }, number);
    }

    public Fridge updateFridge(Fridge fridge){
        String sql = "UPDATE FRIDGE SET type = 'slim', description = 'superSlim', discount = false, defect = false, price = '1000.8', energy = 'A', registered = '2010-10-10 10:10:54+02' WHERE number = 5";
        jdbcTemplate.update(sql);
        return findFridgeByNumber(fridge.getNumber());
    }

    public Fridge deleteFridge(Integer number) {
        Fridge fridgeDelete = findFridgeByNumber(number);
        String sql = "DELETE FROM FRIDGE WHERE number = 5";
        jdbcTemplate.update(sql);
        return fridgeDelete;
    }
    public Fridge insertFridge(Fridge fridge){
        String sql = "INSERT INTO FRIDGE (number, type, description, discount, defect, price, energy, registered) VALUES (5, 'noFrost', 'super', true, false, '5000.88', 'A', '2010-10-10 10:10:54+02')";
        jdbcTemplate.update(sql);
        return findFridgeByNumber(fridge.getNumber());
    }

    public List<Map<String, Object>> readALL() {
        return jdbcTemplate.queryForList("SELECT * FROM FRIDGE");
    }
}
