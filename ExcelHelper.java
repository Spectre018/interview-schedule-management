package com.login.demo.helper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


import com.login.demo.model.Feedback;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Satisfaction Level", "Recommendation Level","Favourite Aspect", "Improvement Suggestions" };
    static String SHEET = "Feedback";

    public static ByteArrayInputStream tutorialsToExcel(List<Feedback> feedbacks) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Feedback feedback : feedbacks) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(feedback.getId());
                row.createCell(1).setCellValue(feedback.getSatisfactionLevel());
                row.createCell(2).setCellValue(feedback.getRecommendationLevel());
                row.createCell(3).setCellValue(feedback.getFavAspect());
                row.createCell(4).setCellValue(feedback.getImprovementAspect());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}