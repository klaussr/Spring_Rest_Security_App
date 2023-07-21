package com.semkin.spring_rest_security_app.service.impl;

import com.semkin.spring_rest_security_app.model.File;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class FileServiceImplTest {
    private File file = Mockito.mock(File.class);

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @Test
    void createBucket() {
        fileServiceImpl.createBucket();
    }

    @Test
    void listBuckets() {
        fileServiceImpl.listBuckets();
    }

    @Test
    void upload(File file) {
        file.setLocation("1.txt");
        fileServiceImpl.upload(file);
    }

    @Test
    void download(File file) {
        fileServiceImpl.download(file);
    }

    @Test
    void listFiles() {
        fileServiceImpl.listFiles();
    }

    @Test
    void deleteFile(String fileName) {
        fileServiceImpl.deleteFile(fileName);
    }
}