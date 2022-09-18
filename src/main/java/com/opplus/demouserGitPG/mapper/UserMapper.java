package com.opplus.demouserGitPG.mapper;

import org.mapstruct.Mapper;

import com.opplus.demouserGitPG.domain.UserEntity;
import com.opplus.demouserGitPG.dto.UserDto;


@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDto, UserEntity>{


}