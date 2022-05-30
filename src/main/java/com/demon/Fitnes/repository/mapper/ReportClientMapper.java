package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.report.ReportClient;
import org.springframework.jdbc.core.RowMapper;

public class ReportClientMapper {

    public static final RowMapper<ReportClient> MAPPER = (rs, rowNum) -> {
        ReportClient reportClient = new ReportClient();
        reportClient.setLogin(rs.getString("client_login"));
        reportClient.setFirstName(rs.getString("client_first_name"));
        reportClient.setMiddleName(rs.getString("client_middle_name"));
        reportClient.setLastName(rs.getString("client_second_name"));
        reportClient.setPhone(rs.getString("client_phone"));
        reportClient.setCountSubscriptions(rs.getInt("count_subs"));

        return reportClient;
    };
}
