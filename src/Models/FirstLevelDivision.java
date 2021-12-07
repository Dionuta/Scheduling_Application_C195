package Models;

import java.sql.Date;
import java.sql.Timestamp;

public class FirstLevelDivision {

    private int division_id;
    private String division;
    private Date create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;
    private int country_id;

    /**
     * FirstLevelDivision constructor
     *
     * @param division_id unique id. Type: Integer
     * @param division name of division. Type: String
     * @param create_date when item was added to database. Type: Date
     * @param created_by who created division. Type: String
     * @param last_update last edit. Type: Timestamp
     * @param last_updated_by who last updated. Type: String
     * @param country_id id of country. Type: Integer
     */

    public FirstLevelDivision (int division_id, String division, Date create_date, String created_by,
                                Timestamp last_update, String last_updated_by, int country_id){

        this.division_id = division_id;
        this.division = division;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.country_id = country_id;

    }


    /**
     * setCreate_date
     * @param create_date when item was added to database. Type: Date
     */

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * setLast_update
     * @param last_update last edit. Type: Timestamp
     */

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * getLast_updated_by
     * @return last_updated_by who last updated. Type: String
     */

    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * getLast_update
     * @return last_update last edit. Type: Timestamp
     */

    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * getCountry_id
     * @return country_id unique id. Type: Integer
     */

    public int getCountry_id() {
        return country_id;
    }

    /**
     * setLast_updated_by
     * @param last_updated_by last_updated_by who last updated. Type: String
     */

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * setCreated_by
     * @param created_by who created division. Type: String
     */

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * getCreated_by
     * @return created_by who created division. Type: String
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * getCreate_date
     * @return create_date when item was added to database. Type: Date
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * getDivision_id
     * @return unique id. Type: Integer
     */
    public int getDivision_id() {
        return division_id;
    }

    /**
     * setDivision
     * @param division name of division. Type: String
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * getDivision
     * @return division
     */
    public String getDivision() {
        return division;
    }

}
