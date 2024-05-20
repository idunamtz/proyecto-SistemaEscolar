package com.epam.school.service.impl;

import com.epam.school.dto.StudentUserDTO;
import com.epam.school.entities.EpamUser;
import com.epam.school.entities.Student;
import com.epam.school.repository.StudentRepository;
import com.epam.school.repository.UserRepository;
import com.epam.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired 
	UserRepository userRepository;

	@Override
	public void registerStudent(StudentUserDTO dto) {
		EpamUser user = new EpamUser();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole("ROLE_STUDENT");
		
		EpamUser epamUser = userRepository.save(user);
		
		Student student = new Student();
		student.setName(dto.getName());
		student.setLastname(dto.getLastname());
		student.setUser(epamUser);
		student.setTeacher(dto.getTeacher());
		student.setLesson(dto.getLesson());
		epamUser.setStudent(student);
		userRepository.save(epamUser);
	}
	
	//for testing
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void edit(Integer id, StudentUserDTO dto) {
		Student found = studentRepository.findById(id).get();
		found.setTeacher(dto.getTeacher());
		found.setLesson(dto.getLesson());
		found.setName(dto.getName());
		found.setLastname(dto.getLastname());
		studentRepository.save(found);
	}

	@Override
	public void delete(Integer id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student findById(Integer id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public List<Student> findByName(String name) {
		return studentRepository.findByName(name);
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		return studentRepository.findByLastname(lastName);
	}

	//for testing
	@Override
	public void editS(Integer id, Student student) {
		Student found = studentRepository.findById(id).get();
		found.setTeacher(student.getTeacher());
		found.setLesson(student.getLesson());
		found.setName(student.getName());
		found.setLastname(student.getLastname());
		studentRepository.save(found);
	}

}
