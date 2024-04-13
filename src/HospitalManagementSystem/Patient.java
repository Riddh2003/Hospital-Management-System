package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    Connection connection;
    Scanner scanner;
    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }
    public void addPatient(){
        System.out.println("Enter Patient Name: ");
        String p_name = scanner.next();
        System.out.println("Enter Patient Age: ");
        int p_age = scanner.nextInt();
        System.out.println("Enter Patient Gender: ");
        String p_gender = scanner.next();
        try{
            String query = "INSERT INTO PATIENTS(P_NAME,P_AGE,P_GENDER) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,p_name);
            preparedStatement.setInt(2,p_age);
            preparedStatement.setString(3,p_gender);
            int affectwedRows = preparedStatement.executeUpdate();
            if(affectwedRows>0){
                System.out.println("Patients Data Successfully add.");
            }
            else{
                System.out.println("Failed to add Patient!!!");
            }
            preparedStatement.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public void viewPatients(){
        String query = "SELECT * FROM PATIENTS";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------+----------------+----------+------------+");
            System.out.println("| P_ID | P_NAME         | P_AGE    | P_GENDER   |");
            System.out.println("+------+----------------+----------+------------+");
            while (resultSet.next()){
                int p_id = resultSet.getInt("P_ID");
                String p_name = resultSet.getString("P_NAME");
                int p_age = resultSet.getInt("P_AGE");
                String p_gender = resultSet.getString("P_GENDER");
                System.out.printf("|%-6s|%-16s|%-10s|%-12s");
            }
            System.out.println("+------+----------------+----------+------------+");
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    public boolean getPateintByID(int p_id){
        String query = "SELECT * FROM PATIENTS WHERE P_ID = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(0,p_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }
}
