package View_Controller.Controllers;

import Helpers.DAO.AppointmentDAO;
import Helpers.DAO.CustomerDAO;
import Helpers.DAO.ReportDAO;
import Helpers.DBQuery;
import Helpers.JDBC;
import Models.Appointment;
import Models.Customer;
import Models.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    public TableView <Report> typesTable;
    public TableColumn <Report, String>type;
    public TableColumn <Report, Integer>total;
    public TableColumn <Report, String>month;
    public ComboBox <String> contactByCombo;

    public TableView <Appointment> appointmentsTable;
    public TableColumn <Appointment, Integer> appointmentID;
    public TableColumn <Appointment, String> title;
    public TableColumn <Appointment, String> description;
    public TableColumn <Appointment, String> location;
    public TableColumn <Appointment, String> type1;
    public TableColumn <Appointment, LocalDateTime> start;
    public TableColumn <Appointment, LocalDateTime> end;
    public TableColumn <Appointment, Integer>a_CustomerID;

    public TableColumn <Appointment, Integer> userID;

    public TableView <Customer> customerTable;
    public TableColumn <Customer, Integer> customerID;
    public TableColumn <Customer, String> name;
    public TableColumn <Customer, String> address;
    public TableColumn <Customer, String> postalCode;
    public TableColumn <Customer, String> phone;
    public TableColumn <Customer, String> divisionName;
    public ComboBox <String> divisionByCombo;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTables();
        initializeContact();
        initializeDivision();

    }

    /**
     * Fills tables with information from database
     */
    public void setTables(){
        typesTable.setItems(ReportDAO.getAllReports());
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        appointmentsTable.setItems(AppointmentDAO.getAllAppointments());
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        a_CustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        userID.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        customerTable.setItems(CustomerDAO.getAllCustomers());
        customerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postal_code"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionName.setCellValueFactory(new PropertyValueFactory<>("divisionName"));

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
                contactByCombo.getItems().add(rs.getString(2));
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initializeDivision(){
        String statement = "SELECT * FROM first_level_divisions";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                divisionByCombo.getItems().add(rs.getString(2));
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * appointment are shown depending on value from contactCombo.
     * @param actionEvent SELECT EVENT
     */
    public void contactByComboHandler(ActionEvent actionEvent) {

        appointmentsTable.setItems(AppointmentDAO.getAllAppointmentsByContact(contactByCombo.getValue()));
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        a_CustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        userID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    }

    /**
     * customers are shown depending on value from divisionByCombo.
     * @param actionEvent SELECT EVENT
     */

    public void DivisionByComboHandler(ActionEvent actionEvent) {
        customerTable.setItems(CustomerDAO.getAllCustomersByDivision(divisionByCombo.getValue()));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postal_code"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionName.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
    }

    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/View_Controller/Views/MainMenu.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
