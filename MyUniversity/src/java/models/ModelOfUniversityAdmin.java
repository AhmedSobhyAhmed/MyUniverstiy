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
public class ModelOfUniversityAdmin {

    public int AddfacultyMethod(String FacultyName,
            int UniversityID, int SystemTypeID, String AddedBy, String RegisterCourseInDate) {
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddInstructorsMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddInstructorsMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `faculty`(`FacultyName`, `UniversityID`, `SystemTypeID`, `AddedBy`,`RegisterCourseInDateFaculty`) VALUES (?,?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);//Remove it and you will have java.lang.NullPointerException =D
            prepared_statement.setString(1, FacultyName);
            prepared_statement.setInt(2, UniversityID);
            prepared_statement.setInt(3, SystemTypeID);
            prepared_statement.setString(4, AddedBy);
            prepared_statement.setString(5, RegisterCourseInDate);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);
            System.out.println("AddfacultyMethod rowAffected:" + rowAffected);

        } catch (SQLException ex) {
            System.err.println("AddfacultyMethod Error:" + ex.getMessage());
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

    public ResultSet ViewMyFacylityAdmins(int UniversityID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyFacylityAdminsDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyFacylityAdminsDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `user` WHERE `UniversityID`=? and `UserTypeID`=3 order by `UserTypeID` asc  ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UniversityID);
            // prepared_statement.setInt(2, userType);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            System.err.println("ViewMyFacylityAdmins Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyUsers)");
             }*/
        }

        return result;
    }

    public ResultSet ViewMyFacylities(int UniversityID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyFacylitiesDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyFacylitiesDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `faculty` WHERE `UniversityID`=?   ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UniversityID);
            // prepared_statement.setInt(2, userType);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            System.err.println("ViewMyFacylities Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyUsers)");
             }*/
        }

        return result;
    }

    public ResultSet ViewUniversityCourses(int UniversityID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `subject` WHERE `UniversityID`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UniversityID);
            //   prepared_statement.setInt(1, FacultyID);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewUniversityCourses Error:" + ex.getMessage() + "");

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

}
