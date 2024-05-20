package com.epam.school.service;

import com.epam.school.entities.EpamUser;
import com.epam.school.entities.Lesson;
import com.epam.school.entities.Student;
import com.epam.school.entities.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {
	
	@MockBean
	StudentService studentService;
	
	@BeforeEach
	public void setUp() {
		System.out.println("Hello world!");
	}
	
	@Test
	public void checkIfStudentServiceIsNotNull() {
		assertThat(studentService).isNotNull();
	}
	
	@Test
	public void saveStudentTests() {
		Student student = new Student(new Lesson(), new Teacher(), new EpamUser(), "Valeria", "Cervantes");
		studentService.save(student);
		when(studentService.findById(student.getId())).thenReturn(student);
		Student found = studentService.findById(student.getId());
		
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo(student.getName());
		assertThat(found.getLastname()).isEqualTo(student.getLastname());
		assertThat(found.getLesson()).isEqualTo(student.getLesson());
		assertThat(found.getTeacher()).isEqualTo(student.getTeacher());
		assertThat(found.getUser()).isEqualTo(student.getUser());
	}
	
	@Test
	public void testFindStudent() {
		when(studentService.findById(4)).thenReturn(new Student());
		studentService.findById(4);
		assertNotNull(studentService.findById(4));
	}
	
	@Test
	public void editStudentTests() {
		Student student = new Student(new Lesson(), new Teacher(), new EpamUser(), "Valeria", "Cervantes");
		Student student2 = new Student(new Lesson(), new Teacher(), new EpamUser(), "Valeria", "Cervantes");
		student2.setName("newName");
		student2.setLastname("newLastName");
		
		studentService.save(student);
		studentService.editS(student.getId(), student2);
		
		when(studentService.findById(student.getId())).thenReturn(student2);
		Student found = studentService.findById(student2.getId());
		
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("newName");
		assertThat(found.getLastname()).isEqualTo("newLastName");
	}
	
	@Test
	public void deleteStudentTests() {
		Student student = new Student(new Lesson(), new Teacher(), new EpamUser(), "Valeria", "Cervantes");
		studentService.save(student);
		studentService.delete(student.getId());
		
		Student found = studentService.findById(student.getId());
		
		assertThat(found).isNull();
	}
	
	@Test
	public void whenFindIdThenReturnStudent() {
		when(studentService.findById(1)).thenReturn(new Student(new Lesson(), new Teacher(), new EpamUser(), "Valeria", "Cervantes"));
		Student found = studentService.findById(1);
		
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Valeria");
		assertThat(found.getLastname()).isEqualTo("Cervantes");
	}
	
	@Test
	public void whenFindIdAndNoStudentThenReturnNull() {
		Student found = studentService.findById(1);
		
		assertThat(found).isNull();
	}
}
