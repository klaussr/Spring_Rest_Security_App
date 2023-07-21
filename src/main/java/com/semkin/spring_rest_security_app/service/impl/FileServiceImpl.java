package com.semkin.spring_rest_security_app.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.semkin.spring_rest_security_app.model.File;
import com.semkin.spring_rest_security_app.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {
    private final AmazonS3 s3client;

    public void createBucket() {
        String bucketName = "semkin";

        if (s3client.doesBucketExistV2(bucketName)) {
            log.info("Bucket {} already exists, use a different name", bucketName);
            return;
        }
        s3client.createBucket(bucketName);
    }

    public void listBuckets(){
        List<Bucket> buckets = s3client.listBuckets();
        log.info("buckets: {}", buckets);
    }

//    @SneakyThrows
//    public void upload(File file) {
//        String bucketName = "expo600_bucket";
//        java.io.File newFile = new java.io.File(file.getLocation());
//        file.setCreated(new Date());
//        log.info("File Uploaded {}", file.getFileName());
//
//        try{
//            s3client.putObject(bucketName, file.getFileName(), newFile);
//        } catch (AmazonServiceException e) {
//            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()),
//                    "Request processing failed at cloud platform", e);
//        } catch (SdkClientException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
//                    "Failed to process your request", e);

    @Override
    @SneakyThrows
    public void upload(File file) {
        String bucketName = "semkin";
        ClassLoader loader = FileServiceImpl.class.getClassLoader();
        java.io.File newFile = new java.io.File(loader.getResource(file.getLocation()).getFile());
        s3client.putObject(bucketName, file.getFileName(), newFile);
    }

    @Override
    @SneakyThrows
    public InputStream download(File file) {
        String bucketName = "semkin";
        if (s3client.doesObjectExist(bucketName, file.getFileName())) {
            S3Object s3Object = s3client.getObject(bucketName, file.getFileName());
            log.info("File download {}", file.getFileName());
            return s3Object.getObjectContent();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Requested file does not exist on bucket");

//            String bucketName = "semkin_bucket";
//
//            S3Object s3object = s3client.getObject(bucketName, file.getFileName());
//            S3ObjectInputStream inputStream = s3object.getObjectContent();
//            java.io.File downloadedFile = new java.io.File("<PUT_DESIRED_PATH_HERE>");

            //FileCopyUtils.copy(inputStream, new FileOutputStream(file));
        }
    }

    @Override
    public Optional<String> listFiles() {
        String bucketName = "semkin";
        ObjectListing objectListing = s3client.listObjects(bucketName);
        if (objectListing != null) {
            log.info("All files in the bucket");
            return Optional.of(String.valueOf(objectListing.getObjectSummaries()));
        } else {
            log.info("No file present in bucket");
            return Optional.empty();
        }
    }

    @Override
    public void deleteFile(String fileName) {
        String bucketName = "semkin";
        s3client.deleteObject(bucketName, fileName);
        log.info("Deleting a File {}", fileName);
    }
}
