package com.example.demo.repository.mapper;

import com.example.demo.domain.User;
import com.example.demo.repository.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRepoMapper {
    UserRepoMapper MAPPER = Mappers.getMapper(UserRepoMapper.class);

    User toDomain(UserPo po);

    @Mappings(value = {
            @Mapping(target = "id", ignore = true)
    })
    UserPo toPo(User user);
}
