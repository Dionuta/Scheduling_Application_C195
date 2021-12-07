package Helpers.DAO;

import Helpers.DBQuery;
import Helpers.JDBC;
import Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerDAO {

    /**
     * Return all customers from database
     * @return ObservableList
     */
    public static ObservableList<Customer> getAllCustomers(){
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String statement = "SELECT c.*, d.Division FROM customers c INNER JOIN  first_level_divisions d ON c.Division_ID " +
                "= d.Division_ID Order By c.Customer_ID";

        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String  lastBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                Customer customer = new Customer(id, name,address, postalCode, phone,createDate,createBy,lastUpdate,
                        lastBy,divisionID, divisionName);
                allCustomers.add(customer);

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return allCustomers;
    }

    public static void addCustomer(String customerName, String address, String postal_code, String phone, int division){
        String statement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By," +
                "Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, NOW(), 'Tay', NOW(), 'Tay', ?)";
        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3,postal_code);
            ps.setString(4, phone);
            ps.setInt(5, division);

            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }



}
    public static void deleteCustomer(int id){

        String appDel = "DELETE From appointments WHERE Customer_ID = ?";

        String cusDel = "DELETE From customers WHERE Customer_ID = ?";

        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), appDel);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setInt(1, id);
            ps.execute();

            DBQuery.setPreparedStatement(JDBC.getConnection(), cusDel);
            PreparedStatement ps2 = DBQuery.getPreparedStatement();

            ps2.setInt(1, id);
            ps2.execute();


        }catch(SQLException e){

            System.out.println(e.getMessage());

        }

    }


    public static void updateCustomer(int id, String customerName, String address, String postal_code, String phone,
                                      int division){
        String statement ="UPDATE customers SET Customer_Name = ?, Address =?, Postal_Code =?, Phone =?," +
                "Division_ID = ? ,Last_Update = NOW() WHERE Customer_ID = ?";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();


            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3,postal_code);
            ps.setString(4, phone);
            ps.setInt(5, division);
            ps.setInt(6, id);

            ps.executeUpdate();
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Return ObservableList of customer by name of division
     * @param division Name of division
     * @return ObservableList
     */
    public static ObservableList<Customer> getAllCustomersByDivision(String division){
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String statement = "SELECT c.*, d.Division FROM customers c INNER JOIN  first_level_divisions d ON c.Division_ID " +
                "= d.Division_ID WHERE d.Division = ? Order By c.Customer_ID";

        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1,division);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String  lastBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                Customer customer = new Customer(id, name,address, postalCode, phone,createDate,createBy,lastUpdate,
                        lastBy,divisionID, divisionName);
                allCustomers.add(customer);

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return allCustomers;
    }

}
