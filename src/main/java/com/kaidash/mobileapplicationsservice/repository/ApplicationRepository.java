package com.kaidash.mobileapplicationsservice.repository;

import com.kaidash.mobileapplicationsservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM applications WHERE id IN (:applicationIds) ORDER BY version DESC")
    List<Application> findApplicationsById(List<Integer> applicationIds);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM applications WHERE content_rate=(:contentRateId)")
    int getContentRateCount(int contentRateId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM applications WHERE app_name = (:applicationName) AND version = (:applicationVersion)")
    Optional<Application> findApplicationByNameAndVersion(String applicationName, String applicationVersion);
}
