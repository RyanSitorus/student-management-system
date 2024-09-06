package com.example.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sms.entity.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	UserModel findByuserName(String userName);
}
