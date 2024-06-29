package org.archadu.core.service;

import cn.dev33.satoken.secure.BCrypt;
import org.archadu.core.dto.UserRequest;
import org.archadu.core.model.User;
import org.archadu.core.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Transactional
    public User createUser(UserRequest req){
        User user = new User();
        user.setUsername(req.username());
        // Hash the password before saving it to the database
        String hashedPassword = BCrypt.hashpw(req.password(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        if(req.email() != null && !req.email().isBlank() ){
            user.setEmail(req.email());
        }

        try{
            return userRepo.save(user);
        } catch (Exception e){
            throw new IllegalArgumentException("Username already exists");
        }

    }





}
