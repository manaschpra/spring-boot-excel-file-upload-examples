package com.javacodepractice.excelfile.helper;

import com.javacodepractice.excelfile.entity.Employee;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ExcelHelper {

    public static  boolean isExcelFormat(MultipartFile file){
        return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                file.getContentType().equals("application/vnd.ms-excel");
    }

    public static List<Employee> parseExcel(InputStream is){
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Employee> employeeList = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;
                Employee employee = new Employee();
                employee.setName(row.getCell(0).getStringCellValue());
                employee.setEmail(row.getCell(1).getStringCellValue());
                employee.setPhone(row.getCell(2).getStringCellValue());
                employee.setDepartment(row.getCell(3).getStringCellValue());
                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());

        }
    }
}
