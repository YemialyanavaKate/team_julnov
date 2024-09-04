package by.ita.je.services;

import by.ita.je.models.Multicooker;
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
public class MulticookerService {

    private final JdbcTemplate jdbcTemplate;
    public MulticookerService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Multicooker findMulticookerByNumber(Integer number){
        String sql = "SELECT * FROM MULTICOOKER WHERE number = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Multicooker>() {
            @Override
            public Multicooker mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        }, number);
    }

    public Multicooker updateMulticooker(Multicooker multicooker){
        String sql = "UPDATE MULTICOOKER SET type = 'NonRemovablePanels', description = 'Grill', isTouchScreen = false, numberModes = 7001, price = '100.99', energy = 'A', registered = '2010-10-10 10:10:54+02' WHERE number = 5";
        jdbcTemplate.update(sql);
        return findMulticookerByNumber(multicooker.getNumber());
    }

    public Multicooker deleteMulticooker(Integer number) {
        Multicooker multicookerDelete = findMulticookerByNumber(number);
        String sql = "DELETE FROM MULTICOOKER WHERE number = 5";
        jdbcTemplate.update(sql);
        return multicookerDelete;
    }

    public Multicooker insertMulticooker(Multicooker multicooker){
        String sql = "INSERT INTO MULTICOOKER (number, type, description, isTouchScreen, numberModes, price, energy, registered) VALUES (5, 'RemovablePanels', 'for waffles', true, 18001, '79.18', 'A', '2024-03-07 07:07:54+02')";
        jdbcTemplate.update(sql);
        return findMulticookerByNumber(multicooker.getNumber());
    }
    public List<Map<String, Object>> readALL() {
        return jdbcTemplate.queryForList("SELECT * FROM MULTICOOKER");
    }
}
