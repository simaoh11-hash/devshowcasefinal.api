package com.devshowcase.devshowcase_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackRequestDTO {

    @NotBlank(message = "O autor é obrigatório")
    private String author;

    @NotNull(message = "A avaliação é obrigatória")
    @Min(value = 1, message = "A avaliação deve ser no mínimo 1")
    @Max(value = 5, message = "A avaliação deve ser no máximo 5")
    private Integer rating;

    @NotBlank(message = "O comentário é obrigatório")
    @Size(max = 1000, message = "O comentário deve ter no máximo 1000 caracteres")
    private String comment;

    public FeedbackRequestDTO() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}