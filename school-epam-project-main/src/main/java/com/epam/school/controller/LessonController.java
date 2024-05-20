package com.epam.school.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.school.entities.Lesson;
import com.epam.school.entities.Student;
import com.epam.school.service.LessonService;

@Controller
@RequestMapping("/lesson")
public class LessonController {

	@Autowired
	LessonService lessonService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getLessons(Model model,
							 @ModelAttribute("newLesson") Lesson newLesson,
							 @ModelAttribute("updateLesson") Lesson updateLesson,
							 @ModelAttribute("deleteLesson") Lesson deleteLesson,
							 @ModelAttribute("findLesson") Lesson findLesson) {
		List<Lesson> lessons=lessonService.findAll();
		model.addAttribute("lessons", lessons);
		return "lessons.html";
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String newLesson(@ModelAttribute("newLesson") Lesson lesson) {
		lessonService.save(lesson);
		return "redirect:/lesson/list";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateLesson(@ModelAttribute("updateLesson") Lesson updateLesson) {
		if(lessonService.findById(updateLesson.getId()) != null) {
			lessonService.edit(updateLesson.getId(), updateLesson);
		}
		else {
			System.out.println("Doesn't exist");
		}
		return "redirect:/lesson/list";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteLesson(@ModelAttribute("idLesson") Lesson deleteLesson) {
		if(lessonService.findById(deleteLesson.getId()) != null) {
			lessonService.delete(deleteLesson.getId());
		}
		else {
			System.out.println("Doesn't exist");
		}
		return "redirect:/lesson/list";
	}

	@RequestMapping(value="/details", method=RequestMethod.GET)
	public String showLessons(Model model, @ModelAttribute("idInfo") Lesson infoLesson) {
		Set<Student> students = infoLesson.getStudents();
		if(students.size() > 0) {
			model.addAttribute("students", students);
		}
		model.addAttribute("lesson", infoLesson);
		return "lessonsStudents.html";
	}

	@RequestMapping(value="/find", method=RequestMethod.POST)
	public String findLesson(Model model,
							 @ModelAttribute("newLesson") Lesson newLesson,
							 @ModelAttribute("updateLesson") Lesson updateLesson,
							 @ModelAttribute("deleteLesson") Lesson deleteLesson,
							 @ModelAttribute("findLesson") Lesson findLesson) {
		List<Lesson> lessons = new ArrayList<>();
		Lesson lessonFound = new Lesson();

		if(findLesson.getId() != null) {
			lessonFound = lessonService.findById(findLesson.getId());
			if(lessonFound!=null) {
				lessons.add(lessonFound);
				model.addAttribute("lessons", lessons);
				return "lessons.html";
			}
		}

		if(findLesson.getTitle()!=null && !findLesson.getTitle().isBlank()) {
			lessons = lessonService.findByTitle(findLesson.getTitle());
			if(lessons != null) {
				model.addAttribute("lessons", lessons);
				return "lessons.html";
			}
		}

		//doesn't work
		if(findLesson.getGrade()!= null) {
			lessons = lessonService.findByGrade(findLesson.getGrade());
			if(lessons != null) {
				model.addAttribute("lessons", lessons);
				return "lessons.html ";
			}
		}

		return "redirect:/lesson/list";
	}
}