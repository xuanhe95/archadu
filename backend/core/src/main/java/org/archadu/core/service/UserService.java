package org.archadu.core.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import org.archadu.core.dto.UserRequest;
import org.archadu.core.model.User;
import org.archadu.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest req){
        User user = new User();
        user.setUsername(req.username());
        // Hash the password before saving it to the database
        String hashedPassword = BCrypt.hashpw(req.password(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        user.setEmail(req.email());

        return userRepository.save(user);
    }




}
