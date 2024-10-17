package com.baas.service.entity.repository;

import com.baas.service.entity.BaaS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaaSRepo extends JpaRepository<BaaS, String> {
    List<BaaS> findByUserId(String userId);

    @Modifying
    @Query("DELETE FROM BaaS b WHERE b.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);
}
