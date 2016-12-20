package com.tu.ziik.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tu.ziik.lms.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	List<Role> findByName(String name);
}
