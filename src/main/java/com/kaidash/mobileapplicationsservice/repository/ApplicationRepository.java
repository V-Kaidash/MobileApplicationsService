package com.kaidash.mobileapplicationsservice.repository;

import com.kaidash.mobileapplicationsservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM applications WHERE id IN (:applicationIds) ORDER BY version DESC")
    public List<Application> findApplicationsById(List<Integer> applicationIds);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM applications WHERE content_rate=(:contentRate)")
    int getContentRateCount(int contentRate);
}
