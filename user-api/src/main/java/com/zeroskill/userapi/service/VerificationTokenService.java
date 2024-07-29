package com.zeroskill.userapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final StringRedisTemplate redisTemplate;

    // Email
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

    // JWT
    public void saveToken(String key, String value, long duration, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, duration, unit);
    }

    public String getToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteToken(String key) {
        redisTemplate.delete(key);
    }

    public boolean isTokenNotInWhiteList(String token) {
        return Objects.requireNonNull(redisTemplate.keys("accessToken:*")).stream()
                .noneMatch(key -> token.equals(redisTemplate.opsForValue().get(key)));
    }
}