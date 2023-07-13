package com.semkin.spring_rest_security_app.service;

import com.semkin.spring_rest_security_app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericService<User,Long>{
    User create(User user);

    User update(User user);

    User findByUsername(String username);

   Optional<User> findByEmail(String email);



}

