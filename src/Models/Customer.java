package Models;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 *  Customers class
 *  check out https://taycodes.com/
 */

public class Customer {

    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private LocalDateTime create_date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_updated_by;
    private int division_id;
    private String  divisionName;

    /**
     * Customers constructor
     *
     * @param customer_id unique identifier for each customer. Type: Integer
     * @param customer_name name of customer. Type: String
     * @param address where customer lives. Type: String
     * @param postal_code zipcode of customer. Type: String
     * @param phone number to call customer. Type: String
     * @param create_date date of creation: Type: Date
     * @param created_by original person who added customer to system. Type: String
     * @param last_update last date of edit to customer. Type: Timestamp
     * @param last_updated_by last person to edit customer. Type: String
     * @param division_id division of user. Type: Integer.
     * @param divisionName name of division. Type: String
     */


    public Customer(int customer_id, String customer_name, String address, String postal_code, String phone,
                    LocalDateTime create_date, String created_by, LocalDateTime last_update, String last_updated_by,
                    int division_id, String divisionName){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = division_id;
        this.divisionName =  divisionName;
    }




    /**
     * setCreated_by
     * @param created_by original person who added customer to system. Type: String
     */

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * setCreate_date
     * @param create_date date of creation: Type: Date
     */

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    /**
     * setLast_updated_by
     * @param last_updated_by last person to edit customer. Type: String
     */

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * setLast_update
     * @param last_update last date of edit to customer. Type: Timestamp
     */

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    /**
     *  setAddress
     * @param address where customer lives. Type: String
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * setCustomer_name
     * @param customer_name name of customer. Type: String
     */

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * setPhone
     * @param phone number to call customer. Type: String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * setPostal_code
     * @param postal_code zipcode of customer. Type: String
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * getLast_update
     * @return last date of edit to customer. Type: Timestamp
     */
    public LocalDateTime getLast_update() {
        return last_update;
    }

    /**
     * getCreated_by
     * @return created_by original person who added customer to system. Type: String
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * getCreate_date
     * @return create_date date of creation: Type: Date
     */

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    /**
     * getAddress
     * @return address name of customer. Type: String
     */
    public String getAddress() {
        return address;
    }

    /**
     * getCustomer_id
     * @return customer_id unique identifier for each customer. Type: Integer
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * getCustomer_name
     * @return customer_name name of customer. Type: String
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * getLast_updated_by
     * @return last_updated_by last person to edit customer. Type: String
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * getPhone
     * @return phone number to call customer. Type: String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * getPostal_code
     * @return postal_code zipcode of customer. Type: String
     */

    public String getPostal_code() {
        return postal_code;
    }

    public int getDivision_id() {
        return division_id;
    }

    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}
