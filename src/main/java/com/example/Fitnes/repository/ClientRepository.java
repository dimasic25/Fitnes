package com.example.Fitnes.repository;

import com.example.Fitnes.model.Client;
import com.example.Fitnes.repository.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ClientRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Client> findByLoginAndPassword(Client client) {
        final String sql = """
                SELECT client_login, client_password
                FROM client
                WHERE client_login = :login AND client_password = :password;
                """;

        var params = new MapSqlParameterSource()
                .addValue("login", client.getLogin())
                .addValue("password", client.getPassword());

        return jdbcTemplate.query(sql, params, ClientMapper.MAPPER1)
                .stream().findAny();

    }

    public Optional<Client> findByLogin(String login) {
        final String sql = """
                SELECT client_login, client_password
                FROM client
                WHERE client_login = :login;
                """;

        var params = new MapSqlParameterSource()
                .addValue("login", login);

        return jdbcTemplate.query(sql, params, ClientMapper.MAPPER1)
                .stream().findAny();
    }

    public List<Client> getAllClients() {
        final String sql = """
                SELECT c.client_login, c.client_first_name,  c.client_second_name, c.client_middle_name, c.client_birthday,
                 count(subscription_id) AS count_sub, sum(subscription_price) AS price from client c
                	INNER JOIN subscription s ON s.client_login = c.client_login
                	GROUP BY(c.client_login);
                """;

        return jdbcTemplate.query(sql, ClientMapper.MAPPER);
    }

    public void deleteUser(String login) {
        final String sql = """
                CALL delete_user(:login);
                """;

        var params = new MapSqlParameterSource()
                .addValue("login", login);

        jdbcTemplate.update(sql, params);
    }
}
