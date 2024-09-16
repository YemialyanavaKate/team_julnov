package by.ita.je.services;

import by.ita.je.models.Kettle;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class KettleService {

    private final JdbcTemplate jdbcTemplate;
    public KettleService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public Kettle findKettleByNumber(Integer number){
        String sql = "SELECT * FROM KETTLE WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> mapRow(resultSet), number);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public Kettle updateKettle(Kettle kettle){
        String sql = "UPDATE KETTLE SET type = ?, color = ?, isElectric = ?, isInduction = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
        jdbcTemplate.update(sql, kettle.getType(), kettle.getColor(), kettle.getIsElectric(), kettle.getIsInduction(), kettle.getPrice(), kettle.getEnergy(), kettle.getRegistered(), kettle.getNumber());
        return findKettleByNumber(kettle.getNumber());
    }

    public Kettle deleteKettle(Integer number) {
        Kettle kettleDelete = findKettleByNumber(number);
        if(kettleDelete == null){
            return null;
        }
        String sql = "DELETE FROM KETTLE WHERE number = ?";
        jdbcTemplate.update(sql, kettleDelete.getNumber());
        return kettleDelete;
    }

    public Kettle insertKettle(Kettle kettle){
        String sql = "INSERT INTO KETTLE (number, type, color, isElectric, isInduction, price, energy, registered) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, kettle.getNumber(), kettle.getType(), kettle.getColor(), kettle.getIsElectric(), kettle.getIsInduction(), kettle.getPrice(), kettle.getEnergy(), kettle.getRegistered());
        return findKettleByNumber(kettle.getNumber());
    }

    public List<Kettle> readALL() {
        return jdbcTemplate.query("SELECT * FROM KETTLE",(resultSet, rowNum) -> mapRow(resultSet));
    }

    public void deleteAll() {
        String sql = "DELETE FROM KETTLE";
        jdbcTemplate.update(sql);
    }


    private Kettle mapRow(ResultSet resultSet) throws SQLException {
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
