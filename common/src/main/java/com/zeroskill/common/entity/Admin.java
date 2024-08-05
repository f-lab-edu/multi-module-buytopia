package com.zeroskill.common.entity;

import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Admin(String loginId, String name, String email, String password) {
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Admin toEntity(AdminDto adminDto) {
        return new Admin(adminDto.loginId(), adminDto.name(), adminDto.email(), adminDto.password());
    }
}
