package com.virginia.exercise;

public class Classroom {
	public String subject;
	private int student_count;
	
	public void displayDetails() {
		System.out.println("Classroom with subject:"+subject+" students count ="+student_count);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getStudent_count() {
		return student_count;
	}

	public void setStudent_count(int student_count) {
		this.student_count = student_count;
	}
	
}
