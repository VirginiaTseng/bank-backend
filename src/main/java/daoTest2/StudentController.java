 package daoTest2;

import java.sql.SQLException;


public class StudentController {

	private StudentDao studentDao;
    private StudentView studentView;

    public StudentController(StudentDao studentDao, StudentView studentView) {
        this.studentDao = studentDao;
        this.studentView = studentView;
    }

    public void displayUser(int Id) throws SQLException {
        Student student = studentDao.get(Id);
        studentView.displayUserDetails(student);
    }

   /* public void displayAllUsers() throws SQLException{
        List<User> users = userDao.getAllUsers();
        userView.displayAllUserDetails(users);
    } */

    public void addUser(Student student) throws SQLException {
        studentDao.insert(student);
        studentView.displayUserAddedMessage(student);
    }

   /* public void updateUser(User user) throws SQLException {
        userDao.updateUser(user);
        userView.displayUserUpdatedMessage(user);
    }

    public void deleteUser(int userId) throws SQLException {
        User user = userDao.getUserById(userId);
        userDao.deleteUser(userId);
        userView.displayUserDeletedMessage(user);
    }
*/
    // Other controller methods as needed
    
	
	
	
}
