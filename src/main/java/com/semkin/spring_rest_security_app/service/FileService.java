package com.semkin.spring_rest_security_app.service;

import com.semkin.spring_rest_security_app.model.File;
import java.io.InputStream;
import java.util.Optional;

public interface FileService {
    void upload(File file);
    InputStream download (File file);
    Optional<String> listFiles();
    void deleteFile(String fileName);
}
