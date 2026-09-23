package com.devshowcase.devshowcase_api.dto;

import java.util.List;

public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String url;
    private Long profileId;
    private List<Long> technologyIds;
    private Double averageRating;
    private Integer upvotes;

    public ProjectResponseDTO() {
    }

    public ProjectResponseDTO(
            Long id,
            String title,
            String description,
            String url,
            Long profileId,
            List<Long> technologyIds,
            Double averageRating,
            Integer upvotes) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.profileId = profileId;
        this.technologyIds = technologyIds;
        this.averageRating = averageRating;
        this.upvotes = upvotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }

    public void setTechnologyIds(List<Long> technologyIds) {
        this.technologyIds = technologyIds;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }
}