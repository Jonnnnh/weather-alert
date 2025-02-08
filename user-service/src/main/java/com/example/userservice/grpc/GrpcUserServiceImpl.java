package com.example.userservice.grpc;

import com.example.grpc.user.GetUserByTelegramIdRequest;
import com.example.grpc.user.GetUserByTelegramIdResponse;
import com.example.grpc.user.GetCityByTelegramIdResponse;
import com.example.userservice.mappers.GrpcUserMapper;

import com.example.grpc.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class GrpcUserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private final GrpcUserMapper mapper;

    @Override
    public void getUserByTelegramId(GetUserByTelegramIdRequest request,
                                    StreamObserver<GetUserByTelegramIdResponse> responseObserver) {
        mapper.getUserByTelegramId(request, responseObserver);
    }

    @Override
    public void getCityByTelegramId(GetUserByTelegramIdRequest request,
                                    StreamObserver<GetCityByTelegramIdResponse> responseObserver) {
        mapper.getCityByTelegramId(request, responseObserver);
    }
}
