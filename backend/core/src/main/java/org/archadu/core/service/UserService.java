package org.archadu.core.service;

import cn.dev33.satoken.secure.BCrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.archadu.core.dto.UserRequest;
import org.archadu.core.model.User;
import org.archadu.core.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class UserService {
    private final UserRepo userRepo;
    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public UserService(UserRepo userRepo, RedisTemplate<String, Object> redisTemplate) {
        this.userRepo = userRepo;
        this.redisTemplate = redisTemplate;
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

    public User getUserByUsername(String username){

        long startTime = System.currentTimeMillis();

        String key = "user:" + username;
        User cachedUser = (User) redisTemplate.opsForValue().get(key);
        if(cachedUser != null){
            System.out.println("Cache hit");
            System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
            return cachedUser;
        }

        User user = userRepo.findByUsername(username);
        if(user != null){
            redisTemplate.opsForValue().set(key, user);
        }

        System.out.println("Cache miss");
        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
        return user;
    }







}
