package com.chaitanya.spb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaitanya.spb.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	

}
