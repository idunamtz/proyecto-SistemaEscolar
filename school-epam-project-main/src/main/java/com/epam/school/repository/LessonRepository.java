package com.epam.school.repository;

import com.epam.school.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{
	
	public List<Lesson> findByTitle(String title);
	public List<Lesson> findByGrade(Float grade);
}
