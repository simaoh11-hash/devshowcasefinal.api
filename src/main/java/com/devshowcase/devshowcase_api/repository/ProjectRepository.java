package com.devshowcase.devshowcase_api.repository;

import com.devshowcase.devshowcase_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}