package com.devshowcase.devshowcase_api.controller;

import com.devshowcase.devshowcase_api.dto.FeedbackRequestDTO;
import com.devshowcase.devshowcase_api.dto.FeedbackResponseDTO;
import com.devshowcase.devshowcase_api.dto.ProjectRequestDTO;
import com.devshowcase.devshowcase_api.dto.ProjectResponseDTO;
import com.devshowcase.devshowcase_api.service.FeedbackService;
import com.devshowcase.devshowcase_api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final FeedbackService feedbackService;

    public ProjectController(
            ProjectService projectService,
            FeedbackService feedbackService) {

        this.projectService = projectService;
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(
            @Valid @RequestBody ProjectRequestDTO dto) {

        ProjectResponseDTO response = projectService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> findAll(
            @RequestParam(required = false) Long technologyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProjectResponseDTO> response =
                projectService.findAll(technologyId, page, size);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<FeedbackResponseDTO> createFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequestDTO dto) {

        FeedbackResponseDTO response =
                feedbackService.create(id, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<ProjectResponseDTO> upvote(
            @PathVariable Long id) {

        ProjectResponseDTO response =
                projectService.upvote(id);

        return ResponseEntity.ok(response);
    }
}