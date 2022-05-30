package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.report.ReportClient;
import com.demon.Fitnes.repository.ReportRepository;
import com.demon.Fitnes.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final String path = "C:\\Users\\User\\Downloads";

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void exportClientReport() throws FileNotFoundException, JRException {
        List<ReportClient> reportClientList = reportRepository.getReportClients();

        File file = ResourceUtils.getFile("classpath:clients.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportClientList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Dimasic25");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\clientReport.pdf");
    }

    @Override
    public void exportServiceReport() throws FileNotFoundException, JRException {
        List<com.demon.Fitnes.model.report.ReportService> reportServices = reportRepository.getReportServices();

        File file = ResourceUtils.getFile("classpath:services.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportServices);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Dimasic25");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\serviceReport.pdf");
    }
}
