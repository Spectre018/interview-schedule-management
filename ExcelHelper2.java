package com.login.demo.helper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


import com.login.demo.model.CandidateAppliedJob;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelHelper2 {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Job Id", "Candidate Id","Hr Id","Candidate Skills", "Mode of Interview" };
    static String SHEET = "CandidateAppliedJob";

    public static ByteArrayInputStream tutorialsToExcel(List<CandidateAppliedJob> candidateAppliedJobs) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (CandidateAppliedJob candidateAppliedJob : candidateAppliedJobs) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(candidateAppliedJob.getId());
                row.createCell(1).setCellValue(candidateAppliedJob.getJobId());
                row.createCell(2).setCellValue(candidateAppliedJob.getCandidateId());
                row.createCell(3).setCellValue(candidateAppliedJob.getHrId());
                row.createCell(4).setCellValue(candidateAppliedJob.getCandidateSkills());
                row.createCell(5).setCellValue(candidateAppliedJob.getModeOfInterview());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}