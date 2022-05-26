package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.repository.mapper.SubscriptionMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public SubscriptionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Subscription> findAllSubs() {
        final String sql = """
                SELECT subscription_id, client_login, start_date, end_date, closed, subscription_price
                FROM subscription
                ORDER BY client_login, end_date DESC;
                """;

        return jdbcTemplate.query(sql, SubscriptionMapper.MAPPER);
    }

    public List<Subscription> findSubsByClient(String login) {
        final String sql = """
                SELECT subscription_id, client_login, start_date, end_date, closed, subscription_price
                FROM subscription
                WHERE client_login = :login and closed = false 
                ORDER BY client_login, end_date DESC;
                """;

        var params = new MapSqlParameterSource()
                .addValue("login", login);

        return jdbcTemplate.query(sql, params, SubscriptionMapper.MAPPER);
    }

    public Optional<Subscription> findById(Long id) {
        final String sql = """
                SELECT subscription_id, client_login, start_date, end_date, closed, subscription_price
                FROM subscription
                WHERE subscription_id = :id;
                """;

        var params = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcTemplate.query(sql, params, SubscriptionMapper.MAPPER).stream().findAny();
    }

    public void insert(Subscription subscription) throws ParseException {
        final String sql = """
                 INSERT INTO subscription (client_login, start_date, end_date, closed, subscription_price)
                 VALUES(:login, :start ,:end ,:closed,:price);
                """;

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date start = format.parse(subscription.getStartDate());

        Date end = format.parse(subscription.getEndDate());

        var params = new MapSqlParameterSource()
                .addValue("login", subscription.getClientLogin())
                .addValue("start", start)
                .addValue("end", end)
                .addValue("closed", false)
                .addValue("price", subscription.getPrice());

        jdbcTemplate.update(sql, params);
    }

    public void update(Subscription subscription) throws ParseException {
        final String sql = """
                 UPDATE subscription SET client_login = :login, start_date = :start,
                    end_date = :end, closed = :closed, subscription_price = :price
                 WHERE subscription_id = :id;
                """;

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date start = format.parse(subscription.getStartDate());

        Date end = format.parse(subscription.getEndDate());

        var params = new MapSqlParameterSource()
                .addValue("id", subscription.getId())
                .addValue("login", subscription.getClientLogin())
                .addValue("start", start)
                .addValue("end", end)
                .addValue("closed", subscription.isClosed())
                .addValue("price", subscription.getPrice());

        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) {
        final String sql = """
                DELETE FROM subscription
                 WHERE subscription_id = :id;
                """;

        var params = new MapSqlParameterSource()
                .addValue("id", id);

        jdbcTemplate.update(sql, params);
    }
}
