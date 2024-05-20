package com.epam.school.repository;

import com.epam.school.entities.EpamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EpamUser, Integer>{
	
	public EpamUser findByUsername(String username);
	public EpamUser findByEmail(String email);
}
