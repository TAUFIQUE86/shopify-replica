package com.product.service;

import com.product.entity.Brand;
import com.product.entity.Image;
import com.product.repository.BrandRepository;
import com.product.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3ServiceImpl implements S3Service {

    @Value("${aws.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    private final S3Client s3Client;
    private final ImageRepository imageRepository;
    private final BrandRepository brandRepository;

    public S3ServiceImpl(S3Client s3Client, ImageRepository imageRepository, BrandRepository brandRepository) {
        this.s3Client = s3Client;
        this.imageRepository = imageRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public String uploadFile(MultipartFile file, long brandId) throws IOException {

        // ✅ validation
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Only image files allowed");
        }

        // ✅ safe filename
        String fileName = "products/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        // ✅ memory safe upload
        s3Client.putObject(
                request,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );
        // this is the url of s3 uploaded photos
        String url = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;

        // save data inside image entity
        // here we store url in databases to get product photos in ui
        Brand brand = brandRepository.findById(brandId).get();
        Image image = new Image();
        image.setBrand(brand);
        image.setUrl(url);

        return url;
    }


}

