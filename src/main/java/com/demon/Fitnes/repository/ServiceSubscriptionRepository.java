package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.repository.mapper.ServiceSubscriptionMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceSubscriptionRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public ServiceSubscriptionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ServiceSubscription> findAllServiceSubs() {
        final String sql = """
                SELECT subscription_id, service_id, number_sessions, number_visits, group_number
                FROM service_subscription;
                """;

        return jdbcTemplate.query(sql, new ServiceSubscriptionMapper());
    }

    public List<ServiceSubscription> findServiceSubsBySub(Long subId) {
        final String sql = """
                SELECT subscription_id, service_id, number_sessions, number_visits, group_number
                FROM service_subscription
                WHERE subscription_id = :subId;
                """;

        var params = new MapSqlParameterSource()
                .addValue("subId", subId);

        return jdbcTemplate.query(sql, params, new ServiceSubscriptionMapper());
    }
}
