package com.model;

import java.time.LocalDate;

public class Students {
	private int studentId;
	private String rollNumber;
	private String name;
	private String gender;
	private LocalDate dob;
	private String phone;
	private String email;
	private int courseId;
	private LocalDate admissionDate;
	private String address;


	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public LocalDate getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Students(int studentId, String rollNumber, String name, String gender, LocalDate dob, String phone,
			String email, int courseId, LocalDate admissionDate, String address) {
		super();
		this.studentId = studentId;
		this.rollNumber = rollNumber;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.courseId = courseId;
		this.admissionDate = admissionDate;
		this.address = address;
	}
	public Students() {
		super();
	}
	@Override
public String toString() {
    return "\n==============================" +
           "\n        STUDENT DETAILS       " +
           "\n==============================" +
           "\n ID            : " + studentId +
           "\n Roll Number   : " + rollNumber +
           "\n Name          : " + name +
           "\n Gender        : " + gender +
           "\n Date of Birth : " + dob +
           "\n Phone         : " + phone +
           "\n Email         : " + email +
           "\n Course ID     : " + courseId +
           "\n Admission Date: " + admissionDate +
           "\n Address       : " + address +
           "\n==============================\n";
}

}
