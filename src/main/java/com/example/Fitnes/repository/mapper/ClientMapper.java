package com.example.Fitnes.repository.mapper;

import com.example.Fitnes.model.Client;
import com.example.Fitnes.model.builder.ClientBuilder;
import org.springframework.jdbc.core.RowMapper;

public class ClientMapper {

    public static final RowMapper<Client> MAPPER = (rs, rowNum) ->
            ClientBuilder.aClient()
                    .login(rs.getString("client_login"))
                    .numberSubs(rs.getInt("count_sub"))
                    .price(rs.getDouble("price"))
                    .firstName(rs.getString("client_first_name"))
                    .lastName(rs.getString("client_second_name"))
                    .middleName(rs.getString("client_middle_name"))
                    .birthdate(rs.getDate("client_birthday"))
                    .build();

    public static final RowMapper<Client> MAPPER1 = (rs, rowNum) ->
            ClientBuilder.aClient()
                    .login(rs.getString("client_login"))
                    .password(rs.getString("client_password"))
                    .build();
}
