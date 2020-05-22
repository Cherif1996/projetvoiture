package com.jeeProjectGb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeeProjectGb.entities.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	
	User findByEmail(String email);
}
