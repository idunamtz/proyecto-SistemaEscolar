package com.epam.school.controller;

import com.epam.school.dto.StudentUserDTO;
import com.epam.school.entities.Student;
import com.epam.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getStudents(Model model, 
			@ModelAttribute("studentDto") StudentUserDTO dto) {
//			@ModelAttribute("updateStudent") Student updateStudent,
//			@ModelAttribute("deleteStudent")Student deleteStudent,
//			@ModelAttribute("findStudent") Student findStudent){
		List<Student> students = studentService.findAll(); 
		model.addAttribute("students", students);
		return "students.html";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String newStudent(@ModelAttribute("studentDto") StudentUserDTO dto) {
		studentService.registerStudent(dto);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateStudent(@ModelAttribute("studentDto") StudentUserDTO dto) {
		if(studentService.findById(dto.getId()) != null) {
			studentService.edit(dto.getId(), dto);
		}
		else {
			System.out.println("Doesn't exist");
		}
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteStudent(@ModelAttribute("studentDto") StudentUserDTO dto) {
		if(studentService.findById(dto.getId()) != null) {
			studentService.delete(dto.getId());
		}
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public String findStudent(Model model, 
			@ModelAttribute("studentDto") StudentUserDTO dto) {
//			@ModelAttribute("newStudent") Student newStudent,
//			@ModelAttribute("updateStudent") Student updateStudent,
//			@ModelAttribute("deleteStudent")Student deleteStudent) {
		List<Student> students = new ArrayList<>();
		Student studentFound = new Student();
		
		if(dto.getId() != null) {	
			studentFound = studentService.findById(dto.getId());
			if(studentFound!=null) {
				students.add(studentFound);
				model.addAttribute("students", students);
				return "students.html";
			}
		}
		
		if(dto.getName()!=null && !dto.getName().isBlank()) {
			students = studentService.findByName(dto.getName());
			if(students != null) {
				model.addAttribute("students", students);
				return "students.html";
			}
		}
		
		if(dto.getLastname()!=null && !dto.getLastname().isBlank()) {
			students = studentService.findByLastName(dto.getLastname());
			if(students != null) {
				model.addAttribute("students", students);
				return "students.html";
			}
		}	
		
		return "redirect:/student/list";
	}
}
