package org.archadu.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.archadu.core.dto.Response;
import org.archadu.core.dto.UserRequest;
import org.archadu.core.model.User;
import org.archadu.core.service.AuthService;
import org.archadu.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final AuthService authService;


    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public Response<User> register(@RequestBody UserRequest req) {
        User createdUser = userService.createUser(req);
        return new Response<User>("Create new user success", createdUser);
    }

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("/doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }


    @GetMapping("/user-by-username")
    public Response<User> getUserByUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        return new Response<User>("User found", user);
    }

}
