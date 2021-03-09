package com.example.demo.application.mapper;

import com.example.demo.application.command.CreateUserCommand;
import com.example.demo.application.command.DeleteUserCommand;
import com.example.demo.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAppMapper {
    UserAppMapper MAPPER = Mappers.getMapper(UserAppMapper.class);

    User toDomain(CreateUserCommand command);

    @Mappings(value = {
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "phone", ignore = true)
    })
    User toDomain(DeleteUserCommand command);
}
