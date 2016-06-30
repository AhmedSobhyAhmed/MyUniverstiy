/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Database.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mohamed
 */
public class ModelOfMyAdmin {
        public  static ResultSet ViewMyUniversityCourses() {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `subject` WHERE 1";
            prepared_statement = connection.prepareStatement(sql);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewMyUniversityCourses Error:" + ex.getMessage() + "");

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return result;
    }
    public int SaveProblem(String UserName,String UserEmail, String Message, String MessageDate) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase SaveProblemMethodDatabaseObject = MyDatabase.getInstance();
            connection = SaveProblemMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `problems`(`Name`, `UserEmail`, `Message`,`ProblemDate`) VALUES (?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setString(1, UserName);
            prepared_statement.setString(2, UserEmail);
            prepared_statement.setString(3, Message);
            prepared_statement.setString(4, MessageDate);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("SaveProblem Error:" + ex.getMessage());
        } finally {
            /*try {
             //  connection.close();
             //result.close();
             //prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet");
             }*/
        }
        return rowAffected;

    }

}
