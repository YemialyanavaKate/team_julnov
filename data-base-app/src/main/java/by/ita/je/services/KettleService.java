package by.ita.je.services;

import by.ita.je.mappers.RowMapperKettle;
import by.ita.je.models.Kettle;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KettleService {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapperKettle rowMapperKettle;
    public KettleService(JdbcTemplate jdbcTemplate, RowMapperKettle rowMapperKettle){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapperKettle = rowMapperKettle;
    }
    public Kettle findKettleByNumber(Integer number){
        String sql = "SELECT * FROM KETTLE WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> rowMapperKettle.mapRow(resultSet), number);
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
        return jdbcTemplate.query("SELECT * FROM KETTLE",(resultSet, rowNum) -> rowMapperKettle.mapRow(resultSet));
    }

    public void deleteAll() {
        String sql = "DELETE FROM KETTLE";
        jdbcTemplate.update(sql);
    }
}
