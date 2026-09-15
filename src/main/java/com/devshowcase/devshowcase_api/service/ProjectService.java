package com.devshowcase.devshowcase_api.service;

import com.devshowcase.devshowcase_api.dto.ProjectRequestDTO;
import com.devshowcase.devshowcase_api.dto.ProjectResponseDTO;
import com.devshowcase.devshowcase_api.entity.Profile;
import com.devshowcase.devshowcase_api.entity.Project;
import com.devshowcase.devshowcase_api.entity.Technology;
import com.devshowcase.devshowcase_api.repository.ProfileRepository;
import com.devshowcase.devshowcase_api.repository.ProjectRepository;
import com.devshowcase.devshowcase_api.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository) {

        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    public ProjectResponseDTO create(ProjectRequestDTO dto) {

        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        Project project = new Project();

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setUrl(dto.getUrl());
        project.setProfile(profile);

        List<Technology> technologies = new ArrayList<>();

        if (dto.getTechnologyIds() != null) {

            for (Long technologyId : dto.getTechnologyIds()) {

                Technology technology = technologyRepository.findById(technologyId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Tecnologia não encontrada: " + technologyId
                                ));

                technologies.add(technology);
            }
        }

        project.setTechnologies(technologies);

        Project savedProject = projectRepository.save(project);

        return convertToResponse(savedProject);
    }

    public List<ProjectResponseDTO> findAll() {

        return projectRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private ProjectResponseDTO convertToResponse(Project project) {

        List<Long> technologyIds = project.getTechnologies()
                .stream()
                .map(Technology::getId)
                .toList();

        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getUrl(),
                project.getProfile().getId(),
                technologyIds
        );
    }
}