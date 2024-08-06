package com.zeroskill.common.entity;


import com.zeroskill.common.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
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

    @Column(name = "activated", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean activated = false;

    @ColumnDefault("'SILVER'")
    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private Grade grade;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Agreement> agreements;

    public Member(String loginId, String name, String email, String password, Address address) {
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.grade = Grade.SILVER;
        this.password = password;
        this.address = address;
        this.activated = false;
    }

    public static Member toEntity(MemberDto memberDto, Address address) {
        return new Member(memberDto.loginId(), memberDto.name(), memberDto.email(), memberDto.password(),address);
    }

    public void activateAccount() {
        this.activated = true;
    }
}