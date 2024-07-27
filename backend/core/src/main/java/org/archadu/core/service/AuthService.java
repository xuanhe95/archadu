package org.archadu.core.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.archadu.core.model.User;
import org.archadu.core.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    // 获取 Logger 实例
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserRepo userRepo;
    @Autowired
    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public SaResult login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if(user == null){
            return SaResult.error("Login failed");
        }

        if(user.getUsername().equals(username) && user.getPassword().equals(password)){
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
