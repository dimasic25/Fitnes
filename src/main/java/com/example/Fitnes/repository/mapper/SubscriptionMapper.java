package com.example.Fitnes.repository.mapper;

import com.example.Fitnes.model.Subscription;
import com.example.Fitnes.model.builder.SubscriptionBuilder;
import org.springframework.jdbc.core.RowMapper;

public class SubscriptionMapper {

    public static final RowMapper<Subscription> MAPPER = (rs, rowNum) ->
            SubscriptionBuilder.aSubscription()
                    .id(rs.getLong("subscription_id"))
                    .clientLogin(rs.getString("client_login"))
                    .closed(rs.getBoolean("closed"))
                    .startDate(rs.getDate("start_date"))
                    .endDate(rs.getDate("end_date"))
                    .price(rs.getDouble("subscription_price"))
                    .build();
}
