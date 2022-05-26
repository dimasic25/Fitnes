package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.repository.mapper.ServiceSubscriptionMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

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

    public Optional<ServiceSubscription> findByServiceAndSubscription(Long serviceId, Long subscriptionId) {
        final String sql = """
                SELECT subscription_id, service_id, number_sessions, number_visits, group_number
                FROM service_subscription
                WHERE subscription_id = :subId and service_id = :serviceId;
                """;

        var params = new MapSqlParameterSource()
                .addValue("subId", subscriptionId)
                .addValue("serviceId", serviceId);

        return jdbcTemplate.query(sql, params, new ServiceSubscriptionMapper()).stream().findAny();
    }

    public void insert(ServiceSubscription serviceSubscription) throws ParseException {
        final String sql = """
                 INSERT INTO service_subscription (subscription_id, service_id, number_sessions, number_visits, group_number)
                 VALUES(:subId, :serviceId ,:numberSessions ,:numberVisits, :groupNumber);
                """;

        var params = new MapSqlParameterSource()
                .addValue("subId", serviceSubscription.getSubscription().getId())
                .addValue("serviceId", serviceSubscription.getService().getId())
                .addValue("numberSessions", serviceSubscription.getNumberSessions())
                .addValue("numberVisits", serviceSubscription.getNumberVisits())
                .addValue("groupNumber", serviceSubscription.getGroupNumber());

        jdbcTemplate.update(sql, params);
    }

    public void update(ServiceSubscription serviceSubscription) {
        final String sql = """
                 UPDATE service_subscription SET service_id = :serviceId, number_sessions = :numberSessions,
                    number_visits = :numberVisits, group_number = :groupNumber
                 WHERE subscription_id = :subId;
                """;

        var params = new MapSqlParameterSource()
                .addValue("subId", serviceSubscription.getSubscription().getId())
                .addValue("serviceId", serviceSubscription.getService().getId())
                .addValue("numberSessions", serviceSubscription.getNumberSessions())
                .addValue("numberVisits", serviceSubscription.getNumberVisits())
                .addValue("groupNumber", serviceSubscription.getGroupNumber());

        jdbcTemplate.update(sql, params);
    }

    public void delete(Long serviceId, Long subscriptionId) {
        final String sql = """
                DELETE FROM service_subscription
                 WHERE subscription_id = :subId and service_id = :serviceId;
                """;

        var params = new MapSqlParameterSource()
                .addValue("subId", subscriptionId)
                .addValue("serviceId", serviceId);

        jdbcTemplate.update(sql, params);
    }
}
