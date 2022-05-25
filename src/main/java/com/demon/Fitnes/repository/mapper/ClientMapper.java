package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.builder.ClientBuilder;
import org.springframework.jdbc.core.RowMapper;

public class ClientMapper {

    public static final RowMapper<Client> MAPPER = (rs, rowNum) ->
            ClientBuilder.aClient()
                    .login(rs.getString("client_login"))
                    .firstName(rs.getString("client_first_name"))
                    .lastName(rs.getString("client_second_name"))
                    .middleName(rs.getString("client_middle_name"))
                    .birthdate(rs.getDate("client_birthday"))
                    .phone(rs.getString("client_phone"))
                    .build();

    public static final RowMapper<Client> MAPPER1 = (rs, rowNum) ->
            ClientBuilder.aClient()
                    .login(rs.getString("client_login"))
                    .password(rs.getString("client_password"))
                    .build();
}
