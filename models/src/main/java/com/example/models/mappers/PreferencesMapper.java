package com.example.models.mappers;

import com.example.models.dto.PreferencesDTO;
import com.example.models.entities.Preferences;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PreferencesMapper {

    @Mapping(source = "user.id", target = "userId")
    PreferencesDTO toDTO(Preferences preferences);

    Preferences toEntity(PreferencesDTO preferencesDTO);
}
