package com.kaidash.mobileapplicationsservice.repository;

import com.kaidash.mobileapplicationsservice.entity.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void itShouldFindApplicationsByIdWithDescendVersionOrder() {
        // Given
        Application application1 = new Application(1, "Application 1", "2.0.1", 12);
        Application application2 = new Application(2, "Application 2", "2.0.3", 12);
        Application application3 = new Application(3, "Application 3", "3.0.3", 12);
        Application application4 = new Application(4, "Application 2", "2.1.3", 12);
        applicationRepository.save(application1);
        applicationRepository.save(application2);
        applicationRepository.save(application3);
        applicationRepository.save(application4);
        List<Integer> applicationIdList = new ArrayList<>();
        Collections.addAll(applicationIdList, 1, 2, 3, 4);

        // When
        List<Application> applicationList = applicationRepository.findApplicationsById(applicationIdList);

        // Then
        assertThat(applicationList.get(0).getVersion()).isEqualTo(application3.getVersion());
        assertThat(applicationList.get(1).getVersion()).isEqualTo(application4.getVersion());
        assertThat(applicationList.get(2).getVersion()).isEqualTo(application2.getVersion());
        assertThat(applicationList.get(3).getVersion()).isEqualTo(application1.getVersion());
    }
}