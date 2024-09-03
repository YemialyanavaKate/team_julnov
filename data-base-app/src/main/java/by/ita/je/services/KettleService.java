package by.ita.je.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KettleService {

    private final JdbcTemplate jdbcTemplate;
    public KettleService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> readALL() {
        return jdbcTemplate.queryForList("SELECT * FROM KETTLE");
    }
}
