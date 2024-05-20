package com.epam.school.controller;

import com.epam.school.dto.TeacherUserDTO;
import com.epam.school.entities.Student;
import com.epam.school.entities.Teacher;
import com.epam.school.service.TeacherService;
import com.epam.school.service.impl.SchoolUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	SchoolUserDetailsService userService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getTeachers(Model model, @ModelAttribute("teacherDto") TeacherUserDTO dto) {
//			@ModelAttribute("updateTeacher") Teacher updateTeacher,
//			@ModelAttribute("deleteTeacher") Teacher deleteTeacher,
//			@ModelAttribute("idInfo") Teacher infoTeacher,
//			@ModelAttribute("findTeacher")Teacher findTeacher){
		dto = new TeacherUserDTO();
		List<Teacher> teachers = teacherService.findAll(); 
		model.addAttribute("teachers", teachers);
		return "teachers.html";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String newTeacher(Model model, @ModelAttribute("teacherDto") TeacherUserDTO dto) {
		teacherService.registerTeacher(dto);
		
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateTeacher(@ModelAttribute("teacherDto") TeacherUserDTO dto) {
		if(teacherService.findById(dto.getId()) != null) {
			teacherService.edit(dto.getId(), dto);
		}
		else {
			System.out.println("Doesn't exist");
		}
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteTeacher(@ModelAttribute("teacherDto") TeacherUserDTO dto) {
		if(teacherService.findById(dto.getId()) != null) {
			teacherService.delete(dto.getId());
		}
		else {
			System.out.println("Doesn't exist");
		}
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value="/details", method=RequestMethod.GET)
	public String showStudents(Model model, @RequestParam("teacherId") Integer id) {
		Teacher teacher = teacherService.findById(id);
		if(teacher != null) {
			Set<Student> students = teacher.getStudents();
			if(students.size() > 0) {
				model.addAttribute("students", students);
			}
		}
		model.addAttribute("teacher", teacher);
		return "teachersStudents.html";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public String findTeacher(Model model, 
			@ModelAttribute("teacherDto") TeacherUserDTO dto) {
		List<Teacher> teachers = new ArrayList<>();
		Teacher teacherFound = new Teacher();
		
		if(dto.getId() != null) {	
			teacherFound = teacherService.findById(dto.getId());
			if(teacherFound!=null) {
				teachers.add(teacherFound);
				model.addAttribute("teachers", teachers);
				return "teachers.html";
			}
		}
		
		if(dto.getName()!=null && !dto.getName().isBlank()) {
			teachers = teacherService.findByName(dto.getName());
			if(teachers != null) {
				model.addAttribute("teachers", teachers);
				return "teachers.html";
			}
		}
		
		if(dto.getLastname()!=null && !dto.getLastname().isBlank()) {
			teachers = teacherService.findByLastname(dto.getLastname());
			if(teachers != null) {
				model.addAttribute("teachers", teachers);
				return "teachers.html";
			}
		}	
		
		return "redirect:/teacher/list";
	}
	
}
