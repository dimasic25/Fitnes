package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.model.builder.CoachBuilder;
import org.springframework.jdbc.core.RowMapper;

public class CoachMapper {

    public static final RowMapper<Coach> MAPPER  = (rs, rowNum) ->
            CoachBuilder.newBuilder()
                    .birthdate(rs.getDate("coach_birthdate"))
                    .login(rs.getString("coach_login"))
                    .firstName(rs.getString("coach_first_name"))
                    .middleName(rs.getString("coach_middle_name"))
                    .phone(rs.getString("coach_phone"))
                    .secondName(rs.getString("coach_last_name"))
                    .experience(rs.getLong("coach_experience"))
                    .build();
}
