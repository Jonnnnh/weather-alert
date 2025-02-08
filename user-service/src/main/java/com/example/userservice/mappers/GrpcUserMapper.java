package com.example.userservice.mappers;

import com.example.grpc.user.GetUserByTelegramIdRequest;
import com.example.grpc.user.GetUserByTelegramIdResponse;
import com.example.grpc.user.GetCityByTelegramIdResponse;
import com.example.userservice.services.UserService;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GrpcUserMapper {

    private final UserService userService;

    public void getUserByTelegramId(GetUserByTelegramIdRequest request,
                                    StreamObserver<GetUserByTelegramIdResponse> responseObserver) {
        var userOpt = userService.getUserByTelegramId(request.getTelegramId());
        if (userOpt.isEmpty()) {
            responseObserver.onNext(GetUserByTelegramIdResponse.newBuilder()
                    .setFound(false)
                    .build());
            responseObserver.onCompleted();
            return;
        }
        var userDTO = userOpt.get();
        var response = GetUserByTelegramIdResponse.newBuilder()
                .setFound(true)
                .setTelegramId(userDTO.getTelegramId())
                .setCity(userDTO.getCity() != null ? userDTO.getCity() : "")
                .setFrequency(userDTO.getFrequency() != null ? userDTO.getFrequency().name() : "")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void getCityByTelegramId(GetUserByTelegramIdRequest request,
                                    StreamObserver<GetCityByTelegramIdResponse> responseObserver) {
        var userOpt = userService.getUserByTelegramId(request.getTelegramId());
        if (userOpt.isEmpty()) {
            responseObserver.onNext(GetCityByTelegramIdResponse.newBuilder()
                    .setFound(false)
                    .build());
            responseObserver.onCompleted();
            return;
        }
        var userDTO = userOpt.get();
        var response = GetCityByTelegramIdResponse.newBuilder()
                .setFound(true)
                .setCity(userDTO.getCity() != null ? userDTO.getCity() : "")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
