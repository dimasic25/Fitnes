package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.report.ReportClient;
import com.demon.Fitnes.model.report.ReportService;
import com.demon.Fitnes.repository.mapper.ReportClientMapper;
import com.demon.Fitnes.repository.mapper.ReportServiceMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ReportRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReportClient> getReportClients() {
        final String sql = """
                   SELECT C.client_login, client_first_name, client_second_name, client_middle_name,
                                       client_phone, count(subscription_id) AS count_subs
                                FROM client C
                                         INNER JOIN subscription S ON C.client_login = S.client_login
                                GROUP BY C.client_login
                                ORDER BY count_subs DESC;            
                    """;

        return jdbcTemplate.query(sql, ReportClientMapper.MAPPER);
    }

    public List<ReportService> getReportServices() {
        final String sql = """
                   SELECT name, count(SS.subscription_id) AS count_subs, sum(service_price * number_sessions) AS price
                                FROM service S 
                                         INNER JOIN service_subscription SS ON S.service_id = SS.service_id
                                GROUP BY name
                                ORDER BY price DESC, count_subs DESC;            
                    """;

        return jdbcTemplate.query(sql, ReportServiceMapper.MAPPER);
    }
}
