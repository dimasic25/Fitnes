package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.builder.ServiceBuilder;
import org.springframework.jdbc.core.RowMapper;

public class ServiceMapper {

    public static final RowMapper<Service> MAPPER  = (rs, rowNum) ->
            ServiceBuilder.aService()
                    .id(rs.getLong("service_id"))
                    .description(rs.getString("description"))
                    .name(rs.getString("name"))
                    .place(rs.getString("place"))
                    .price(rs.getDouble("service_price"))
                    .build();
}

