package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.repository.mapper.DiscountMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DiscountRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Discount> findAllDiscounts() {
        final String sql = """
                    SELECT d.discount_id, service_id, amount, discount_number_visits
                    FROM discount d               
                    ORDER BY service_id, amount;              
                    """;
        return jdbcTemplate.query(sql, DiscountMapper.MAPPER);
    }

    public List<Discount> findClientDiscounts() {
        final String sql = """
                    SELECT d.discount_id, cd.client_login, service_id, amount, discount_number_visits
                    FROM discount d
                    INNER JOIN client_discount cd
                    ON cd.discount_id = d.discount_id;
                    """;
        return jdbcTemplate.query(sql, DiscountMapper.MAPPER_WITH_CLIENT);
    }

    public List<Discount> findDiscountsByClient(String clientLogin) {
        final String sql = """
                    SELECT D.discount_id, service_id, amount, discount_number_visits
                    FROM discount D                 
                    INNER JOIN client_discount CD
                    ON CD.discount_id = D.discount_id
                    WHERE CD.client_login = :clientLogin;               
                    """;

        var params = new MapSqlParameterSource()
                .addValue("clientLogin", clientLogin);

        return jdbcTemplate.query(sql, params, DiscountMapper.MAPPER);
    }

    public void insert(Discount discount) {
        final String sql = """
                 INSERT INTO client_discount (discount_id, client_login)
                 VALUES(:discount_id, :login);
                """;

        var params = new MapSqlParameterSource()
                .addValue("discount_id", discount.getId())
                .addValue("login", discount.getClientLogin());

        jdbcTemplate.update(sql, params);
    }

    public Long existsDiscount(String clientLogin, Long id) {
        try {
            final String sql = """
                SELECT discount_id
                FROM client_discount
                WHERE discount_id = :id and client_login = :login;
                """;

            var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("login", clientLogin);

            return jdbcTemplate.queryForObject(sql, params, Long.class);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
            }
    }

    public void delete(String login, Long id) {
        final String sql = """
                DELETE FROM client_discount
                WHERE discount_id = :id and client_login = :login;
                """;

        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("login", login);

        jdbcTemplate.update(sql, params);
    }
}
