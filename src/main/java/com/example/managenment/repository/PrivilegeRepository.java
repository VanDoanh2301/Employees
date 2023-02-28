package com.example.managenment.repository;

import com.example.managenment.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    public Privilege findByName(String name);
}
