package com.example.notice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notice.entity.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer>{
	
	public UserDtls findByEmail(String email);
}
