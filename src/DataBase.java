import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBase {
    Connection connection;
    Statement statement;
    ResultSet resultSet = null;
    int status = 0;
    
    public void get_connection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spmdb", "root", "Velu@2002");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int login(String username, String password){
        get_connection();
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM USER_TABLE WHERE USERNAME=?");
            stmt.setString(1, username);
            resultSet = stmt.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    if(resultSet.getString("role").equals("admin")){
                        return 1;
                    }else{
                        return 2;
                    }
                }else{
                    return 401;
                }
            }
            
        }catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return 0;
    }
    
    
    public int add_employee(String name, String dob,
            String email, String mobile, String designation, String salary){
        get_connection();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO EMPLOYEES_TABLE (Name, DOB, Email, Mobile, Designation, Salary) VALUES(?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, dob);
            stmt.setString(3, email);
            stmt.setString(4, mobile);
            stmt.setString(5, designation);
            stmt.setString(6, salary);
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
            PreparedStatement stmt = connection.prepareStatement("UPDATE EMPLOYEES_TABLE SET NAME=?, DOB=?, EMAIL=?, MOBILE=?, DESIGNATION=?, SALARY=? WHERE EMPID=?");
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
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM EMPLOYEES_TABLE WHERE EMPID=?");
            stmt.setInt(1, empId);
            status = stmt.executeUpdate();
            connection.close();
        } catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return status;
    }
    
    public ResultSet select_employee(){
        get_connection();
        try{
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM EMPLOYEES_TABLE";
            resultSet = stmt.executeQuery(query);
            
        }catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return resultSet;
    }
    
    public ResultSet get_employee(String empId){
        get_connection();
        try{
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM EMPLOYEES_TABLE WHERE EMPID="+empId;
            resultSet = stmt.executeQuery(query);
            
        }catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return resultSet;
    }
    
    public ResultSet select_tasks(){
        get_connection();
        try{
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM TASKS_TABLE";
            resultSet = stmt.executeQuery(query);
            
        }catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return resultSet;
    }
    
    public ResultSet select_task(String empId){
        get_connection();
        try{
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM TASKS_TABLE WHERE EMPID="+empId;
            resultSet = stmt.executeQuery(query);
            
        }catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return resultSet;
    }
    
    
    
    public int add_task(int empId, String empName, String designation, String task, String deadline, String description){
        get_connection();
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO TASKS_TABLE(empId, name, designation, task, deadline, description) VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, empId);
            stmt.setString(2, empName);
            stmt.setString(3, designation);
            stmt.setString(4, task);
            stmt.setString(5, deadline);
            stmt.setString(6, description);
            status = stmt.executeUpdate();
            connection.close();
        } catch (SQLException sQLException) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        return status;
    }
}
