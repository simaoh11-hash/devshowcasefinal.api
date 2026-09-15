package com.devshowcase.devshowcase_api.controller;

import com.devshowcase.devshowcase_api.dto.ProjectRequestDTO;
import com.devshowcase.devshowcase_api.dto.ProjectResponseDTO;
import com.devshowcase.devshowcase_api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(
            @Valid @RequestBody ProjectRequestDTO dto) {

        ProjectResponseDTO response = projectService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> findAll() {

        List<ProjectResponseDTO> response = projectService.findAll();

        return ResponseEntity.ok(response);
    }
}