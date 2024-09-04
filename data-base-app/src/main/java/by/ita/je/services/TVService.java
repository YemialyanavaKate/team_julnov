package by.ita.je.services;

import by.ita.je.models.TV;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TVService {

    private final JdbcTemplate jdbcTemplate;
    public TVService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public TV findTVByNumber(Integer number){
        String sql = "SELECT * FROM TV WHERE number = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<TV>() {
            @Override
            public TV mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        }, number);
    }

    public TV updateTV(TV tv){
        String sql = "UPDATE TV SET type = 'Plasma', brand = 'Philips', discount = false, diagonal = 42, price = '7000.01', energy = 'A', registered = '2024-08-08 08:30:54+02' WHERE number = 5";
        jdbcTemplate.update(sql);
        return findTVByNumber(tv.getNumber());
    }

    public TV deleteTV(Integer number) {
        TV tvDelete = findTVByNumber(number);
        String sql = "DELETE FROM TV WHERE number = 5";
        jdbcTemplate.update(sql);
        return tvDelete;
    }

    public TV insertTV(TV tv){
        String sql = "INSERT INTO TV (number, type, brand, discount, diagonal, price, energy, registered) VALUES (5, 'LED', 'Thomson', true, 18, '1555.18', 'A', '2024-09-02 09:09:54+02')";
        jdbcTemplate.update(sql);
        return findTVByNumber(tv.getNumber());
    }

    public List<Map<String, Object>> readALL() {
        return jdbcTemplate.queryForList("SELECT * FROM TV");
    }
}
