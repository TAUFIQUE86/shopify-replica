package com.product.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    public String uploadFile(MultipartFile file, long brandId) throws IOException;
}
