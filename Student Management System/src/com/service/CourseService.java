package com.service;
import com.dao.CourseDao;
import com.model.Courses;
import java.util.List;

public class CourseService {
    private CourseDao courseDao = new CourseDao();
    public boolean addCourse(Courses course) {
        if (course.getCourseName() == null || course.getCourseName().isEmpty()) {
            System.out.println("Course name can't be empty");
            return false;
        }
        if (course.getDuration() <= 0) {
            System.out.println("Duration must be greater than zero");
            return false;
        }
        if (course.getFees() < 0) {
            System.out.println("Fees can't be negative");
            return false;
        }
        return courseDao.addCourse(course);
    }
    public List<Courses> getAllCourses() {
        return courseDao.getAllCourses();
    }
    public boolean updateCourse(Courses course) {
        if (course.getCourseId() <= 0) {
            System.out.println("Invalid course ID");
            return false;
        }
        return courseDao.updateCourse(course);
    }
    public boolean deleteCourse(int courseId) {
        if (courseId <= 0) {
            System.out.println("Invalid course ID");
            return false;
        }
        return courseDao.deleteCourse(courseId);
    }
    public Courses searchCourse(int courseId) {
        if (courseId <= 0) {
            System.out.println("Invalid course ID");
            return null;
        }
        return courseDao.searchCourse(courseId);
    }
    

}
