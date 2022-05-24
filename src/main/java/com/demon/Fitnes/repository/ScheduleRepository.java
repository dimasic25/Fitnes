package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Schedule;
import com.demon.Fitnes.repository.mapper.ScheduleMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ScheduleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Schedule> findAllSchedules() {
        final String sql = """
                    SELECT schedule_id, service_id, coach_login, start_time, end_time, weekday, schedule_group_number
                    FROM schedule;
                    """;
        return jdbcTemplate.query(sql, new ScheduleMapper());
    }
}
