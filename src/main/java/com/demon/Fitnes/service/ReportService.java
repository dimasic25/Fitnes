package com.demon.Fitnes.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ReportService {

    void exportClientReport() throws FileNotFoundException, JRException;

    void exportServiceReport() throws FileNotFoundException, JRException;
}
