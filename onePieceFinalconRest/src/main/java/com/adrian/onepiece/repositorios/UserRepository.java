package com.adrian.onepiece.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.adrian.onepiece.entities.UserEntity;


public interface  UserRepository extends CrudRepository<UserEntity, Integer>  {
	
	@Query("SELECT u FROM com.adrian.onepiece.entities.UserEntity u " +
				"WHERE u.userName = :userName ")
	UserEntity findByUserName(String userName);
}
