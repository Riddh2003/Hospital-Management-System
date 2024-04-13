package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointments {
    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        System.out.println("Enter Patient ID: ");
        int p_id = scanner.nextInt();
        System.out.println("Enter Doctor ID: ");
        int d_id = scanner.nextInt();
        System.out.println("Enter Appointment Date(YYYY-MM-DD)");
        String a_date = scanner.next();
        if(patient.getPateintByID(p_id) && doctor.getDoctorByID(d_id)){
            if(checkDoctorAvailability(d_id,a_date,connection)){
                String appointmentquery = "INSERT INTO APPOINTMENTS(P_ID,D_ID,APPOINTMENT_DATE) VALUES(?,?,?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentquery);
                    preparedStatement.setInt(1,p_id);
                    preparedStatement.setInt(2,d_id);
                    preparedStatement.setString(3,a_date);
                    int affectedRows = preparedStatement.executeUpdate();
                    if(affectedRows>0){
                        System.out.println("Appointment Booked");
                    }
                    else{
                        System.out.println("Failed to booked appointment");
                    }
                }catch (SQLException e){
                    System.out.println(e);
                }
            }
            else {
                System.out.println("Doctor not available on this date!!!");
            }
        }
        else {
            System.out.println("Either Doctor or Patient doesn't exist!!!");
        }
    }
    public static boolean checkDoctorAvailability(int d_id, String a_date, Connection connection){
        String query = "SELECT COUNT(*) FROM APPOINTMENTS WHERE D_ID = ? AND APPOINTMENT_DATE = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,d_id);
            preparedStatement.setString(2,a_date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if (count==0){
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }
}
