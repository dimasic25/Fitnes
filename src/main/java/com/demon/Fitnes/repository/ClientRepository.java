package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.repository.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
                 c.client_phone from client c;            
                """;

        return jdbcTemplate.query(sql, ClientMapper.MAPPER);
    }

    public void deleteClient(String login) {
        final String sql = """
                CALL delete_user(:login);
                """;

        var params = new MapSqlParameterSource()
                .addValue("login", login);

        jdbcTemplate.update(sql, params);
    }

    public String insert(Client client) {
        final String sql = "INSERT INTO client (client_login, client_first_name, client_second_name, client_middle_name, client_birthday, client_phone, client_password)" +
                " VALUES(:login, :firstName, :secondName, :middleName, :birthday, :phone, :password)";

        var params = new MapSqlParameterSource()
                .addValue("login", client.getLogin())
                .addValue("firstName", client.getFirstName())
                .addValue("secondName", client.getLastName())
                .addValue("middleName", client.getMiddleName())
                .addValue("birthday", client.getBirthdate())
                .addValue("phone", client.getPhone())
                .addValue("password", client.getPassword());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (String) keyHolder.getKeys().get("login");
    }

    public Optional<Client> findAdminByLogin(String login) {
        final String sql = """
                SELECT C.client_login, client_password
                FROM client C
                INNER JOIN admin A
                ON C.client_login = A.client_login
                WHERE C.client_login = :login;
                """;

        var params = new MapSqlParameterSource()
                .addValue("login", login);

        return jdbcTemplate.query(sql, params, ClientMapper.MAPPER1)
                .stream().findAny();
    }
}
