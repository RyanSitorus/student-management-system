package com.example.sms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;

@Controller
public class StudentController {
	
	private StudentService studentService;
	public static final String USER_LOGIN = "userLogin";

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/students";
	}

	@GetMapping("/students")
	public String listStudents(Model model, @AuthenticationPrincipal UserDetails userDetails) {

		String userName = userDetails.getUsername();
		String userRole = userDetails.getAuthorities().stream().map(it -> it.getAuthority()).toList().get(0);
		model.addAttribute(USER_LOGIN, userName);

		model.addAttribute("students", studentService.getAllStudents());
		model.addAttribute("UserRole", userRole);
		return "students";
	}

	@GetMapping("/students/new")
	@PreAuthorize("hasRole('ADMIN')")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/students")
	@PreAuthorize("hasRole('ADMIN')")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {

		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setGender(student.getGender());
		existingStudent.setReligion(student.getReligion());

		studentService.updateStudent(existingStudent);
		return "redirect:/students";

	}

	@GetMapping("/students/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}

}
