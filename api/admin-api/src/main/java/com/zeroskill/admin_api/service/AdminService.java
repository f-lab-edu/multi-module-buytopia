package com.zeroskill.admin_api.service;

import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.common.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.zeroskill.common.dto.AdminDto.hashPassword;


@Service
@RequiredArgsConstructor
public class AdminService {
    private static final Logger logger = LogManager.getLogger(AdminService.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final AdminRepository adminRepository;


    public void register(AdminDto dto) {
        if (isLoginIdOrEmailDuplicate(dto.loginId(), dto.email())) {
            throw new BuytopiaException(ErrorType.DUPLICATE_ENTITY, logger::error);
        }
        String hashedPassword = passwordEncoder().encode(dto.password());
        Admin admin = Admin.toEntity(hashPassword(dto, hashedPassword));
        adminRepository.save(admin);
    }

    public boolean isLoginIdDuplicate(String loginId) {
        return adminRepository.findByLoginId(loginId).isPresent();
    }

    public boolean isEmailDuplicate(String email) {
        return adminRepository.findByEmail(email).isPresent();
    }

    public boolean isLoginIdOrEmailDuplicate(String loginId, String email) {
        return isLoginIdDuplicate(loginId) || isEmailDuplicate(email);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }
}
