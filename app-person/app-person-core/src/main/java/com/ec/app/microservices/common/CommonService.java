package com.ec.app.microservices.common;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for common report resources
 *
 * @author arobayo
 */
@Lazy
@Service
public class CommonService implements ICommonService {

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] generateReport(List<?> reportData, String jrxmlPath) {
        try (InputStream reportStream = getClass().getClassLoader().getResourceAsStream(jrxmlPath)) {
            if (reportStream == null) {
                throw new RuntimeException("JRXML file not found.");
            }
            JasperReport report = JasperCompileManager.compileReport(reportStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("extension", "xlsx");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            return byteArrayOutputStream.toByteArray();
        } catch (JRException | IOException e) {
            throw new RuntimeException("Error generating report", e);
        }
    }
}
