package Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;




public class DBQuery {

    private static PreparedStatement statement; //statement reference

    //Create Statement Object

    public static void setPreparedStatement(Connection conn,String sqlStatement) throws SQLException {
        if(conn == null){
            System.out.println("You are not connected to Database! Connect to database and try again.");
        }else {
            statement = conn.prepareStatement(sqlStatement);
        }
    }

    //Return Statement Object

    public static PreparedStatement getPreparedStatement(){
        return statement;
    }



}
