package Models;

import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Appointment {

    private int appointment_id;
    private String title;
    private String description;
    private String location;
    private String contact_name;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime created_date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_updated_by;
    private int customer_id;
    private int user_id;


    /**
     * Appointment constructor
     *
     * @param appointment_id unique identifier for each appointment. Type: Integer
     * @param title name of appointment. Type: String
     * @param location where there the appointment will be held. Type: String
     * @param contact_name name of contact associated with appointment. Type: String
     * @param description info on appointment. Type: String
     * @param type type of appointment. Type: String 
     * @param start start of appointment. Type: TimeStamp
     * @param end end of appointment. Type:TimeStamp
     * @param created_date day of creation. Type: LocalDateTime
     * @param created_by person who created appointment. Type: String
     * @param last_update last date of edit. Type: LocalDateTime
     * @param last_updated_by who last updated the appointment. Type: String
     * @param customer_id id of customer. Type: Integer
     * @param user_id id of user.Type: Integer
     *
     */

    public Appointment(int appointment_id, String title, String description, String location, String contact_name,
                        String type, LocalDateTime start, LocalDateTime end, LocalDateTime created_date, String created_by,
                       LocalDateTime last_update, String last_updated_by, int customer_id, int user_id){

        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact_name = contact_name;
        this.type = type;
        this.start = start;
        this.end = end;
        this.created_by = created_by;
        this.created_date = created_date;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.customer_id = customer_id;
        this.user_id = user_id;

    }

    /**
     * getLast_updated_by
     * @return last_updated_by who last updated the appointment. Type: String
     */

    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * getCustomer_id
     * @return id of customer. Type: Integer
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * getCreated_by
     * @return person who created appointment. Type: String
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * getLast_update
     * @return last date of edit. Type: Date
     */
    public LocalDateTime getLast_update() {
        return last_update;
    }

    /**
     * getCreated_date
     * @return day of creation. Type: Date
     */
    public LocalDateTime getCreated_date() {
        return created_date;
    }

    /**
     * getEnd
     * @return end of appointment. Type: Date
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * getAppointment_id
     * @return unique identifier for each appointment. Type: Integer
     */
    public int getAppointment_id() {
        return appointment_id;
    }



    /**
     * getDescription
     * @return info on appointment. Type: String
     */
    public String getDescription() {
        return description;
    }

    /**
     * getLocation
     * @return where there the appointment will be held. Type: String
     */
    public String getLocation() {
        return location;
    }

    /**
     * getStart
     * @return start of appointment. Type: Date
     */
    public LocalDateTime getStart() {

        return start;
    }

    /**
     * getTitle
     * @return name of appointment. Type: String
     */
    public String getTitle() {
        return title;
    }

    /**
     * getUser_id
     * @return id of user.Type: Integer
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * getType
     * @return type of appointment. Type: String
     */

    public String getType() {
        return type;
    }

    public String getContact_name() {
        return contact_name;
    }

    //Setters:
    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }


    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}
