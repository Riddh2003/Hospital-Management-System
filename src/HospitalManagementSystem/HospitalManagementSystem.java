package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String userName = "root";
    private static final String password = "riddhmodi";
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try{
            Connection connection = DriverManager.getConnection(url,userName,password);
            Patient patient = new Patient(connection,scanner);
            Doctor doctor = new Doctor(connection);
            Appointments appointments = new Appointments();
            while (true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1=>Add Patients");
                System.out.println("2=>View Patients");
                System.out.println("3=>View Doctors");
                System.out.println("4=>Book Appointment");
                System.out.println("5=>Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        //Add Patients
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        //View Patients
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        //View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        //Book Appointment
                        appointments.bookAppointment(patient,doctor,connection,scanner);
                        System.out.println();
                        break;
                    case 5:
                        //Exit
                        System.out.println("!!!THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM!!!");
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        break;
                }
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
