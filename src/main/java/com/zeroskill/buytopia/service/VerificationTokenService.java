package com.zeroskill.buytopia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final StringRedisTemplate redisTemplate;

    public String createVerificationToken(String email) {
        String token = UUID.randomUUID().toString();
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(token, email, 15, TimeUnit.MINUTES); // 15분동안 유효
        return token;
    }

    public String validateToken(String token) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(token);
    }
}
