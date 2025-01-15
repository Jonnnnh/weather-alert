package com.example.models.mappers;

import com.example.models.dto.NotificationDTO;
import com.example.models.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "user.id", target = "userId")
    NotificationDTO toDTO(Notification notification);

    Notification toEntity(NotificationDTO notificationDTO);
}
