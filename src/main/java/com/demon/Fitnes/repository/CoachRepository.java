package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.repository.mapper.CoachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CoachRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CoachRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Coach> findByLogin(String login) {
        final String sql = """
                    SELECT coach_login, coach_first_name, coach_last_name, coach_middle_name, coach_birthdate, coach_phone, coach_experience
                    FROM coach
                    WHERE coach_login = ?;
                    """;
        return jdbcTemplate.getJdbcTemplate().query(sql, CoachMapper.MAPPER, login)
                .stream().findAny();
    }

    public List<Coach> findAllClients() {
        final String sql = """
                    SELECT coach_login, coach_first_name, coach_last_name, coach_middle_name, coach_birthdate, coach_phone, coach_experience
                    FROM coach;
                    """;
        return jdbcTemplate.query(sql, CoachMapper.MAPPER);
    }

    public String insert(Coach coach) {
        final String sql = "INSERT INTO coach (coach_login, coach_first_name, coach_last_name, coach_middle_name, coach_birthdate, coach_phone, coach_experience)" +
                " VALUES(:login, :firstName, :secondName, :middleName, :birthday, :phone, :experience)";

        var params = new MapSqlParameterSource()
                .addValue("login", coach.getLogin())
                .addValue("firstName", coach.getFirstName())
                .addValue("secondName", coach.getSecondName())
                .addValue("middleName", coach.getMiddleName())
                .addValue("birthday", coach.getBirthdate())
                .addValue("phone", coach.getPhone())
                .addValue("experience", coach.getExperience());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (String) keyHolder.getKeys().get("login");
    }

    public void update(Coach coach) {
        final String sql = "UPDATE coach SET coach_first_name = :firstName," +
                " coach_last_name = :secondName, coach_middle_name = :middleName, coach_birthdate = :birthday," +
                " coach_phone = :phone, coach_experience = :experience" +
                " WHERE coach_login = :login";

        var params = new MapSqlParameterSource()
                .addValue("login", coach.getLogin())
                .addValue("firstName", coach.getFirstName())
                .addValue("secondName", coach.getSecondName())
                .addValue("middleName", coach.getMiddleName())
                .addValue("birthday", coach.getBirthdate())
                .addValue("phone", coach.getPhone())
                .addValue("experience", coach.getExperience());

        jdbcTemplate.update(sql, params);
    }
}
