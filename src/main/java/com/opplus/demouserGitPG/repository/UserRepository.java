package com.opplus.demouserGitPG.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opplus.demouserGitPG.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
