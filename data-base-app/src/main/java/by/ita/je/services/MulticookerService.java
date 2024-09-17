package by.ita.je.services;

import by.ita.je.mappers.RowMapperMulticooker;
import by.ita.je.models.Multicooker;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MulticookerService {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapperMulticooker rowMapperMulticooker;
    public MulticookerService(JdbcTemplate jdbcTemplate, RowMapperMulticooker rowMapperMulticooker){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapperMulticooker = rowMapperMulticooker;
    }

    public Multicooker findMulticookerByNumber(Integer number){
        String sql = "SELECT * FROM MULTICOOKER WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowMap)-> rowMapperMulticooker.mapRow(resultSet), number);
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
        return jdbcTemplate.query("SELECT * FROM MULTICOOKER", (rs, rowNum) -> rowMapperMulticooker.mapRow(rs));
    }

    public void deleteALL() {
        String sql = "DELETE FROM MULTICOOKER";
        jdbcTemplate.update(sql);
    }
}
