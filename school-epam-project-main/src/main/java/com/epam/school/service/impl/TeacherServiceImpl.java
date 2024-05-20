package com.epam.school.service.impl;

import com.epam.school.dto.TeacherUserDTO;
import com.epam.school.entities.EpamUser;
import com.epam.school.entities.Teacher;
import com.epam.school.repository.TeacherRepository;
import com.epam.school.repository.UserRepository;
import com.epam.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void registerTeacher(TeacherUserDTO dto) {
		EpamUser user= new EpamUser();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole("ROLE_TEACHER");
		
		EpamUser epamUser = userRepository.save(user);		
		
		Teacher teacher = new Teacher();
		teacher.setName(dto.getName());
		teacher.setLastname(dto.getLastname());
		teacher.setUser(epamUser);
		epamUser.setTeacher(teacher);
		userRepository.save(epamUser);
		
	}

	@Override
	public void edit(Integer id, TeacherUserDTO teacher) {
		Teacher found = teacherRepository.findById(id).get();
		found.setName(teacher.getName());
		found.setLastname(teacher.getLastname());
		teacherRepository.save(found);
	}

	@Override
	public void delete(Integer id) {
		teacherRepository.deleteById(id);
	}

	@Override
	public Teacher findById(Integer id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public List<Teacher> findByName(String name) {
		return teacherRepository.findByName(name);
	}

	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	@Override
	public List<Teacher> findByLastname(String lastName) {
		return teacherRepository.findByLastname(lastName);
	}

}
