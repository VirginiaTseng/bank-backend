 package daoTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentMain {
	

	public static void main(String[] args)throws SQLException {
		
		// CREATE TABLE ---This is just a label, delete or comment off before running code
	 /*
		Connection connection = Database.getConnection();
		String sql = "CREATE TABLE IF NOT EXISTS student(id int AUTO_INCREMENT, student_id int NOT NULL, Student_Name VARCHAR(30), PRIMARY KEY(id))";
		PreparedStatement prepardStatement = connection.prepareStatement(sql);
		prepardStatement.execute(sql); 
		
		prepardStatement.close();
		connection.close();
		*/
		
		
		
		// RETRIEVE RECORD ---This is just a label, delete or comment off before running code
		// Execute retrieve method on StudentDAO
		/*
		StudentDao studentDao =  new StudentDaoImpl();
		Student student = studentDao.get(3);
		System.out.println(student); */
	
		
		// INSERT RECORD ---This is just a label, delete or comment off before running code
		//Call the INSERT method from StudentDAO
		/*
		StudentDao studentDao = new StudentDaoImpl();
		Student student = new Student(0,1235, "Daryl2");
		
		int result = studentDao.insert(student);
		
		System.out.println(result); 
		*/
		
		
		// UPDATE RECORD ---This is just a label, delete or comment off before running code
		
		//call the UPDATE method from StudentDAO
		
		/*
		StudentDao studentDao = new StudentDaoImpl();
		Student student = new Student(0,1000, "Daryl");
		
		int result = studentDao.update(student);
		
		System.out.println(result); 
		*/
		
		
		// DELETE RECORD ---This is just a label, delete or comment off before running code
		//call the Delete method from our DAO
		
		StudentDao studentDao = new StudentDaoImpl();
		//Student student = new Student(1,1000,"Daryl");
		Student student = studentDao.get(3);
		System.out.println(student);
		
		int result = studentDao.delete(student);
		
		System.out.println(result); 
		

		
	}

}
