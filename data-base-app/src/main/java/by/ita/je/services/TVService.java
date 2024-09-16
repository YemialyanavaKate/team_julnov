package by.ita.je.services;

import by.ita.je.models.TV;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TVService {

    private final JdbcTemplate jdbcTemplate;
    public TVService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public TV findTVByNumber(Integer number){
        String sql = "SELECT * FROM TV WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRow(rs), number);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public TV updateTV(TV tv){
        String sql = "UPDATE TV SET type = ?, brand = ?, discount = ?, diagonal = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
        jdbcTemplate.update(sql, tv.getType(), tv.getBrand(), tv.getDiscount(), tv.getDiagonal(),tv.getPrice(), tv.getEnergy(), tv.getRegistered(), tv.getNumber());
        return findTVByNumber(tv.getNumber());
    }

    public TV deleteTV(Integer number) {
        TV tvDelete = findTVByNumber(number);
        String sql = "DELETE FROM TV WHERE number = ?";
        jdbcTemplate.update(sql, tvDelete.getNumber());
        return tvDelete;
    }

    public TV insertTV(TV tv){
        String sql = "INSERT INTO TV (number, type, brand, discount, diagonal, price, energy, registered)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, tv.getNumber(), tv.getType(), tv.getBrand(), tv.getDiscount(), tv.getDiagonal(), tv.getPrice(), tv.getEnergy(), tv.getRegistered());
        return findTVByNumber(tv.getNumber());
    }

    public List<TV> readALL() {
        return jdbcTemplate.query("SELECT * FROM TV", ((rs, rowNum) -> mapRow(rs)));
    }

    public void deleteAll() {
        String sql = "DELETE FROM TV";
        jdbcTemplate.update(sql);
    }

    private TV mapRow(ResultSet rs) throws SQLException {
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
