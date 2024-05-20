package com.epam.school.repository;

import com.epam.school.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	
	public List<Teacher> findByName(String name);
	public List<Teacher> findByLastname(String lastName);
	
}
