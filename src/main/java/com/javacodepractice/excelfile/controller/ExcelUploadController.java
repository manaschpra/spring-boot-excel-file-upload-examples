package com.javacodepractice.excelfile.controller;

import com.javacodepractice.excelfile.helper.ExcelHelper;
import com.javacodepractice.excelfile.service.ExcelUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
public class ExcelUploadController {

    private final ExcelUploadService excelUploadService;

    public ExcelUploadController(ExcelUploadService excelUploadService) {
        this.excelUploadService = excelUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {

        if (!ExcelHelper.isExcelFormat(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel file");
        }
        try {
            excelUploadService.save(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }
}
