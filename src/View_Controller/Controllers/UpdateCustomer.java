package View_Controller.Controllers;

import Helpers.DAO.CustomerDAO;
import Helpers.DBQuery;
import Helpers.JDBC;
import Models.Customer;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateCustomer  implements Initializable {

    public TextField nameTextField;
    public TextField addressField;
    public ComboBox divisionCombo;
    public TextField postalTextField;
    public TextField phoneTextField;
    public TextField customerId;
    public ComboBox countryCombo;

    private Alert a = new Alert(Alert.AlertType.NONE);
    private Customer updateCustomer = MainMenu.passToUpdate();

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

    /**
     * onSelection - helps in filtering division by country. The country becomes string which is used in SQL statement
     * in initializeDivision. Only item related to country will appear in division combo box.
     * @param actionEvent on selection of country.
     */
    public void onSelection(ActionEvent actionEvent) {
        String country = (String) countryCombo.getSelectionModel().getSelectedItem();
        initializeDivision(country);
    }


    public String setCountryName(int id){
        String statementGetID = "SELECT Country_ID FROM first_level_divisions d WHERE Division_ID = " + id;
        String statementGetName = "SELECT Country FROM countries WHERE Country_ID = ?";
        int countryID = 0;
        String name = null;

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statementGetID);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                countryID = rs.getInt("Country_ID");
            }


            DBQuery.setPreparedStatement(JDBC.getConnection(), statementGetName);
            PreparedStatement ps2 = DBQuery.getPreparedStatement();
            ps2.setInt(1, countryID);
            ResultSet rs2 = ps2.executeQuery();


            while (rs2.next()){
                name = rs2.getString("Country");
            }


        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return name;
    }

    public String setDivisionName(int id){
        String statementGetID = "SELECT Division FROM first_level_divisions d WHERE Division_ID = " + id;
        String name = null;

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statementGetID);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                name = rs.getString("Division");
            }


        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return name;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        customerId.setText(String.valueOf(updateCustomer.getCustomer_id()));
        nameTextField.setText(String.valueOf(updateCustomer.getCustomer_name()));
        addressField.setText(String.valueOf(updateCustomer.getAddress()));
        phoneTextField.setText(String.valueOf(updateCustomer.getPhone()));
        postalTextField.setText(String.valueOf(updateCustomer.getPostal_code()));
        countryCombo.setValue(setCountryName(updateCustomer.getDivision_id()));
        divisionCombo.setValue(setDivisionName(updateCustomer.getDivision_id()));

        initializeCountry();

        System.out.println(setCountryName(updateCustomer.getDivision_id()));


    }

    public void cancelPartHandle(ActionEvent actionEvent) throws IOException {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                    getResource("/View_Controller/Views/MainMenu.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
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

        int id =  updateCustomer.getCustomer_id();
        String name = nameTextField.getText();
        String address = addressField.getText();
        int division_id = divisionSearch((String) divisionCombo.getSelectionModel().getSelectedItem());
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
            CustomerDAO.updateCustomer(id, name, address, postal, phone, division_id);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View_Controller/Views/MainMenu.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


}
