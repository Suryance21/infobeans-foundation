package com.controller;




import com.model.Students;
import com.service.StudentService;
import com.service.CourseService;
import com.model.Courses;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.*;
import java.util.List;


public class App {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        StudentService studentService = new StudentService();
		CourseService courseService = new CourseService();
		while(true) {
			System.out.println("Welcome to Student Management System");
			System.out.println("1. Students Menu");
			System.out.println("2. Courses Menu");
			System.out.println("3. Exit");
			int choice;
			try {
				 choice = Integer.parseInt(input.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Please enter a valid Integer");
				continue;
			}
			switch(choice) {
				case 1 -> studentsMenu(studentService, input);
				case 2 -> coursesMenu(input, courseService);
				case 3 -> {
					System.out.println("Exiting...");
					input.close();
					return;
				}
				default -> System.out.println("Invalid choice, please try again.");
			}
		}

	}

	public static void studentsMenu(StudentService studentService, Scanner input) {
		while(true) {
			System.out.println("---Student Menu---");
			System.out.println("1. Add Student");
			System.out.println("2. Show All Students");
			System.out.println("3. Search Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Exit");
			int choice;
			try {
				 choice = Integer.parseInt(input.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Please enter a valid Integer");
				continue;
			}
			try {
			switch(choice) {
			case 1->{
				System.out.print("Enter student name : ");
				String name = input.nextLine();
				System.out.print("Enter student roll Number : ");
				String rollNo = input.nextLine();
				System.out.print("Enter gender of student (male, female, other) : ");
				String gender = input.nextLine();
				System.out.print("Enter date of birth (yyyy-MM-dd) : ");
				LocalDate dob = LocalDate.parse(input.nextLine());
				System.out.print("Enter phone number of student : ");
				String phone = input.nextLine();
				System.out.print("Enter email of student : ");
				String email = input.nextLine();
				System.out.print("Enter course Id : ");
				int courseId = Integer.parseInt(input.nextLine());
				System.out.print("Enter admission date (yyyy-MM-dd) : ");
				LocalDate admissionDate = LocalDate.parse(input.nextLine());
				System.out.print("Enter address of student  :");
				String address = input.nextLine();
                boolean isAdded = studentService.addStudent(new Students(0,rollNo,name,gender,dob,phone,email,courseId,admissionDate,address));
                if(isAdded) {
                    System.out.println("Student added successfully");
                }else{
                    System.out.println("Failed to add student");
                }

				
			}
            case 2->{
                List<Students> s = studentService.getAllStudent();
				if(s == null || s.isEmpty()) {
					System.out.println("No students found");
				} else {
					System.out.println("List of Students:");
					for(Students student : s) {
						System.out.println(student);
					}
				}

            }
			case 3->{
				System.out.print("Enter student roll number to search : ");
				String rollNo = input.nextLine();
				Students student = studentService.searchStudent(rollNo);
				if(student != null) {
					System.out.println("Student found: " + student);
				} else {
					System.out.println("No student found with roll number: " + rollNo);
				}
			}
			case 4->{
				System.out.print("Enter student roll number to update : ");
				String rollNo = input.nextLine();
				Students student = studentService.searchStudent(rollNo);
				if(student != null) {
					System.out.print("Enter new address (current: " + student.getAddress() + "): ");
					String address = input.nextLine();
					System.out.print("Enter new phone number (current: " + student.getPhone() + "): ");
					String phone = input.nextLine();
					System.out.print("Enter new email (current: " + student.getEmail() + "): ");
					String email = input.nextLine();
					student.setAddress(address);
					student.setPhone(phone);
					student.setEmail(email);
					boolean isUpdated = studentService.updateStudent(student);
					if(isUpdated) {
						System.out.println("Student updated successfully");
					} else {
						System.out.println("Failed to update student");
					}
				} else {
					System.out.println("No student found with roll number: " + rollNo);
				}
			}
			case 5->{
				System.out.print("Enter student roll number to delete : ");
				String rollNo = input.nextLine();
				boolean isDeleted = studentService.deleteStudent(rollNo);
				if(isDeleted) {
					System.out.println("Student deleted successfully");
				} else {
					System.out.println("Failed to delete student or student not found");
				}
			}
			

            case 6->{
                System.out.println("Exiting...");
                return;
            }
                default ->  System.out.println("Invalid choice");
			}
			}catch(NumberFormatException | InputMismatchException e) {
				System.out.println("Input Mismatch!! Please enter correct value");
				continue;
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
		
		}
	}
	public static void coursesMenu(	Scanner input, CourseService courseService) {
		while(true) {
			System.out.println("---Courses Menu---");
			System.out.println("1. Add Course");
			System.out.println("2. Show All Courses");
			System.out.println("3. Search Course");
			System.out.println("4. Update Course");
			System.out.println("5. Exit");
			int choice;
			try {
				 choice = Integer.parseInt(input.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Please enter a valid Integer");
				continue;
			}
			switch(choice) {
				case 1->{
					System.out.print("Enter course name: ");
					String courseName = input.nextLine();
					System.out.print("Enter course duration (in months): ");
					int duration = Integer.parseInt(input.nextLine());
					System.out.print("Enter course fees: ");
					double fees = Double.parseDouble(input.nextLine());
					Courses course = new Courses(0, courseName, duration, fees);
					boolean isAdded = courseService.addCourse(course);
					if(!isAdded) {
						System.out.println("Failed to add course. Please check the details and try again.");
						continue;
					}
					System.out.println("Course added successfully");
				}
				case 2->{
					List<Courses> courses = courseService.getAllCourses();
					if(courses.isEmpty()) {
						System.out.println("No courses available.");
					} else {
						System.out.println("List of Courses:");
						for(Courses course : courses) {
							System.out.println(course);
						}
					}
					
				}
				case 3->{
					System.out.print("Enter course ID to search: ");
					int courseId = Integer.parseInt(input.nextLine());
					Courses course = courseService.searchCourse(courseId);
					if(course != null) {
						System.out.println("Course found: " + course);
					} else {
						System.out.println("No course found with ID: " + courseId);
					}
				}
				case 4->{
					System.out.print("Enter course ID to update: ");
					int courseId = Integer.parseInt(input.nextLine());
					Courses course = courseService.searchCourse(courseId);
					if(course != null) {
						System.out.print("Enter new course name (current: " + course.getCourseName() + "): ");
						String courseName = input.nextLine();
						System.out.print("Enter new duration in months (current: " + course.getDuration() + "): ");
						int duration = Integer.parseInt(input.nextLine());
						System.out.print("Enter new fees (current: " + course.getFees() + "): ");
						double fees = Double.parseDouble(input.nextLine());
						course.setCourseName(courseName);
						course.setDuration(duration);
						course.setFees(fees);
						boolean isUpdated = courseService.updateCourse(course);
						if(isUpdated) {
							System.out.println("Course updated successfully");
						} else {
							System.out.println("Failed to update course");
						}
					} else {
						System.out.println("No course found with ID: " + courseId);
					}
				}
				case 5->{
					System.out.println("Exiting...");
					return;
				}
				default -> System.out.println("Invalid choice, please try again.");
			}
		}
	}

}
