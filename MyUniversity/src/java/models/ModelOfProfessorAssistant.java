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
public class ModelOfProfessorAssistant {
/*
    public ResultSet ViewMyCoursesProfessorAssistant(int student_code) {
//subject=`SubjectName`, `Code`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
//subjectstudentInstructor=`ID`, `SubjectID`, `UserID`, `semester`, `InstructorSubjectYear`, `SubjectInstructorRegisterDate`
//user=`Code`, `Mail`,
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            String sql = "select `subjectteachingstuff`.`SubjectInstructorRegisterDate`, `subject`.SubjectName,`subject`.`Code`,`subject`.`ID`, `user`.`Code`,`user`.`Mail`,`subjectteachingstuff`.UserID,`subjectteachingstuff`.`SubjectID`, `subjectteachingstuff`.`semester`,`subjectteachingstuff`.`InstructorSubjectYear`, `subjectteachingstuff`.`SubjectInstructorRegisterDate` from `subject`,`subjectteachingstuff`,`user` where `subject`.`ID`=`subjectteachingstuff`.SubjectID and `user`.`Code`=`subjectteachingstuff`.`UserID` and `user`.`Code`=?";
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
  /*      }

        return result;
    }
*/
}
