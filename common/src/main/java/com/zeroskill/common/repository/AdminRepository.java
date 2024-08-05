package com.zeroskill.common.repository;

import com.zeroskill.common.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Object> findByLoginId(String loginId);

    Optional<Object> findByEmail(String email);
}
