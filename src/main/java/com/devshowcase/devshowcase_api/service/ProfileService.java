package com.devshowcase.devshowcase_api.service;

import com.devshowcase.devshowcase_api.dto.ProfileRequestDTO;
import com.devshowcase.devshowcase_api.dto.ProfileResponseDTO;
import com.devshowcase.devshowcase_api.entity.Profile;
import com.devshowcase.devshowcase_api.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponseDTO create(ProfileRequestDTO dto) {

        Profile profile = new Profile();

        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setBio(dto.getBio());

        Profile savedProfile = profileRepository.save(profile);

        return new ProfileResponseDTO(
                savedProfile.getId(),
                savedProfile.getName(),
                savedProfile.getEmail(),
                savedProfile.getBio()
        );
    }

    public ProfileResponseDTO findById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        return new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getBio()
        );
    }

    public List<ProfileResponseDTO> findAll() {

        return profileRepository.findAll()
                .stream()
                .map(profile -> new ProfileResponseDTO(
                        profile.getId(),
                        profile.getName(),
                        profile.getEmail(),
                        profile.getBio()
                ))
                .toList();
    }
}
