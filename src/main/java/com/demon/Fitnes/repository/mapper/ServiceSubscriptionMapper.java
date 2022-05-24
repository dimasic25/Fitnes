package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.model.builder.ServiceSubscriptionBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSubscriptionMapper implements RowMapper<ServiceSubscription> {

    @Override
    public ServiceSubscription mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getLong("subscription_id"));

        Service service = new Service();
        service.setId(rs.getLong("service_id"));

        return ServiceSubscriptionBuilder.aServiceSubscription()
                .subscription(subscription)
                .service(service)
                .numberSessions(rs.getInt("number_sessions"))
                .numberVisits(rs.getInt("number_visits"))
                .groupNumber(rs.getInt("group_number"))
                .build();
    }
}
