package com.example.bot.configuration;

import com.example.grpc.user.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcUserServiceClientConfiguration {

    @Value("${grpc.client.userService.address}")
    private String serverAddress;

    @Bean
    public ManagedChannel userServiceManagedChannel() {
        return ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();
    }

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub(@Qualifier("userServiceManagedChannel") ManagedChannel managedChannel) {
        return UserServiceGrpc.newBlockingStub(managedChannel);
    }
}
