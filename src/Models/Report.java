package Models;

public class Report {

    private String month;
    private String type;

    private int total;

    /**
     * @param month name of month. Type: String
     * @param type name of type. Type: String
     * @param total total number of appointments. Type: int
     */

    public Report(String month, String type, int total){

        this.month = month;
        this.type = type;

        this.total = total;

    }

    /**
     * @param type name of type. Type: String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return type- name of type. Type: String
     */
    public String getType() {
        return type;
    }

    /**
     * @param total total number of appointments. Type: int
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return Total -total number of appointments. Type: int
     */
    public int getTotal() {
        return total;
    }


    /**
     * @param month -name of month. Type: String
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return name of month. Type: String
     */
    public String getMonth() {
        return month;
    }
}
