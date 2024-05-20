package com.epam.school.entities;


import com.epam.school.utils.Status;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * Lesson generated by hbm2java
 */

@Entity
@Table(name = "lesson", catalog = "school_epam_project")
public class Lesson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Float grade;
	private Set<Student> students = new HashSet<Student>(0);


	public Lesson() {
	}

	public Lesson(String title, Float grade) {
		this.title = title;
		this.grade = grade;
	}
	
	public Lesson(String title, Float grade, Set<Student> students) {
		this.title = title;
		this.grade = grade;
		this.students = students;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false, length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "grade", nullable = false, precision = 12, scale = 0)
	public Float getGrade() {
		return this.grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
