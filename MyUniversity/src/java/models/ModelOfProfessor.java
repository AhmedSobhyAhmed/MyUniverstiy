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
import java.sql.Statement;

/**
 *
 * @author mohamed
 */
public class ModelOfProfessor {

    public ResultSet ViewMyCoursesProfessor(int professor_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyCoursesProfessorDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyCoursesProfessorDatabaseObject.connect_to_DB();
            String sql = "select * from `subject`,`subjectteachingstuff`,`user` where `subject`.`ID`=`subjectteachingstuff`.SubjectID and `user`.`Code`=`subjectteachingstuff`.`UserID` and `subjectteachingstuff`.`UserID`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, professor_code);
            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewMyCoursesProfessor Error:" + ex.getMessage());

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

    public ResultSet ViewMyStudentsInSpecialCourse(int professor_code, int SubjectCode) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyCoursesProfessorDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyCoursesProfessorDatabaseObject.connect_to_DB();
            String sql = "select * from `subjectteachingstuff`,`subjectstudent` where `subjectstudent`.`CourseOpeningFrom`=`subjectteachingstuff`.`CourseOpeningFrom` and `subjectstudent`.`CourseOpeningTo`=`subjectteachingstuff`.`CourseOpeningTo` and `subjectteachingstuff`.`SubjectID`=`subjectstudent`.`SubjectID` and `subjectteachingstuff`.`UserID`=? and `subjectteachingstuff`.`SubjectID`=?";
            // `subjectteachingstuff`.`CourseOpeningFrom`>=? and `subjectteachingstuff`.`CourseOpeningTo`<=? and 
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, professor_code);
            prepared_statement.setInt(2, SubjectCode);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewMyCoursesProfessor Eror:" + ex.getMessage());

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

    public static String ReturnCourseOpeningFrom(int professor_code, int SubjectCode) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String CourseOpeningFrom = null;
        try {
            MyDatabase ViewMyCoursesProfessorDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyCoursesProfessorDatabaseObject.connect_to_DB();
            String sql = "select distinct `subjectteachingstuff`.`CourseOpeningFrom` from `subjectteachingstuff`,`subjectstudent` where `subjectstudent`.`CourseOpeningFrom`=`subjectteachingstuff`.`CourseOpeningFrom` and `subjectstudent`.`CourseOpeningTo`=`subjectteachingstuff`.`CourseOpeningTo` and `subjectteachingstuff`.`SubjectID`=`subjectstudent`.`SubjectID` and `subjectteachingstuff`.`UserID`=? and `subjectteachingstuff`.`SubjectID`=?";
            // `subjectteachingstuff`.`CourseOpeningFrom`>=? and `subjectteachingstuff`.`CourseOpeningTo`<=? and 
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, professor_code);
            prepared_statement.setInt(2, SubjectCode);

            result = prepared_statement.executeQuery();
            while (result.next()) {
                CourseOpeningFrom = result.getString("subjectteachingstuff.CourseOpeningFrom");
                System.out.println("CourseOpeningFrom" + CourseOpeningFrom);

            }
          
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("Return `CourseOpeningFrom` Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return CourseOpeningFrom;
    }

    public static String ReturnCourseOpeningTo(int professor_code, int SubjectCode) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String CourseOpeningTo = null;
        try {
            MyDatabase ViewMyCoursesProfessorDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyCoursesProfessorDatabaseObject.connect_to_DB();
            String sql = "select distinct `subjectteachingstuff`.`CourseOpeningTo` from `subjectteachingstuff`,`subjectstudent` where `subjectstudent`.`CourseOpeningFrom`=`subjectteachingstuff`.`CourseOpeningFrom` and `subjectstudent`.`CourseOpeningTo`=`subjectteachingstuff`.`CourseOpeningTo` and `subjectteachingstuff`.`SubjectID`=`subjectstudent`.`SubjectID` and `subjectteachingstuff`.`UserID`=? and `subjectteachingstuff`.`SubjectID`=?";
            // `subjectteachingstuff`.`CourseOpeningFrom`>=? and `subjectteachingstuff`.`CourseOpeningTo`<=? and 
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, professor_code);
            prepared_statement.setInt(2, SubjectCode);

            result = prepared_statement.executeQuery();
            while (result.next()) {
                CourseOpeningTo = result.getString("subjectteachingstuff.CourseOpeningTo");
                System.out.println("CourseOpeningTo" + CourseOpeningTo);

            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("Return `CourseOpeningTo` Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return CourseOpeningTo;
    }

    public int addgrade(int WritenGrade, int MidtermGrade, int FinalExamGrade, int FinalGrade, int Subject_Code, int studentid) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        boolean update = true;
        int rowAffected = -1;
        String sql = "UPDATE `subjectstudent` SET `WritenGrade`=?,`MidtermGrade`=?,`FinalExamGrade`=?,`FinalGrade`=? WHERE `SubjectID`=? and `StudentID`=?";
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, WritenGrade);
            prepared_statement.setInt(2, MidtermGrade);
            prepared_statement.setInt(3, FinalExamGrade);
            prepared_statement.setInt(4, FinalGrade);
            prepared_statement.setInt(5, Subject_Code);
            prepared_statement.setInt(6, studentid);

            rowAffected = prepared_statement.executeUpdate();
            // String sql = "select `subjectteachingstuff`.`SubjectInstructorRegisterDate`, `subject`.SubjectName,`subject`.`Code`,`subject`.`ID`, `user`.`Code`,`user`.`Mail`,`subjectteachingstuff`.UserID,`subjectteachingstuff`.`SubjectID`, `subjectteachingstuff`.`semester`,`subjectteachingstuff`.`InstructorSubjectYear`, `subjectteachingstuff`.`SubjectInstructorRegisterDate` from `subject`,`subjectteachingstuff`,`user` where `subject`.`ID`=`subjectteachingstuff`.SubjectID and `user`.`Code`=`subjectteachingstuff`.`UserID` and `user`.`Code`=?";

        } catch (SQLException ex) {
            System.err.println("Update Student Grades Error:" + ex.getMessage());
            // System.out.println("SQLException:Can't Login");

        }
        return rowAffected;

    }

}
