package Models;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * Countries class
 * check out https://taycodes.com/
 */

public class Country {

    private int country_id_int;
    private String country;
    private Date create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;


    /**
     * Countries constructor
     *
     * @param country_id_int  unique identifier for each country. Type: Integer
     * @param country         name of the country: Type: String
     * @param create_date     date of creation: Type: Date
     * @param created_by      original person who added country to system. Type: String
     * @param last_update     last date of edit to country. Type: Timestamp
     * @param last_updated_by last person to edit country. Type: String
     */
    public Country(int country_id_int, String country, Date create_date, String created_by, Timestamp last_update,
                   String last_updated_by) {

        this.country_id_int = country_id_int;
        this.country = country;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;

    }


    /**
     * getCountry_id_int
     *
     * @return country_id_int unique identifier for each country. Type: Integer
     */

    public int getCountry_id_int() {
        return country_id_int;
    }

    /**
     * getCountry
     *
     * @return country name of the country: Type: String
     */

    public String getCountry() {
        return country;
    }

    /**
     * getCreate_date
     *
     * @return create_date date of creation: Type: Date
     */

    public Date getCreate_date() {
        return create_date;
    }

    /**
     * getCreated_by
     *
     * @return created_by original person who added country to system. Type: String
     */

    public String getCreated_by() {
        return created_by;
    }

    /**
     * getLast_updated_by
     *
     * @return last_updated_by last person to edit country. Type: String
     */

    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * getLast_update
     *
     * @return last_update last date of edit to country. Type: Timestamp
     */

    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * setLast_update
     *
     * @param last_update last_update last date of edit to country. Type: Timestamp
     */

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * setLast_updated_by
     *
     * @param last_updated_by last person to edit country. Type: String
     */

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * setCountry
     *
     * @param country name of the country: Type: String
     */

    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * setCreate_date
     *
     * @param create_date date of creation: Type: Date
     */

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * setCreated_by
     *
     * @param created_by original person who added country to system. Type: String
     */

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }


}
