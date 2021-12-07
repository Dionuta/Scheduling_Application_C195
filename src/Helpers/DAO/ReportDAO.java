package Helpers.DAO;

import Helpers.DBQuery;
import Helpers.JDBC;
import Models.Customer;
import Models.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportDAO {

    public static ObservableList<Report> getAllReports(){
        ObservableList<Report> allReports = FXCollections.observableArrayList();
        String statement = "SELECT monthname(Start) AS Month, Type, Count(*) AS Total FROM " +
                "appointments group by 1,2 order by 3;";

        try{
            DBQuery.setPreparedStatement(JDBC.getConnection(), statement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String month = rs.getString("Month");
                String type = rs.getString("Type");
                int total = rs.getInt("Total");

                Report report = new Report(month, type, total);
                allReports.add(report);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return allReports;
    }



}
