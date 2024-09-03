package by.ita.je.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FridgeService {

    private final JdbcTemplate jdbcTemplate;
    public FridgeService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> readALL() {
        return jdbcTemplate.queryForList("SELECT * FROM FRIDGE");
    }
}
