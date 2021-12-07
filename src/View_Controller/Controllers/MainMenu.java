package View_Controller.Controllers;

import Helpers.DAO.AppointmentDAO;
import Helpers.DAO.CustomerDAO;
import Models.Appointment;
import Models.Customer;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {


    public ComboBox OrderByCombo;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public TableView <Appointment> appointmentsTable;
    public TableColumn <Appointment, Integer> appointmentID;
    public TableColumn <Appointment, String> title;
    public TableColumn <Appointment, String> description;
    public TableColumn <Appointment, String> location;
    public TableColumn <Appointment, String> type;
    public TableColumn <Appointment, String> start;
    public TableColumn <Appointment, String> end;
    public TableColumn <Appointment, Integer>a_CustomerID;
    public TableColumn <Appointment, String> contactName;
    public TableColumn <Appointment, Integer> userID;
    
    public TableView <Customer> customerTable;
    public TableColumn <Customer, Integer> customerID;
    public TableColumn <Customer, String> name;
    public TableColumn <Customer, String> address;
    public TableColumn <Customer, String> postalCode;
    public TableColumn <Customer, String> phone;
    public TableColumn <Customer, String> divisionName;


    private Alert a = new Alert(Alert.AlertType.NONE);

    public static Customer customerToUpdate;
    public static Appointment appointmentToUpdate;

    ObservableList<String> options = FXCollections.observableArrayList();

    public void setTables(){

        appointmentsTable.setItems(AppointmentDAO.getAllAppointments());
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contact_name"));
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
     * Delete Appointment from database
     * @param actionEvent click event
     */
    public void deleteAppointmentHandler(ActionEvent actionEvent)  {
        try {
        int appointment = appointmentsTable.getSelectionModel().getSelectedItem().getAppointment_id();
        if (appointment < 1 )
            return;
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure want to delete Appointment ID: " + appointmentsTable.getSelectionModel().
                getSelectedItem().getAppointment_id() + " Type: " + appointmentsTable.getSelectionModel().
                getSelectedItem().getType() +"?");
        a.showAndWait();
        if (a.getResult() == ButtonType.OK) {
            AppointmentDAO.deleteAppointment(appointment);
            setTables();
        }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete Customer from database
     * @param actionEvent click event
     */

    public void deleteCustomerHandler(ActionEvent actionEvent){
        int customerId = customerTable.getSelectionModel().getSelectedItem().getCustomer_id();
        if (customerId < 1)
            return;
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure want to delete " + customerTable.getSelectionModel().getSelectedItem().
                getCustomer_name() + "?" );
        a.showAndWait();
        if (a.getResult() == ButtonType.OK) {
            CustomerDAO.deleteCustomer(customerId);
            setTables();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        options.addAll("ID", "Month", "Week");
        OrderByCombo.setItems(options);
        OrderByCombo.setValue("ID");
       setTables();

    }

    /**
     * Switch to Add Appointment view
     * @param actionEvent listen for event to trigger scene change
     * @throws IOException prevent app from crashing if IO error happens
     */

    public void ToAddAppointmentHandler(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/View_Controller/Views/AddAppointment.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch to Add Customer view
     * @param actionEvent listen for event to trigger scene change
     * @throws IOException prevent app from crashing if IO error happens
     */

    public void ToAddCustomerHandler(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/View_Controller/Views/AddCustomer.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Passes selected item to customer update Screen
     * @return customerToUpdate it item being passed
     */

    public static Customer passToUpdate(){

        return customerToUpdate;
    }

    public static Appointment passToAUpdate(){

        return appointmentToUpdate;
    }




    public void toCustomerUpdateHandler(ActionEvent actionEvent) throws IOException {
        customerToUpdate = customerTable.getSelectionModel().getSelectedItem();

        if (customerToUpdate == null)
            return;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("/View_Controller/Views/UpdateCustomer.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toUpdateAppointmentHandler(ActionEvent actionEvent) throws IOException {
        appointmentToUpdate = appointmentsTable.getSelectionModel().getSelectedItem();

        if (appointmentToUpdate == null)
            return;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("/View_Controller/Views/UpdateAppointment.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void OrderByComboHandler(ActionEvent actionEvent) {
        if(OrderByCombo.getValue() =="ID"){
            appointmentsTable.setItems(AppointmentDAO.getAllAppointments());
        }else if(OrderByCombo.getValue() =="Week"){
            appointmentsTable.setItems(AppointmentDAO.getAllAppointmentsWeek());
        }else if(OrderByCombo.getValue() == "Month"){
            appointmentsTable.setItems(AppointmentDAO.getAllAppointmentsMonth());
        } else{
            appointmentsTable.setItems(AppointmentDAO.getAllAppointments());
        }
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contact_name"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        a_CustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        userID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    }

    public void toReportsHandler(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                ("/View_Controller/Views/Report.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
