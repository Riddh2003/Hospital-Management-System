package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    Connection connection;
    public Doctor(Connection connection){
        this.connection = connection;
    }

    //DOCTORS DATA ADD THROUGH THE ADMISTRATER

    public void viewDoctors(){
        String query = "SELECT * FROM DOCTORS";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("DOCTORS: ");
            System.out.println("+------+----------------+----------------+");
            System.out.println("| D_ID | D_NAME         | D_DEPARTMENT   |");
            System.out.println("+------+----------------+----------------+");
            while (resultSet.next()){
                int d_id = resultSet.getInt("D_ID");
                String d_name = resultSet.getString("D_NAME");
                String d_department = resultSet.getString("D_DEPARTMENT");
                System.out.printf("| %-4s| %-15s| %-15s|\n",d_id,d_name,d_department);
            }
            System.out.println("+------+----------------+----------------+");
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
    public boolean getDoctorByID(int d_id){
        String query = "SELECT * FROM DOCTORS WHERE D_ID = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(0,d_id);
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
