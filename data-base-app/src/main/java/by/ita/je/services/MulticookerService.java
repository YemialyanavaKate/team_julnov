package by.ita.je.services;

import by.ita.je.models.Multicooker;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MulticookerService {

    private final JdbcTemplate jdbcTemplate;
    public MulticookerService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Multicooker findMulticookerByNumber(Integer number){
        String sql = "SELECT * FROM MULTICOOKER WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowMap)-> mapRow(resultSet), number);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Multicooker updateMulticooker(Multicooker multicooker){
        String sql = "UPDATE MULTICOOKER SET type = ?, description = ?, isTouchScreen = ?, numberModes = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
        jdbcTemplate.update(sql, multicooker.getType(), multicooker.getDescription(), multicooker.getIsTouchScreen(), multicooker.getNumberModes(), multicooker.getPrice(), multicooker.getEnergy(), multicooker.getRegistered(), multicooker.getNumber());
        return findMulticookerByNumber(multicooker.getNumber());
    }

    public Multicooker deleteMulticooker(Integer number) {
        Multicooker multicookerDelete = findMulticookerByNumber(number);
        String sql = "DELETE FROM MULTICOOKER WHERE number = ?";
        jdbcTemplate.update(sql, multicookerDelete.getNumber());
        return multicookerDelete;
    }

    public Multicooker insertMulticooker(Multicooker multicooker){
        String sql = "INSERT INTO MULTICOOKER (number, type, description, isTouchScreen, numberModes, price, energy, registered)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, multicooker.getNumber(), multicooker.getType(), multicooker.getDescription(), multicooker.getIsTouchScreen(), multicooker.getNumberModes(), multicooker.getPrice(), multicooker.getEnergy(), multicooker.getRegistered());
        return findMulticookerByNumber(multicooker.getNumber());
    }
    public List<Multicooker> readALL() {
        return jdbcTemplate.query("SELECT * FROM MULTICOOKER", (rs, rowNum) -> mapRow(rs));
    }

    public void deleteALL() {
        String sql = "DELETE FROM MULTICOOKER";
        jdbcTemplate.update(sql);
    }

    private Multicooker mapRow(ResultSet rs) throws SQLException {
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
