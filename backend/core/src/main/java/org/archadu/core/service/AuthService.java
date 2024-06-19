package org.archadu.core.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.archadu.core.model.User;
import org.archadu.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SaResult login(String username, String password) {

        User user = userRepository.findByUsername(username);
        if(user == null){
            return SaResult.error("Login failed");
        }

        if(user.getUsername().equals(username) && user.getPassword().equals(hashedPassword)){
            StpUtil.login(user.getId());
            return SaResult.ok("Login success");
        }
        return SaResult.error("Login failed");

    }

    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok("Logout success");
    }

    public SaResult isLogin() {
        if(StpUtil.isLogin()){
            return SaResult.ok("User is logged in");
        }
        return SaResult.error("User is not logged in");
    }


}
