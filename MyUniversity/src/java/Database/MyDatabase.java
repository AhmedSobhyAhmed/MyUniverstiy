/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mohamed
 */
public class MyDatabase {

    private Connection connection;
    private PreparedStatement prepared_statement;
    private Statement y;
    private ResultSet result;

    private static MyDatabase myDatabaseObject;
    public static int count = 0;

    public static synchronized MyDatabase getInstance() {

        if (myDatabaseObject == null) {
            myDatabaseObject = new MyDatabase();
        }

        return myDatabaseObject;

    }

    private MyDatabase() {
        // if (connection == null) {
        try {
            String classForName = "com.mysql.jdbc.Driver";

            //Connect to Driver
            Class.forName(classForName);//org.apache.derby.jdbc.ClientDriver"
            System.out.println("Connection Driver true");

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
            //  JOptionPane.showMessageDialog(null, "ClassNotFoundException");
            System.err.println(ex.getMessage());

        }
        try {
            //Connect to DB
            String URL = "jdbc:mysql://localhost/my_university";
            String USER = "root";
            String PASS = "1234";

            connection = DriverManager.getConnection(URL, USER, PASS);
            
            //   JOptionPane.showMessageDialog(null, "true connection");
            System.out.println("Connection DB True");
        } catch (SQLException ex) {
            System.out.println("SQLException:Connection Failuer" + ex.getMessage());
            System.err.println(ex.getMessage());

            //JOptionPane.showMessageDialog(null, "SQLException:Connection Failuer");
        }
        // }//end of if connection =null
        this.count++;
    }

    public Connection connect_to_DB() {

        return connection;
    }//end of function connect_to_DB
}//end of class

