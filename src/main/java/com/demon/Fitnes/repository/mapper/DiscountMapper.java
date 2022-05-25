package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.builder.DiscountBuilder;
import org.springframework.jdbc.core.RowMapper;

public class DiscountMapper {

    public static final RowMapper<Discount> MAPPER = (rs, rowNum) -> {
        Service service = new Service();
        service.setId(rs.getLong("service_id"));

        return DiscountBuilder.aDiscount()
                .id(rs.getLong("discount_id"))
                .service(service)
                .amount(rs.getInt("amount"))
                .numberVisits(rs.getInt("discount_number_visits"))
                .build();
    };

    public static final RowMapper<Discount> MAPPER_WITH_CLIENT = (rs, rowNum) -> {
        Service service = new Service();
        service.setId(rs.getLong("service_id"));

        return DiscountBuilder.aDiscount()
                .id(rs.getLong("discount_id"))
                .service(service)
                .amount(rs.getInt("amount"))
                .numberVisits(rs.getInt("discount_number_visits"))
                .clientLogin(rs.getString("client_login"))
                .build();
    };
}
