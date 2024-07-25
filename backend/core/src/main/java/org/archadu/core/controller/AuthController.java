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
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        logger.info("AuthController created");
        this.authService = authService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/login")
    public Response<SaResult> login(@RequestParam String username, @RequestParam String password) {
        logger.info("Login attempt with username: " + username + " and password: " + password);
        SaResult result = authService.login(username, password);
        return new Response<SaResult>("Login success", result);
    }

    @GetMapping("/logout")
    public Response<SaResult> logout() {
        SaResult result = authService.logout();
        return new Response<SaResult>("Logout success", result);
    }

    @GetMapping("/is-login")
    public Response<SaResult> isLogin() {
        SaResult result = authService.isLogin();
        return new Response<SaResult>("User is logged in", result);
    }

}
