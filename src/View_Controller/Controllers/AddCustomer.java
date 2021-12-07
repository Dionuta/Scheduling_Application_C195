package View_Controller.Controllers;

import Helpers.DAO.CustomerDAO;
import Helpers.DBQuery;
import Helpers.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
    public ComboBox <String> countryCombo;
    public ComboBox <String> divisionCombo;
    public TextField nameTextField;
    public TextField addressField;
    public TextField postalTextField;
    public TextField phoneTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Alert a = new Alert(Alert.AlertType.NONE);


    public void initializeCountry(){
        String statement = "SELECT * FROM countries";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                countryCombo.getItems().add(rs.getString(2));
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initializeDivision( String country){

        String statement = "SELECT * FROM first_level_divisions d, " +
                "countries c where d.Country_ID = c.Country_ID AND c.Country = ?";

        try {

            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,country);

            ResultSet rs = ps.executeQuery();
            divisionCombo.setVisibleRowCount(5);
            divisionCombo.getItems().clear();

            while (rs.next()){
                divisionCombo.getItems().add(rs.getString(2));
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCountry();


    }

    /**
     * Returns user to main screen uses alert lambda to take in a user conformation responses
     * @param actionEvent click event
     * @throws IOException throw io
     */
    public void cancelPartHandle(ActionEvent actionEvent) throws IOException {
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
     * onSelection - helps in filtering division by country. The country becomes string which is used in SQL statement
     * in initializeDivision. Only item related to country will appear in division combo box.
     * @param actionEvent on selection of country.
     */
    public void onSelection(ActionEvent actionEvent) {
        String country = countryCombo.getSelectionModel().getSelectedItem();
        initializeDivision(country);
    }

    public static int divisionSearch(String division){
        int id = 0;
        String statement = "SELECT Division_ID FROM first_level_divisions WHERE Division = ? ";
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, division);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                id = rs.getInt("Division_ID");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void savaButtonHandler(ActionEvent actionEvent) throws IOException {

        String name = nameTextField.getText();
        String address = addressField.getText();
        int division_id = divisionSearch(divisionCombo.getSelectionModel().getSelectedItem());
        String phone = phoneTextField.getText();
        String postal = postalTextField.getText();

        if(division_id < 1){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select valid division!");
            a.showAndWait();
        }else if (name.trim().isEmpty()|| address.trim().isEmpty() || phone.trim().isEmpty() || postal.trim().isEmpty())
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please all fields must have a value");
            a.showAndWait();
        }
        else {
            CustomerDAO.addCustomer(name, address, postal, phone, division_id);

            root = FXMLLoader.load(getClass().getResource("/View_Controller/Views/MainMenu.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
