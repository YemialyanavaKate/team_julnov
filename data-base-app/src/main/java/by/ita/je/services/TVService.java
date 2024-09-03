package by.ita.je.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TVService {

    private final JdbcTemplate jdbcTemplate;
    public TVService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> readALL() {
        return jdbcTemplate.queryForList("SELECT * FROM TV");
    }
}
