package com.model;

public class Courses {
	private int  courseId;
	private String courseName;
	private int duration;
	private double fees;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public Courses(int courseId, String courseName, int duration, double fees) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.duration = duration;
		this.fees = fees;
	}
	 @Override
    public String toString() {
        return "\n📚 Course Details 📚" +
               "\n------------------------" +
               "\nCourse ID     : " + courseId +
               "\nCourse Name   : " + courseName +
               "\nDuration      : " + duration + " years" +
               "\nFees          : ₹" + String.format("%.2f", fees) +
               "\n------------------------";
    }
	
	
}
