package com.devshowcase.devshowcase_api.controller;

import com.devshowcase.devshowcase_api.dto.ProfileRequestDTO;
import com.devshowcase.devshowcase_api.dto.ProfileResponseDTO;
import com.devshowcase.devshowcase_api.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> create(
            @Valid @RequestBody ProfileRequestDTO dto) {

        ProfileResponseDTO response = profileService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> findById(
            @PathVariable Long id) {

        ProfileResponseDTO response = profileService.findById(id);

        return ResponseEntity.ok(response);
    }
}