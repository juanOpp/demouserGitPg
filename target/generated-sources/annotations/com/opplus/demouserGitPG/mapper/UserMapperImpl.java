package com.opplus.demouserGitPG.mapper;

import com.opplus.demouserGitPG.domain.UserEntity;
import com.opplus.demouserGitPG.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setNombre( dto.getNombre() );
        userEntity.setApellido1( dto.getApellido1() );
        userEntity.setApellido2( dto.getApellido2() );

        return userEntity;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setNombre( entity.getNombre() );
        userDto.setApellido1( entity.getApellido1() );
        userDto.setApellido2( entity.getApellido2() );

        return userDto;
    }

    @Override
    public List<UserEntity> toEntity(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<UserEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( UserEntity userEntity : entityList ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }
}
