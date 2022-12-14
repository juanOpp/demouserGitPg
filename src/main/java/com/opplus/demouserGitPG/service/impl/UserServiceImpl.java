package com.opplus.demouserGitPG.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.opplus.demouserGitPG.domain.UserEntity;
import com.opplus.demouserGitPG.dto.UserDto;
import com.opplus.demouserGitPG.mapper.UserMapper;
import com.opplus.demouserGitPG.repository.UserRepository;
import com.opplus.demouserGitPG.service.UserService;
import com.opplus.demouserGitPG.service.impl.UserServiceImpl;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	private static final String USER_NOT_FOUND="User %s not found"; 

	@Override
	public List<UserDto> findAll() {
		this.logger.info("Find All Users");
		List<UserDto> usuarios = userMapper.toDto((List<UserEntity>) this.userRepository.findAll());
		return usuarios;
	}

	@Override
	public UserDto findUserById(Long userId) {
		this.logger.info("Find User By Id {}", userId);
		Optional<UserEntity> result = this.userRepository.findById(userId);
		if (result.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(USER_NOT_FOUND, userId));
		else
			return userMapper.toDto(result.get());
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		this.logger.info("Create User {}", userDto);

		UserEntity newUser = userMapper.toEntity(userDto);
		newUser = this.userRepository.save(newUser);
		this.logger.info("Created User with id {}", newUser.getId());
		return userMapper.toDto(newUser);

	}

	@Override
	public Long deleteUserById(Long userId) {
		this.logger.info("Delete User by userId {}", userId);
		Optional<UserEntity> userOrig = this.userRepository.findById(userId);
		if (userOrig.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(USER_NOT_FOUND, userId));
		else {
			this.logger.info("Deleted User by userId {}", userId);
			this.userRepository.deleteById(userId);
			return Long.valueOf(1);
		}
	}

	@Override
	public UserDto updateUserById(Long userId, UserDto user) {
		this.logger.info("Update User by userId {} with {}", userId, user);

		// Recuperar el user por el id especificado
		Optional<UserEntity> userOrig = this.userRepository.findById(userId);
		if (userOrig.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(USER_NOT_FOUND, userId));
		else {
			UserEntity userUpd = userMapper.toEntity(user);
			if (userUpd.getNombre() == null || userUpd.getNombre().isBlank())
				userUpd.setNombre(userOrig.get().getNombre());
			if (userUpd.getApellido1() == null || userUpd.getApellido1().isBlank())
				userUpd.setApellido1(userOrig.get().getApellido1());
			if (userUpd.getApellido2() == null || userUpd.getApellido2().isBlank())
				userUpd.setApellido2(userOrig.get().getApellido2());
			
				userUpd.setId(userId);
			try {
				return userMapper.toDto(this.userRepository.save(userUpd));
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
						String.format("User %s not updated", userId));
			}

		}

	}

}
