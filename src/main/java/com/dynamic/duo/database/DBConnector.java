package com.dynamic.duo.database;
import javax.sql.rowset.JdbcRowSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class DBConnector {

    Connection conn;
    public DBConnector() {
        File db = new File("src/main/resources/DB_configuration.properties");
        //get db information from properties file
        String user = "";
        String pass = "";
        try {
            Scanner fileS = new Scanner(db);
            Scanner lineS = new Scanner(fileS.nextLine());
            lineS.next();
            user = lineS.next();
            lineS.close();
            lineS = new Scanner(fileS.nextLine());
            lineS.next();
            pass = lineS.next();
            lineS.close();
            fileS.close();
        }catch(FileNotFoundException ex){
            // handle any errors
            System.out.println("FileNotFoundException: " + ex.getMessage());
        }
        //connect to the db
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?", user, pass);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void selectFirstEntry(String table, List<String> params, String whereClause){
        try{
            String sql = "Select ";
            for(String param: params){
                sql += param + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += "from " + table + " where " + whereClause + " LIMIT 1";
            //make sure input has been properly sanitized
            if(!checkStatement(sql))
                throw new IllegalArgumentException("Where clause cannot contain semi-colon");
            sql += ";";
            Statement s = conn.createStatement();
            s.execute(sql);
            ResultSet rs = s.getResultSet();

        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     * close connection
     * @throws SQLException
     */
    public void close() throws SQLException{
        conn.close();
    }

    /**
     * make sure statement has been properly sanitized before executing
     * @param where - clause to be checked
     * @return true if it doesn't contain a semi-colon
     */
    private boolean checkStatement(String where){
        for(char c: where.toCharArray()){
            if(c == ';')
                return false;
        }
        return true;
    }
}
