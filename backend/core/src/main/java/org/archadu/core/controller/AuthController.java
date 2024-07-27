package org.archadu.core.controller;

import cn.dev33.satoken.util.SaResult;
import org.archadu.core.dto.Response;
import org.archadu.core.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        log.info("AuthController created");
        this.authService = authService;
    }


    @GetMapping("/login")
    public Response<SaResult> login(@RequestParam String username, @RequestParam String password) {
        log.info("Login attempt with username: " + username + " and password: " + password);
        SaResult result = authService.login(username, password);
        if(result == null){
            return Response.error("Login failed");
        }
        return Response.success(result);
    }

    @GetMapping("/logout")
    public Response<SaResult> logout() {
        SaResult result = authService.logout();
        if(result == null){
            return Response.error("Logout failed");
        }
        return Response.success(result);
    }

    @GetMapping("/is-login")
    public Response<SaResult> isLogin() {
        SaResult result = authService.isLogin();
        if(result == null){
            return Response.error("Error checking login status");
        }
        return Response.success(result);
    }

}
