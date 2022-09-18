package com.opplus.demouserGitPG.service;

import java.util.List;

import com.opplus.demouserGitPG.dto.UserDto;

public interface UserService {

	List<UserDto> findAll();

	UserDto findUserById(Long userId);

	UserDto createUser(UserDto userDto);

	Long deleteUserById(Long userId);

	UserDto updateUserById(Long userId, UserDto user);
}

