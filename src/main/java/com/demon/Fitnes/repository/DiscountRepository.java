package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.repository.mapper.DiscountMapper;
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

    // todo добавить клиента к запросу
    public List<Discount> findAllDiscounts() {
        final String sql = """
                    SELECT discount_id, service_id, amount, discount_number_visits
                    FROM discount;
                    """;
        return jdbcTemplate.query(sql, new DiscountMapper());
    }

    public List<Discount> findDiscountsByClient(String clientLogin) {
        final String sql = """
                    SELECT discount.discount_id, service_id, amount, discount_number_visits
                    FROM discount                 
                    INNER JOIN client_discount
                    ON client_discount.client_login = :clientLogin
                    WHERE client_discount.discount_id = discount.discount_id;               
                    """;

        var params = new MapSqlParameterSource()
                .addValue("clientLogin", clientLogin);

        return jdbcTemplate.query(sql, params, new DiscountMapper());
    }
}
