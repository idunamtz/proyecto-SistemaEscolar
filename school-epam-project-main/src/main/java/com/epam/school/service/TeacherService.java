package com.epam.school.service;

import com.epam.school.dto.TeacherUserDTO;
import com.epam.school.entities.Teacher;

import java.util.List;

public interface TeacherService {

	public void registerTeacher(TeacherUserDTO dto);
	public void edit(Integer id, TeacherUserDTO teacher);
	public void delete(Integer id);
	public Teacher findById(Integer id);
	public List<Teacher> findByName(String name);
	public List<Teacher> findByLastname(String lastName);
	public List<Teacher> findAll();

}
