package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Schedule;
import com.demon.Fitnes.repository.mapper.ScheduleMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    public List<Schedule> findClientSchedules(String clientLogin) {
        final String sql = """
                    SELECT DISTINCT schedule_id, s.service_id, coach_login, start_time, end_time, weekday, schedule_group_number
                    FROM schedule sc
                    INNER JOIN service s ON s.service_id = sc.service_id
                    INNER JOIN service_subscription ser_sub ON ser_sub.service_id = s.service_id and ser_sub.group_number = schedule_group_number
                    INNER JOIN subscription sub ON sub.subscription_id = ser_sub.subscription_id and sub.closed = false 
                    INNER JOIN client c ON sub.client_login = c.client_login and c.client_login = :login
                    ORDER BY weekday, start_time;
                    """;

        var params = new MapSqlParameterSource()
                .addValue("login", clientLogin);

        return jdbcTemplate.query(sql, params, new ScheduleMapper());
    }
}
