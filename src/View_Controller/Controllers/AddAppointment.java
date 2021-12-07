package View_Controller.Controllers;

import Helpers.DAO.AppointmentDAO;
import Helpers.DBQuery;
import Helpers.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField typeTextField;
    public ComboBox<String> customerComboBox;
    public ComboBox<String> userComboBox;
    public ComboBox<String> contactComboBox;
    public ComboBox endHourCombo;
    public ComboBox endMinCombo;
    public ComboBox strHoursCombo;
    public ComboBox strMinCombo;
    public DatePicker endDatePicker;
    public DatePicker strDatePicker;

    private Stage stage;
    private Scene scene;
    private Parent root;


    private Alert a = new Alert(Alert.AlertType.NONE);

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    /**
     * Returns user to main screen uses alert lambda to take in a user conformation responses
     * @param actionEvent click event
     */
    public void cancelPartHandle(ActionEvent actionEvent){
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure?");


        a.showAndWait().ifPresent((response -> {

            if (response == ButtonType.OK) {
                try {


                    root = FXMLLoader.load(Objects.requireNonNull(getClass().
                            getResource("/View_Controller/Views/MainMenu.fxml")));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }

            }
        }));
    }

    /**
     * Populates the contact combo box with inform database
     */

    public void initializeContact(){
        String statement = "SELECT * FROM contacts";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                contactComboBox.getItems().add(rs.getString(2));
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Populates the customers combo box with inform database
     */

    public void initializeCustomers(){
        String statement = "SELECT * FROM customers";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                customerComboBox.getItems().add(rs.getString(1));
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Populates the users combo box with inform database
     */

    public void initializeUsers(){
        String statement = "SELECT * FROM users";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                userComboBox.getItems().add(rs.getString(1));
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int contactSearch(String contact){
        int id = 0;
        String statement = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ? ";
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, contact);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                id = rs.getInt("Contact_ID");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void saveHandler(ActionEvent actionEvent) throws IOException{
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        String start = startTimeFormatter();
        String end = endTimeFormatter();
        String user = userComboBox.getValue();
        String  customer = customerComboBox.getValue();
        int contact = contactSearch(contactComboBox.getValue());




        if(title == null || user == null || customer == null || contact  < 1 || start == null || end ==null){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You can not have missing values! Please look over appointment.");
            a.showAndWait();

        }
        else if  (overlapCheck(start, end)){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please change time for appointment! The appointment time conflicts with another!");
            a.showAndWait();
        }else if(outsideHours(start, end)){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Business hours are defined as 8:00 to 22:00 EST, including weekends. Time(s) entered falls " +
                    "outside of those hours please change. ");
            a.showAndWait();
        }else {


            AppointmentDAO.addAppointment(title, description, location, type, start,
                    end, Integer.parseInt(customer), Integer.parseInt(user), contact);

            root = FXMLLoader.load(Objects.requireNonNull(getClass().
                    getResource("/View_Controller/Views/MainMenu.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }


    /**
     * Checks if overlap with any appointment in database
     * @param start start of appointment
     * @param end end of appointment
     * @return true or false depending on if start or end found in database.
     */

    public boolean overlapCheck(String start, String end){

        String statement = "SELECT * FROM appointments  where  Start  <= ? and End  >= ?";
        LocalDateTime startUTC = LocalDateTime.parse(start).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime endUTC =   LocalDateTime.parse(end).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,endUTC.toString());
            ps.setString(2,startUTC.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Check if the appointment is with in business hours.
     * @param start start of appointment
     * @param end end of appointment
     * @return true if outside of business hours and false if not
     */

    public boolean outsideHours(String start, String end){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime strUTC = LocalDateTime.parse(start, formatter);
        LocalDateTime endUTC = LocalDateTime.parse(end, formatter);


        LocalDateTime strLocal = strUTC.atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endLocal = endUTC.atZone(ZoneId.systemDefault()).toLocalDateTime();

        ZonedDateTime utcStartZoned = strLocal.atZone(ZoneId.systemDefault());
        ZonedDateTime startLocalZoned = utcStartZoned.withZoneSameInstant(ZoneId.of("US/Eastern"));

        ZonedDateTime utcEndZoned = endLocal.atZone(ZoneId.systemDefault());
        ZonedDateTime endLocalZoned = utcEndZoned.withZoneSameInstant(ZoneId.of("US/Eastern"));




       if (startLocalZoned.getHour() < 8 || endLocalZoned.getHour() >= 22 ||
               endLocalZoned.getHour() < 8 || startLocalZoned.getHour() >= 22 ){
           return true;
       }
       return false;
    }


    /**
     * Formats start appointment
     * @return formatted String value
     */

    public String startTimeFormatter(){


            LocalDate date = strDatePicker.getValue();
            String hour = (String) strHoursCombo.getValue();
            String minute = (String) strMinCombo.getValue();
            if(date == null || hour == null || minute == null ){
                return null;
            }else{


            LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));

            LocalDateTime start = LocalDateTime.of(date, time);

            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String newStart = customFormatter.format(start);


            return newStart;
            }

    }

    /**
     * Formats end appointment
     * @return formatted String value
     */

    public String endTimeFormatter(){

        LocalDate date = endDatePicker.getValue();
        String hour = (String) endHourCombo.getValue();
        String minute = (String) endMinCombo.getValue();
        if(date == null || hour == null || minute == null ){
            return null;
        }else {
//            System.out.println(Integer.parseInt(hour));

            LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));

            LocalDateTime end = LocalDateTime.of(date, time);



//            System.out.println(endZT);


            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//            System.out.println(utcZoned);
            String newEnd = customFormatter.format(end);
            return newEnd;
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeContact();
        initializeCustomers();
        initializeUsers();
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        strHoursCombo.setItems(hours);
        strMinCombo.setItems(minutes);
        endHourCombo.setItems(hours);
        endMinCombo.setItems(minutes);
    }


}
