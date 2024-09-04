package by.ita.je.services;

import by.ita.je.models.Kettle;
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
public class KettleService {

    private final JdbcTemplate jdbcTemplate;
    public KettleService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public Kettle findKettleByNumber(Integer number){
        String sql = "SELECT * FROM KETTLE WHERE number = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Kettle>() {
            @Override
            public Kettle mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer number = rs.getInt("number");
                String type = rs.getString("type");
                String color = rs.getString("color");
                boolean isElectric = rs.getBoolean("isElectric");
                boolean isInduction = rs.getBoolean("isInduction");
                BigDecimal price = rs.getBigDecimal("price");
                Character energy = rs.getString("energy").charAt(0);
                ZonedDateTime registered = rs.getObject("registered", ZonedDateTime.class);

                return new Kettle( type, color, isElectric, isInduction, price, number, energy, registered);
            }
        }, number);
    }

    public Kettle updateKettle(Kettle kettle){
        String sql = "UPDATE KETTLE SET type = 'glass', color = 'pink', isElectric = true, isInduction = true, price = '122.22', energy = 'A', registered = '2011-11-11 11:11:11+02' WHERE number = 5";
        jdbcTemplate.update(sql);
        return findKettleByNumber(kettle.getNumber());
    }

    public Kettle deleteKettle(Integer number) {
        Kettle kettleDelete = findKettleByNumber(number);
        String sql = "DELETE FROM KETTLE WHERE number = 5";
        jdbcTemplate.update(sql);
        return kettleDelete;
    }

    public Kettle insertKettle(Kettle kettle){
        String sql = "INSERT INTO KETTLE (number, type, color, isElectric, isInduction, price, energy, registered) VALUES (5, 'steel', 'yellow', false, false, '55.55', 'B', '2010-10-10 10:10:54+02')";
        jdbcTemplate.update(sql);
        return findKettleByNumber(kettle.getNumber());
    }

    public List<Map<String, Object>> readALL() {
        return jdbcTemplate.queryForList("SELECT * FROM KETTLE");
    }
}
