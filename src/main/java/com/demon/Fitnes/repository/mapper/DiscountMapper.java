package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.builder.DiscountBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountMapper implements RowMapper<Discount> {

    @Override
    public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {

        Service service = new Service();
        service.setId(rs.getLong("service_id"));

        return DiscountBuilder.aDiscount()
                .id(rs.getLong("discount_id"))
                .service(service)
                .amount(rs.getInt("amount"))
                .numberVisits(rs.getInt("discount_number_visits"))
                .build();
    }
}
