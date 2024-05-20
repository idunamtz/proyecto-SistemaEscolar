package com.epam.school.service.impl;

import com.epam.school.entities.Lesson;
import com.epam.school.repository.LessonRepository;
import com.epam.school.service.LessonService;
import com.epam.school.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService{
	
	@Autowired
	LessonRepository lessonRepository;

	@Override
	public void save(Lesson lesson) {
		lessonRepository.save(lesson);
	}

	@Override
	public void edit(Integer id, Lesson lesson) {
		Lesson found = lessonRepository.findById(id).orElse(null);
		if(found != null){
			found.setTitle(lesson.getTitle());
			found.setGrade(lesson.getGrade());
			save(lesson);

			//APPROVED AND FAILED
			String status = Status.calculateStatus(lesson.getGrade());
			System.out.println("STATUS: " + status);
		}

	}

	@Override
	public void delete(Integer id) {
		lessonRepository.deleteById(id);
	}

	@Override
	public Lesson findById(Integer id) {
		return lessonRepository.findById(id).get();
	}

	@Override
	public List<Lesson> findByTitle(String title) {
		return lessonRepository.findByTitle(title);
	}

	@Override
	public List<Lesson> findAll() {
		return lessonRepository.findAll();
	}

	@Override
	public List<Lesson> findByGrade(Float grade) {
		return lessonRepository.findByGrade(grade);
	}


}


