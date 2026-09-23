package com.devshowcase.devshowcase_api.service;

import com.devshowcase.devshowcase_api.dto.FeedbackRequestDTO;
import com.devshowcase.devshowcase_api.dto.FeedbackResponseDTO;
import com.devshowcase.devshowcase_api.entity.Feedback;
import com.devshowcase.devshowcase_api.entity.Project;
import com.devshowcase.devshowcase_api.exception.ResourceNotFoundException;
import com.devshowcase.devshowcase_api.repository.FeedbackRepository;
import com.devshowcase.devshowcase_api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            ProjectRepository projectRepository) {

        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    public FeedbackResponseDTO create(Long projectId, FeedbackRequestDTO dto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado: " + projectId
                        ));

        Feedback feedback = new Feedback();

        feedback.setAuthor(dto.getAuthor());
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        feedback.setProject(project);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        updateProjectAverageRating(project);

        return convertToResponse(savedFeedback);
    }

    private void updateProjectAverageRating(Project project) {

        List<Feedback> feedbacks =
                feedbackRepository.findByProjectId(project.getId());

        double average = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        average = Math.round(average * 100.0) / 100.0;

        project.setAverageRating(average);

        projectRepository.save(project);
    }

    private FeedbackResponseDTO convertToResponse(Feedback feedback) {

        return new FeedbackResponseDTO(
                feedback.getId(),
                feedback.getAuthor(),
                feedback.getRating(),
                feedback.getComment(),
                feedback.getProject().getId()
        );
    }
}