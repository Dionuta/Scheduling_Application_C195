package View_Controller.Controllers;

import Helpers.DAO.AppointmentDAO;
import Helpers.DBQuery;
import Helpers.JDBC;
import Helpers.LoginInterface;
import Models.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


///**
// * Interface for lambda function
// * Just a test of lambda function.
// */
//interface StringFunction {
//    String run(String str);
//}

/**
 * Login Controller. All functions related to Login page.
 */
public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public Label UsernameLabel;
    public Label PasswordLabel;
    public TextField usernameTextField;
    public Button LoginButton;
    public javafx.scene.control.PasswordField passwordField;
    public Label LocationLabel;
    public Label zoneLabel;
    private Alert a = new Alert(Alert.AlertType.NONE);

    private int userID;

    ResourceBundle rb = ResourceBundle.getBundle("Helpers/lan", Locale.getDefault());

    /**
     * Change text according to location. English and French only languages at the moment.
     */

    public void setLanguage(){
        try {
            UsernameLabel.setText(rb.getString("username"));
            PasswordLabel.setText(rb.getString("password"));
            usernameTextField.setPromptText(rb.getString("username"));
            passwordField.setPromptText(rb.getString("password"));
            LoginButton.setText(rb.getString("login"));
            LocationLabel.setText(rb.getString("timezone"));
            zoneLabel.setText(ZoneId.systemDefault().getId());
        }catch(Exception exception){
            System.out.println(exception.getLocalizedMessage() +
                    " Information needed to establish location not found! Language is set to english.");
        }


    }


    /**
     * Value returning lambda expression
     * Returns a messages with username attached
     */

    LoginInterface message = (s) -> s + " " + usernameTextField.getText();

//    /**
//     * @param str String
//     * @param format what will be add to end of string
//     * @return new String that has been formatted
//     */
//
//    public static String printFormatted(String str, StringFunction format) {
//        String result = format.run(str);
//        return result;
//    }


    /**
     * Logs user into the application. Passes username and password to loginVerification. Click event.
     * @param actionEvent click event
     * @throws IOException throws IOException
     */

    public void loginHandler(ActionEvent actionEvent) throws IOException {

        String userName = usernameTextField.getText();
        String password = passwordField.getText();



        if(loginVerification(userName, password)) {
            upComingAppointment();
            // message Lambda function being used
            logOfLogs( message.loginMessage("Successful Login By:"));
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                    ("/View_Controller/Views/MainMenu.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        else {
            logOfLogs(message.loginMessage("Unsuccessful Login By:"));
            a.setAlertType(Alert.AlertType.ERROR);
            a.setHeaderText(rb.getString("errorHeader"));
            a.setTitle(rb.getString("errorTitle"));
            a.setContentText(rb.getString("errorContent"));
            a.show();

        }

    }

    /**
     * LAMBDA FOREACH USED!!!
     * Let's user know of upcoming appointments based on user id.
     * Only triggered if appointment is with in 15 minutes of time scheduled.
     */

    public  void upComingAppointment(){

        ObservableList<Appointment> upComingApp = AppointmentDAO.getAllAppointments();

        //Used because you can't use break in a foreach loop. If false runs 2nd alert.
        AtomicBoolean hasUpcoming = new AtomicBoolean(false);

// The lambda expression checks to see if each appointment is within 15 minutes of now if so the Alert is triggered.
        // Justification of usage: Easy to read and less buggy than for loop. Downside: no breaks.
       upComingApp.forEach((app) ->{
            if(ChronoUnit.MINUTES.between(LocalDateTime.now(), app.getStart()) >= 0 &&
                    ChronoUnit.MINUTES.between(LocalDateTime.now(), app.getStart()) <= 15 ){
               a.setAlertType(Alert.AlertType.INFORMATION);
               a.setTitle(rb.getString("Message"));
               a.setHeaderText(rb.getString("Message"));
                //message Lambda function being used
               a.setContentText(message.loginMessage(rb.getString("user")) + " " + rb.getString("hasApp") + "ID:" +
                       " "+ app.getAppointment_id() + " " +
                       app.getTitle() +" "+ rb.getString("at") + " " + app.getStart().getHour() +":"
              + app.getStart().getMinute() + " " + app.getStart().getMonthValue() + "/" + app.getStart().getDayOfMonth()  + "/" + app.getStart().getYear());
               a.showAndWait();
                    hasUpcoming.set(true);
                 }
                   });

       if (hasUpcoming.get() == false){
         a.setAlertType(Alert.AlertType.INFORMATION);
           // message Lambda function being used
         a.setContentText(message.loginMessage(rb.getString("user")) + " " + rb.getString("hasNoApp") );
         a.showAndWait();
       }


    }


    /**
     * Keeps a log of successful and unsuccessful login attempts.
     * @param login text if login was successful or not and by who.
     */



    public void logOfLogs(String  login){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("login_activity.txt",
                    true)));
            out.println(LocalDateTime.now()+ " " + login);
            out.close();
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }
    }

    /**
     * Verifies username and password
     * @param userName String
     * @param password String
     * @return boolean
     */

    public boolean loginVerification(String userName, String password){
        try {
            String selectStatement = "SELECT * FROM users WHERE User_Name=? AND Password=?";
            DBQuery.setPreparedStatement(JDBC.getConnection(), selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }

        } catch (SQLException sql){
            System.out.println(sql);
        }
        return false;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLanguage();
    }


}
