package com.javacodepractice.excelfile.service;

import com.javacodepractice.excelfile.entity.Employee;
import com.javacodepractice.excelfile.helper.ExcelHelper;
import com.javacodepractice.excelfile.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

    private final EmployeeRepository employeeRepository;

    public ExcelUploadServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(MultipartFile file){
        try {
            List<Employee> employees = ExcelHelper.parseExcel(file.getInputStream());
            employeeRepository.saveAll(employees);
        }catch (IOException e){
            throw new RuntimeException("Failed to store excel data: " + e.getMessage());
        }


    }
}
