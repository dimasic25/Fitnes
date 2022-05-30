package com.demon.Fitnes.repository.mapper;

import com.demon.Fitnes.model.report.ReportService;
import org.springframework.jdbc.core.RowMapper;

public class ReportServiceMapper {

    public static final RowMapper<ReportService> MAPPER = (rs, rowNum) -> {
        ReportService reportService = new ReportService();
       reportService.setServiceName(rs.getString("name"));
       reportService.setCountSubscriptions(rs.getInt("count_subs"));

        return reportService;
    };
}
