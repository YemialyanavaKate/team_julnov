package by.ita.je.services;

import by.ita.je.mappers.RowMapperTV;
import by.ita.je.models.TV;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TVService {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapperTV rowMapperTV;
    public TVService(JdbcTemplate jdbcTemplate, RowMapperTV rowMapperTV){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapperTV = rowMapperTV;
    }

    public TV findTVByNumber(Integer number){
        String sql = "SELECT * FROM TV WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rowMapperTV.mapRow(rs), number);
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
        return jdbcTemplate.query("SELECT * FROM TV", ((rs, rowNum) -> rowMapperTV.mapRow(rs)));
    }

    public void deleteAll() {
        String sql = "DELETE FROM TV";
        jdbcTemplate.update(sql);
    }
}
