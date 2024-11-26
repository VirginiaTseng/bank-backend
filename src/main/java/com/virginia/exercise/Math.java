package com.virginia.exercise;

public class Math extends Classroom{
	public Math() {
		super();
		this.subject="Math";
	}
	
	public static void main(String[] args) {
		Math m=new Math();
		m.setStudent_count(60);
		m.displayDetails();
	}
	
}
