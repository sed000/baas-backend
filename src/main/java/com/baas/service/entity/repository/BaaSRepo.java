package com.baas.service.entity.repository;

import com.baas.service.entity.BaaS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaaSRepo extends JpaRepository<BaaS, String> {
    List<BaaS> findByUserId(String userId);
}
