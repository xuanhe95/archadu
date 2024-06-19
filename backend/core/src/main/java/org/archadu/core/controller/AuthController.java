package org.archadu.core.controller;

import cn.dev33.satoken.util.SaResult;
import org.archadu.core.dto.Response;
import org.archadu.core.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public Response<SaResult> login(@RequestParam String username, @RequestParam String password) {
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
