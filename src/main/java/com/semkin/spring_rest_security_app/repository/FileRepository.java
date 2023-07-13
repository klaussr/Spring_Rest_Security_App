package com.semkin.spring_rest_security_app.repository;

import com.semkin.spring_rest_security_app.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
