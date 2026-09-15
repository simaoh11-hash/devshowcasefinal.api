package com.devshowcase.devshowcase_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TechnologyRequestDTO {

    @NotBlank(message = "O nome da tecnologia é obrigatório")
    @Size(max = 100, message = "O nome da tecnologia deve ter no máximo 100 caracteres")
    private String name;

    public TechnologyRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}