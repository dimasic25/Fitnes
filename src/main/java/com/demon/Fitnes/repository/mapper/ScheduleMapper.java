package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.converter.WeekdayConverter;
import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.model.Schedule;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.builder.ScheduleBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Service service = new Service();
        service.setId(rs.getLong("service_id"));

        Coach coach = new Coach();
        coach.setLogin(rs.getString("coach_login"));

        try {
            return ScheduleBuilder.aSchedule()
                    .id(rs.getLong("schedule_id"))
                    .coach(coach)
                    .service(service)
                    .startTime(rs.getTime("start_time"))
                    .endTime(rs.getTime("end_time"))
                    .weekday(WeekdayConverter.fromInt(rs.getInt("weekday")))
                    .groupNumber(rs.getInt("schedule_group_number"))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
