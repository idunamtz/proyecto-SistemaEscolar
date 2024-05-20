package com.epam.school.dto;

import com.epam.school.entities.EpamUser;
import com.epam.school.entities.Lesson;
import com.epam.school.entities.Teacher;

public class StudentUserDTO {
	
	private Integer id;
	private Lesson lesson;
	private Teacher teacher;
	private EpamUser user;
	private String name;
	private String lastname;
	private String username;
	private String email;
	private String password;
	private String role;
	
	
	public StudentUserDTO() {
		
	}

	public StudentUserDTO(Integer id, Lesson lesson, Teacher teacher, EpamUser user, String name, String lastname,
			String username, String email, String password, String role) {
		super();
		this.id = id;
		this.lesson = lesson;
		this.teacher = teacher;
		this.user = user;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Lesson getLesson() {
		return lesson;
	}
	
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public EpamUser getUser() {
		return user;
	}
	
	public void setUser(EpamUser user) {
		this.user = user;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
}
