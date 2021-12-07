package Helpers.DAO;

import Helpers.DBQuery;
import Helpers.JDBC;
import Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class AppointmentDAO {


    /**
     * getAllAppointments Order By ID.
     * @return a ObservableList of appointments currently in the Database.
     */
    public static ObservableList<Appointment> getAllAppointments() {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String statement = "SELECT a.Appointment_ID, a.Title," +
                " a.Description, a.Location, c.Contact_ID, c.Contact_Name, a.Type, " +
                "a.Start, a.End, a.Customer_ID, a.Create_Date," +
                " a.Created_By, a.Last_Update, a.Last_Updated_BY, a.User_ID FROM" +

                " appointments a INNER JOIN contacts c ON a.Contact_ID = c.Contact_ID Order by a.Appointment_ID";
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact_name = rs.getString("Contact_Name");
                String type = rs.getString("Type");

                LocalDate startDate = rs.getDate("Start").toLocalDate();
                LocalTime startTime = rs.getTime("Start").toLocalTime();
                LocalDateTime start =LocalDateTime.of(startDate, startTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                LocalDate endDate = rs.getDate("End").toLocalDate();
                LocalTime endTime = rs.getTime("End").toLocalTime();
                LocalDateTime end = LocalDateTime.of(endDate,endTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();



                int customer_id = rs.getInt("Customer_ID");
                int user_id = rs.getInt("User_ID");
                LocalDateTime create_date = rs.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = rs.getString("Created_By");
                LocalDateTime last_update = rs.getTimestamp("Last_Update").toLocalDateTime();
                String last_updated_by = rs.getString("Last_Updated_By");

                Appointment appointment = new Appointment(appID, title, description, location, contact_name, type, start,
                        end, create_date, created_by, last_update, last_updated_by, customer_id, user_id);

                allAppointments.add(appointment);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return allAppointments;
    }


    /**
     * getAllAppointments Returns appointment happening this week.
     * @return a ObservableList of appointments currently in the Database.
     */
    public static ObservableList<Appointment> getAllAppointmentsWeek() {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String statement = "SELECT a.Appointment_ID, a.Title," +
                " a.Description, a.Location, c.Contact_ID, c.Contact_Name, a.Type, " +
                "a.Start, a.End, a.Customer_ID, a.Create_Date," +
                " a.Created_By, a.Last_Update, a.Last_Updated_BY, a.User_ID FROM" +

                " appointments a INNER JOIN contacts c ON a.Contact_ID = c.Contact_ID AND week(Start, 0) = " +
                "week(curdate(), 0) Order BY Start;";
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact_name = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                LocalDate startDate = rs.getDate("Start").toLocalDate();
                LocalTime startTime = rs.getTime("Start").toLocalTime();
                LocalDateTime start =LocalDateTime.of(startDate, startTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                LocalDate endDate = rs.getDate("End").toLocalDate();
                LocalTime endTime = rs.getTime("End").toLocalTime();
                LocalDateTime end = LocalDateTime.of(endDate,endTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");
                int user_id = rs.getInt("User_ID");
                LocalDateTime create_date = rs.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = rs.getString("Created_By");
                LocalDateTime last_update = rs.getTimestamp("Last_Update").toLocalDateTime();
                String last_updated_by = rs.getString("Last_Updated_By");

                Appointment appointment = new Appointment(appID, title, description, location, contact_name, type, start,
                        end, create_date, created_by, last_update, last_updated_by, customer_id, user_id);

                allAppointments.add(appointment);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return allAppointments;
    }
    /**
     * getAllAppointments Returns appointment happening this Month.
     * @return a ObservableList of appointments currently in the Database.
     */
    public static ObservableList<Appointment> getAllAppointmentsMonth() {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String statement = "SELECT a.Appointment_ID, a.Title," +
                " a.Description, a.Location, c.Contact_ID, c.Contact_Name, a.Type, " +
                "a.Start, a.End, a.Customer_ID, a.Create_Date," +
                " a.Created_By, a.Last_Update, a.Last_Updated_BY, a.User_ID FROM" +

                " appointments a INNER JOIN contacts c ON a.Contact_ID = c.Contact_ID AND month(Start) = " +
                "month(curdate()) Order BY Start;";
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact_name = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                LocalDate startDate = rs.getDate("Start").toLocalDate();
                LocalTime startTime = rs.getTime("Start").toLocalTime();
                LocalDateTime start =LocalDateTime.of(startDate, startTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                LocalDate endDate = rs.getDate("End").toLocalDate();
                LocalTime endTime = rs.getTime("End").toLocalTime();
                LocalDateTime end = LocalDateTime.of(endDate,endTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");
                int user_id = rs.getInt("User_ID");
                LocalDateTime create_date = rs.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = rs.getString("Created_By");
                LocalDateTime last_update = rs.getTimestamp("Last_Update").toLocalDateTime();
                String last_updated_by = rs.getString("Last_Updated_By");

                Appointment appointment = new Appointment(appID, title, description, location, contact_name, type, start,
                        end, create_date, created_by, last_update, last_updated_by, customer_id, user_id);

                allAppointments.add(appointment);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return allAppointments;
    }

    /**
     * deleteAppointment remove item database.
     * @param id the id of item to be deleted. Type: Integer
     */

    public static void deleteAppointment(int id){
        String statement = "DELETE From appointments WHERE Appointment_ID = ?";


        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setInt(1, id);
            ps.executeUpdate();

        }catch(SQLException e){

            System.out.println(e.getMessage());

        }
    }


    public static void addAppointment(String title, String description, String location,
                                      String type, String start, String end,
                                        int customer_id, int user_id, int contact_id) {
        String statement = "INSERT INTO appointments(Title, Description," +
                " Location, Type, Start," +
                " End, Create_Date, Created_by," +
                "Last_Update, Last_Updated_By, Customer_ID, " +
                "User_ID, Contact_ID) " +
                "VALUES(?, ?, ?, ?, ?, ?,NOW(), 'Tay', NOW(), " +
                "'Tay', ?,?, ?)";

        LocalDateTime startUTC = LocalDateTime.parse(start).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime endUTC =   LocalDateTime.parse(end).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();


        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, Timestamp.valueOf(startUTC).toString());
            ps.setString(6, Timestamp.valueOf(endUTC).toString());
            ps.setInt(7, customer_id);
            ps.setInt(8, user_id);
            ps.setInt(9, contact_id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateAppointment(int id, String title, String description, String location,
                                      String type, String start, String end,
                                      int customer_id, int user_id, int contact_id) {
        String statement ="UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, " +
                "End = ?, Last_Update = Now(), Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        LocalDateTime startUTC = LocalDateTime.parse(start).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime endUTC =   LocalDateTime.parse(end).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setString(1, title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setString(5, Timestamp.valueOf(startUTC).toString());
            ps.setString(6, Timestamp.valueOf(endUTC).toString());
            ps.setInt(7,customer_id);
            ps.setInt(8,user_id);
            ps.setInt(9,contact_id);
            ps.setInt(10, id);

            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

        public static ObservableList<Appointment> getByUserAllAppointments(int userID) {

            ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
            String statement = "SELECT a.Appointment_ID, a.Title," +
                    " a.Description, a.Location, c.Contact_ID, c.Contact_Name, a.Type, " +
                    "a.Start, a.End, a.Customer_ID, a.Create_Date," +
                    " a.Created_By, a.Last_Update, a.Last_Updated_BY, a.User_ID FROM" +

                    " appointments a INNER JOIN contacts c ON a.Contact_ID = c.Contact_ID WHERE User_ID = ?";
            try{
                DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
                PreparedStatement ps = DBQuery.getPreparedStatement();
                ps.setInt(1, userID);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int appID = rs.getInt("Appointment_ID");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String location = rs.getString("Location");
                    String contact_name = rs.getString("Contact_Name");
                    String type = rs.getString("Type");
                    LocalDate startDate = rs.getDate("Start").toLocalDate();
                    LocalTime startTime = rs.getTime("Start").toLocalTime();
                    LocalDateTime start =LocalDateTime.of(startDate, startTime).atZone(ZoneId.of("UTC"))
                            .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDate endDate = rs.getDate("End").toLocalDate();
                    LocalTime endTime = rs.getTime("End").toLocalTime();
                    LocalDateTime end = LocalDateTime.of(endDate,endTime).atZone(ZoneId.of("UTC"))
                            .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                    int customer_id = rs.getInt("Customer_ID");
                    int user_id = rs.getInt("User_ID");
                    LocalDateTime create_date = rs.getTimestamp("Create_Date").toLocalDateTime();
                    String created_by = rs.getString("Created_By");
                    LocalDateTime last_update = rs.getTimestamp("Last_Update").toLocalDateTime();
                    String last_updated_by = rs.getString("Last_Updated_By");

                    Appointment appointment = new Appointment(appID, title, description, location, contact_name, type, start,
                            end, create_date, created_by, last_update, last_updated_by, customer_id, user_id);

                    allAppointments.add(appointment);
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return allAppointments;
        }

        public static int findUserByName(String name) {
        String statement = "SELECT User_ID From users WHERE User_Name = ?";
        int id = 0;
        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                id = rs.getInt("User_ID");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
            return id;
        }

    public static ObservableList<Appointment> getAllAppointmentsByContact(String contact) {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String statement = "SELECT a.Appointment_ID, a.Title," +
                " a.Description, a.Location, c.Contact_ID, c.Contact_Name, a.Type, " +
                "a.Start, a.End, a.Customer_ID, a.Create_Date," +
                " a.Created_By, a.Last_Update, a.Last_Updated_BY, a.User_ID FROM" +

                " appointments a INNER JOIN contacts c ON a.Contact_ID = c.Contact_ID WHERE c.Contact_Name = ?";
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, contact);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact_name = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                LocalDate startDate = rs.getDate("Start").toLocalDate();
                LocalTime startTime = rs.getTime("Start").toLocalTime();
                LocalDateTime start =LocalDateTime.of(startDate, startTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                LocalDate endDate = rs.getDate("End").toLocalDate();
                LocalTime endTime = rs.getTime("End").toLocalTime();
                LocalDateTime end = LocalDateTime.of(endDate,endTime).atZone(ZoneId.of("UTC"))
                        .withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");
                int user_id = rs.getInt("User_ID");
                LocalDateTime create_date = rs.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = rs.getString("Created_By");
                LocalDateTime last_update = rs.getTimestamp("Last_Update").toLocalDateTime();
                String last_updated_by = rs.getString("Last_Updated_By");

                Appointment appointment = new Appointment(appID, title, description, location, contact_name, type, start,
                        end, create_date, created_by, last_update, last_updated_by, customer_id, user_id);

                allAppointments.add(appointment);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return allAppointments;
    }



}

