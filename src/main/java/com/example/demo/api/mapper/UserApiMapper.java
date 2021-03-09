package com.example.demo.api.mapper;

import com.example.demo.api.request.CreateUserRequest;
import com.example.demo.api.response.UserResponse;
import com.example.demo.application.command.CreateUserCommand;
import com.example.demo.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserApiMapper {
    UserApiMapper MAPPER = Mappers.getMapper(UserApiMapper.class);

    CreateUserCommand toCommand(CreateUserRequest request);

    UserResponse toResponse(User domain);
}
