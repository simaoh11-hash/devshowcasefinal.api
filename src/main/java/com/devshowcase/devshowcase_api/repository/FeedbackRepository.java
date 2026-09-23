package com.devshowcase.devshowcase_api.repository;

import com.devshowcase.devshowcase_api.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByProjectId(Long projectId);
}