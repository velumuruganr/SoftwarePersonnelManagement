import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBase {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    int status;
    
    public void get_connection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spmdb", "root", "Velu@2002");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int add_employee(int empId, String name, String dob,
            String email, String mobile, String designation, String salary){
        get_connection();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO EMPLOYEE_TABLE VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, empId);
            stmt.setString(2, name);
            stmt.setString(3, dob);
            stmt.setString(4, email);
            stmt.setString(5, mobile);
            stmt.setString(6, designation);
            stmt.setString(7, salary);
            status = stmt.executeUpdate();
            connection.close();
        } catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return status;
    }
    
    public int update_employee(int empId, String name, String dob,
            String email, String mobile, String designation, String salary){
        get_connection();
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE EMPLOYEE_TABLE SET NAME=?, DOB=?, EMAIL=?, MOBILE=?, DESIGNATION=?, SALARY=? WHERE EMPID=?");
            stmt.setString(1, name);
            stmt.setString(2, dob);
            stmt.setString(3, email);
            stmt.setString(4, mobile);
            stmt.setString(5, designation);
            stmt.setString(6, salary);
            stmt.setInt(7, empId);
            status = stmt.executeUpdate();
            connection.close();
        } catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return status;
    }
    
    public int delete_employee(int empId){
        get_connection();
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM EMPLOYEE_TABLE WHERE EMPID=?");
            stmt.setInt(1, empId);
            status = stmt.executeUpdate();
            connection.close();
        } catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return status;
    }
    
    public int add_task(int taskId){
        get_connection();
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO TASK_TABLE VALUES(?)");
            stmt.setInt(1, taskId);
            status = stmt.executeUpdate();
            connection.close();
        } catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return status;
    }
}
