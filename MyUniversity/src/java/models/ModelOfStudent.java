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
public class ModelOfStudent extends MyPerson {

    public ResultSet ViewMyCourses(int student_code) {
//subject=`SubjectName`, `Code`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
//subjectstudent=`SubjectID`, `StudentID`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
//user=`Code`, `Mail`,
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            String sql = "select * from subject,subjectstudent,user where subject.ID=subjectstudent.SubjectID and user.Code=subjectstudent.StudentID and subjectstudent.StudentID=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(ex.getMessage() + "View Courses");

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

    // ViewRegisterCourses
    public ResultSet ViewRegisterCourses(int FaculityID, int UniversityID, int LevelID, int DepartmentID, int StudentCode) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewRegisterCoursesDatabaseObject = MyDatabase.getInstance();
            connection = ViewRegisterCoursesDatabaseObject.connect_to_DB();
            String sql = "select * from `subject` where`FaculityID`=? and `UniversityID`=? and `LevelID`=? and `DepartmentID`=? and `ID` Not In (select subjectstudent.SubjectID from subject,subjectstudent,user where subject.ID=subjectstudent.SubjectID and user.Code=subjectstudent.StudentID and subjectstudent.StudentID=? ) ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, FaculityID);
            prepared_statement.setInt(2, UniversityID);
            prepared_statement.setInt(3, LevelID);
            prepared_statement.setInt(4, DepartmentID);
            prepared_statement.setInt(5, StudentCode);
            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewRegisterCourses Error:" + ex.getMessage());

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
    //End of ViewRegisterCourses Method

    //StudentRegisterCourseMethod
    public int StudentRegisterCourseMethod(int SubjectID, int UserID,
            String StudentRegisterSubjectDate, String Term,
            String CourseOpeningFrom, String CourseOpeningTo) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase DeleteUserMethodDatabaseObject = MyDatabase.getInstance();
            connection = DeleteUserMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `subjectstudent`(`SubjectID`, `StudentID` ,`StudentRegisterSubjectDate`,`Term`,`CourseOpeningFrom`,`CourseOpeningTo`) VALUES (?,?,?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, SubjectID);
            prepared_statement.setInt(2, UserID);
            prepared_statement.setString(3, StudentRegisterSubjectDate);
            prepared_statement.setString(4, Term);
            prepared_statement.setString(5, CourseOpeningFrom);
            prepared_statement.setString(6, CourseOpeningTo);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Student Register Course Method Error:" + ex.getMessage());
        }
        return rowAffected;
    }

    //End of StudentRegisterCourse Method
    public ResultSet ViewStudentInstructors(int FacultyID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewStudentInstructorsDatabaseObject = MyDatabase.getInstance();
            connection = ViewStudentInstructorsDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `user` where `FacultyID`=?  and `UserTypeID`=5 or  `UserTypeID`=4  ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, FacultyID);
            // prepared_statement.setInt(2, userType);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("" + ex.getMessage() + ":ViewStudentInstructors");

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

    public int rateInstructor(int instructorCode, int studentCode, int rateGrade, String positives, String negatives, String RatingDate,int facultyID) {

        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddInstructorsMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddInstructorsMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `rating`(`StudentID`, `InstructorID`, `Positives`, `Negatives`, `RatingGrade`, `RatingDate`,`FacultyID`) VALUES (?,?,?,?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);//Remove it and you will have java.lang.NullPointerException =D
            prepared_statement.setInt(1, studentCode);
            prepared_statement.setInt(2, instructorCode);
            prepared_statement.setInt(5, rateGrade);
            prepared_statement.setString(3, positives);
            prepared_statement.setString(4, negatives);
            prepared_statement.setString(6, RatingDate);
            prepared_statement.setInt(7, facultyID);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);
            System.out.println("rateInstructorMethod rowAffected:" + rowAffected);

        } catch (SQLException ex) {
            System.err.println("rateInstructorMethod Error:" + ex.getMessage());
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
