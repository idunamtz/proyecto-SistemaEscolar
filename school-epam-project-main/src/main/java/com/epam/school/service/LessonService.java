package com.epam.school.service;

import com.epam.school.entities.Lesson;

import java.util.List;

public interface LessonService {

	public void save(Lesson lesson);
	public void edit(Integer id, Lesson lesson);
	public void delete(Integer id);
	public Lesson findById(Integer id);
	public List<Lesson> findByTitle(String title);
	public List<Lesson> findByGrade(Float grade);
	public List<Lesson> findAll();

}
