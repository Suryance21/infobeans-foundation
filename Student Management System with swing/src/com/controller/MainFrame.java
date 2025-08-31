package com.controller;

import com.model.Students;
import com.model.Courses;
import com.service.StudentService;
import com.service.CourseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MainFrame extends JFrame {
    private final StudentService studentService;
    private final CourseService courseService;

    public MainFrame() {
        studentService = new StudentService();
        courseService = new CourseService();
        setTitle("Student Management System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        panel.setBackground(new Color(245, 250, 255));

        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(0, 102, 204));
        panel.add(title);

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> addStudent());
        panel.add(addStudentButton);

        JButton showAllStudentsButton = new JButton("Show All Students");
        showAllStudentsButton.addActionListener(e -> showAllStudents());
        panel.add(showAllStudentsButton);

        JButton searchStudentButton = new JButton("Search Student");
        searchStudentButton.addActionListener(e -> searchStudent());
        panel.add(searchStudentButton);

        JButton updateStudentButton = new JButton("Update Student");
        updateStudentButton.addActionListener(e -> updateStudent());
        panel.add(updateStudentButton);

        JButton deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.addActionListener(e -> deleteStudent());
        panel.add(deleteStudentButton);

        panel.add(new JSeparator());

        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(e -> addCourse());
        panel.add(addCourseButton);

        JButton showAllCoursesButton = new JButton("Show All Courses");
        showAllCoursesButton.addActionListener(e -> showAllCourses());
        panel.add(showAllCoursesButton);

        JButton searchCourseButton = new JButton("Search Course");
        searchCourseButton.addActionListener(e -> searchCourse());
        panel.add(searchCourseButton);

        JButton updateCourseButton = new JButton("Update Course");
        updateCourseButton.addActionListener(e -> updateCourse());
        panel.add(updateCourseButton);

        panel.add(new JSeparator());

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(255, 102, 102));
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        setContentPane(panel);
    }

    private void addStudent() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField rollNoField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField dobField = new JTextField("yyyy-MM-dd");
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField courseIdField = new JTextField();
        JTextField admissionDateField = new JTextField("yyyy-MM-dd");
        JTextField addressField = new JTextField();

        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Roll No:")); panel.add(rollNoField);
        panel.add(new JLabel("Gender:")); panel.add(genderField);
        panel.add(new JLabel("DOB:")); panel.add(dobField);
        panel.add(new JLabel("Phone:")); panel.add(phoneField);
        panel.add(new JLabel("Email:")); panel.add(emailField);
        panel.add(new JLabel("Course ID:")); panel.add(courseIdField);
        panel.add(new JLabel("Admission Date:")); panel.add(admissionDateField);
        panel.add(new JLabel("Address:")); panel.add(addressField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Students student = new Students(
                        0,
                        rollNoField.getText(),
                        nameField.getText(),
                        genderField.getText(),
                        LocalDate.parse(dobField.getText()),
                        phoneField.getText(),
                        emailField.getText(),
                        Integer.parseInt(courseIdField.getText()),
                        LocalDate.parse(admissionDateField.getText()),
                        addressField.getText()
                );
                boolean isAdded = studentService.addStudent(student);
                JOptionPane.showMessageDialog(this, isAdded ? "Student added successfully!" : "Failed to add student.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        }
    }

    private void showAllStudents() {
        List<Students> students = studentService.getAllStudent();
        if (students == null || students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students found.");
            return;
        }
        String[] columns = {"ID", "Roll No", "Name", "Gender", "DOB", "Phone", "Email", "Course ID", "Admission Date", "Address"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Students s : students) {
            model.addRow(new Object[]{
                    s.getStudentId(), s.getRollNumber(), s.getName(), s.getGender(), s.getDob(),
                    s.getPhone(), s.getEmail(), s.getCourseId(), s.getAdmissionDate(), s.getAddress()
            });
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "All Students", JOptionPane.PLAIN_MESSAGE);
    }

    private void searchStudent() {
        String rollNo = JOptionPane.showInputDialog(this, "Enter student roll number to search:");
        if (rollNo == null || rollNo.isEmpty()) return;
        Students s = studentService.searchStudent(rollNo);
        if (s != null) {
            JOptionPane.showMessageDialog(this, s.toString(), "Student Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No student found with roll number: " + rollNo);
        }
    }

    private void updateStudent() {
        String rollNo = JOptionPane.showInputDialog(this, "Enter student roll number to update:");
        if (rollNo == null || rollNo.isEmpty()) return;
        Students s = studentService.searchStudent(rollNo);
        if (s == null) {
            JOptionPane.showMessageDialog(this, "No student found with roll number: " + rollNo);
            return;
        }
        JTextField addressField = new JTextField(s.getAddress());
        JTextField phoneField = new JTextField(s.getPhone());
        JTextField emailField = new JTextField(s.getEmail());
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("New Address:")); panel.add(addressField);
        panel.add(new JLabel("New Phone:")); panel.add(phoneField);
        panel.add(new JLabel("New Email:")); panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Student", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            s.setAddress(addressField.getText());
            s.setPhone(phoneField.getText());
            s.setEmail(emailField.getText());
            boolean isUpdated = studentService.updateStudent(s);
            JOptionPane.showMessageDialog(this, isUpdated ? "Student updated successfully!" : "Failed to update student.");
        }
    }

    private void deleteStudent() {
        String rollNo = JOptionPane.showInputDialog(this, "Enter student roll number to delete:");
        if (rollNo == null || rollNo.isEmpty()) return;
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete student with roll number: " + rollNo + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean isDeleted = studentService.deleteStudent(rollNo);
            JOptionPane.showMessageDialog(this, isDeleted ? "Student deleted successfully!" : "Failed to delete student or student not found.");
        }
    }

    private void addCourse() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField feesField = new JTextField();

        panel.add(new JLabel("Course Name:")); panel.add(nameField);
        panel.add(new JLabel("Duration (months):")); panel.add(durationField);
        panel.add(new JLabel("Fees:")); panel.add(feesField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Courses course = new Courses(
                        0,
                        nameField.getText(),
                        Integer.parseInt(durationField.getText()),
                        Double.parseDouble(feesField.getText())
                );
                boolean isAdded = courseService.addCourse(course);
                JOptionPane.showMessageDialog(this, isAdded ? "Course added successfully!" : "Failed to add course.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        }
    }

    private void showAllCourses() {
        List<Courses> courses = courseService.getAllCourses();
        if (courses == null || courses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No courses found.");
            return;
        }
        String[] columns = {"ID", "Name", "Duration", "Fees"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Courses c : courses) {
            model.addRow(new Object[]{
                    c.getCourseId(), c.getCourseName(), c.getDuration(), c.getFees()
            });
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "All Courses", JOptionPane.PLAIN_MESSAGE);
    }

    private void searchCourse() {
        String idStr = JOptionPane.showInputDialog(this, "Enter course ID to search:");
        if (idStr == null || idStr.isEmpty()) return;
        try {
            int id = Integer.parseInt(idStr);
            Courses c = courseService.searchCourse(id);
            if (c != null) {
                JOptionPane.showMessageDialog(this, c.toString(), "Course Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No course found with ID: " + id);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void updateCourse() {
        String idStr = JOptionPane.showInputDialog(this, "Enter course ID to update:");
        if (idStr == null || idStr.isEmpty()) return;
        try {
            int id = Integer.parseInt(idStr);
            Courses c = courseService.searchCourse(id);
            if (c == null) {
                JOptionPane.showMessageDialog(this, "No course found with ID: " + id);
                return;
            }
            JTextField nameField = new JTextField(c.getCourseName());
            JTextField durationField = new JTextField(String.valueOf(c.getDuration()));
            JTextField feesField = new JTextField(String.valueOf(c.getFees()));
            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
            panel.add(new JLabel("New Name:")); panel.add(nameField);
            panel.add(new JLabel("New Duration:")); panel.add(durationField);
            panel.add(new JLabel("New Fees:")); panel.add(feesField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Update Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                c.setCourseName(nameField.getText());
                c.setDuration(Integer.parseInt(durationField.getText()));
                c.setFees(Double.parseDouble(feesField.getText()));
                boolean isUpdated = courseService.updateCourse(c);
                JOptionPane.showMessageDialog(this, isUpdated ? "Course updated successfully!" : "Failed to update course.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}