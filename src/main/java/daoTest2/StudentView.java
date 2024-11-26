package daoTest2;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//import ProjectMVC.User;

public class StudentView {
	
	 private JFrame frame;
	    private JTextArea userDetailsTextArea;
	    

	    public StudentView() {
	        frame = new JFrame("Student Details");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 300);

	        userDetailsTextArea = new JTextArea(10, 30);
	        userDetailsTextArea.setEditable(false);

	        JScrollPane scrollPane = new JScrollPane(userDetailsTextArea);
	        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	        
	        //new code below for views with buttons
	       
	    }//new code ends her
	    

	    public void displayUserDetails(Student user) {
	        userDetailsTextArea.setText("");
	        if (user != null) {
	            userDetailsTextArea.append(" ID: " + user.getId() + "\n");
	            userDetailsTextArea.append(" Student ID: " + user.getStudent_id() + "\n");
	            userDetailsTextArea.append("Student Name: " + user.getStudent_Name() + "\n");
	            // Add other user details as needed
	        } else {
	            userDetailsTextArea.append("User not found.");
	        }
	        frame.setVisible(true);
	    }

	  /*  public void displayAllUserDetails(List<User> users) {
	        userDetailsTextArea.setText("");
	        if (users != null && !users.isEmpty()) {
	            for (User user : users) {
	                userDetailsTextArea.append("User ID: " + user.getId() + "\n");
	                userDetailsTextArea.append("Name: " + user.getName() + "\n");
	                userDetailsTextArea.append("\n");
	            }
	        } else {
	            userDetailsTextArea.append("No users found.");
	        }
	        frame.setVisible(true);
	    }
	    */

	    public void displayUserAddedMessage(Student user) {
	        userDetailsTextArea.setText("Hey Superstar, Student added successfully.\nStudent ID: " + user.getStudent_id() + "\nStudent Name: " + user.getStudent_Name());
	        frame.setVisible(true);
	    }
	/*
	    public void displayUserUpdatedMessage(User user) {
	        userDetailsTextArea.setText("User updated successfully.\nUser ID: " + user.getId() + "\nName: " + user.getName());
	        frame.setVisible(true);
	    }

	    public void displayUserDeletedMessage(User user) {
	        userDetailsTextArea.setText("User deleted successfully.\nUser ID: " + user.getId() + "\nName: " + user.getName());
	        frame.setVisible(true);
	    }
	    */
	    

}
