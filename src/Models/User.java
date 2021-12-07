package Models;

import java.sql.Date;
import java.sql.Timestamp;

public class User {

    private  int user_id;
    private String user_name;
    private String password;
    private Date create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;

    /**
     * User constructor
     *
     * @param user_id unique id for user. Type: Integer
     * @param user_name name of user. Type: String
     * @param password Type: String
     * @param create_date date users added to database. Type: Date
     * @param created_by who added user to database. Type: String
     * @param last_update last date of  update. Type: Date
     * @param last_updated_by who last updated. Type: String
     */

    public User(int user_id, String user_name, String password, Date create_date, String created_by,
                Timestamp last_update, String last_updated_by){

       this.user_id = user_id;
       this.user_name = user_name;
       this.password = password;
       this.create_date = create_date;
       this.created_by =created_by;
       this.last_update = last_update;
       this.last_updated_by = last_updated_by;

    }

    /**
     * getUser_id
     * @return unique id for user. Type: Integer
     */

    public int getUser_id() {
        return user_id;
    }

    /**
     * getUser_name
     * @return name of user. Type: String
     */

    public String getUser_name() {
        return user_name;
    }

    /**
     * getPassword
     * @return Type: String
     */
    public String getPassword() {
        return password;
    }

    /**
     * getCreate_date
     * @return date users added to database. Type: Date
     */

    public Date getCreate_date() {
        return create_date;
    }

    /**
     * getCreated_by
     * @return who added user to database. Type: String
     */

    public String getCreated_by() {
        return created_by;
    }

    /**
     * @param created_by who added user to database. Type: String
     */

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @param last_updated_by last_updated_by who last updated. Type: String
     */

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * getLast_update
     * @return last date of  update. Type: Date
     */

    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * getLast_updated_by
     * @return who last updated. Type: String
     */

    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * setLast_update
     * @param last_update last date of  update. Type: Date
     */

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * setCreate_date
     * @param create_date date users added to database. Type: Date
     */

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }


}
