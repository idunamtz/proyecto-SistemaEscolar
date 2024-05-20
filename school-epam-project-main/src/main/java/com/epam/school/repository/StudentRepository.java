package com.epam.school.repository;

import com.epam.school.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
							
@Repository											//entity and type of the PK
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	//for testing
	//public Student findByName(String name);
	public List<Student> findByName(String name);
	public List<Student> findByLastname(String lastName);
}
