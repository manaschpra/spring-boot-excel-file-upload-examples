package com.javacodepractice.excelfile.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelUploadService {
    void save(MultipartFile file);
}
