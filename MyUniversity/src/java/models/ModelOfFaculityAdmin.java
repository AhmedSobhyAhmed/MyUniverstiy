/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Database.MyDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
/*import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

/**
 *
 * @author mohamed
 */
public class ModelOfFaculityAdmin {

    //Add Course
    public int AddCourseMethod(String CourseName,
            String CourseCode, String CourseDescription,
            int WritenGrade, int MidtermGrade,
            int FinalExamGrade, int FaculityID,
            int UniversityID, String UserMail, String RegisterCourseInDate) throws SQLException {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddCourseMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddCourseMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `subject`(`SubjectName`, `Code`, `WritenGrade`, `MidtermGrade`, `FinalExamGrade`, `FaculityID`, `UniversityID`,`CourseDescription`,`AddedBy`,`RegisterCourseInDate`) VALUES  (?,?,?,?,?,?,?,?,?,?)";

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setString(1, CourseName);
            prepared_statement.setString(2, CourseCode);
            prepared_statement.setInt(3, WritenGrade);
            prepared_statement.setInt(4, MidtermGrade);
            prepared_statement.setInt(5, FinalExamGrade);
            prepared_statement.setInt(6, FaculityID);
            prepared_statement.setInt(7, UniversityID);
            prepared_statement.setString(8, CourseDescription);
            prepared_statement.setString(9, UserMail);
            prepared_statement.setString(10, RegisterCourseInDate);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);
        } catch (SQLException ex) {
            System.err.println("AddCourseMethod Error:" + ex.getMessage());
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
    //End of Add Course Method

    // ViewFaculityCourses
    public ResultSet ViewFaculityCourses(int FaculityID, int UniversityID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();

            String sql = "SELECT * FROM `subject` WHERE `UniversityID`=? and `FaculityID`=? ";
            //and `OpenFromDate`<=? and `OpenToDate`>= ? 
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UniversityID);
            prepared_statement.setInt(2, FaculityID);

            //  prepared_statement.setString(3, currentDateString);
            //prepared_statement.setString(4, currentDateString);
            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewFaculityCourses Error:" + ex.getMessage());

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
    //End of ViewFaculityCourses Method

    // ViewSpecialFaculityCourse
    public String[] ViewSpecialFaculityCourse(int SubjectCode, int FaculityID, int UniversityID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String[] subjectObject = new String[3];
        try {
            MyDatabase ViewSpecialFaculityCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewSpecialFaculityCourseDatabaseObject.connect_to_DB();
            /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String currentDateString = sdf.format(new Date());*/
            String sql = "select * from `subject` where `ID`=?  ";
            //and `OpenFromDate`<=? and `OpenToDate`>= ? 
            prepared_statement = connection.prepareStatement(sql);

            prepared_statement.setInt(1, SubjectCode);

            /* prepared_statement.setInt(2, FaculityID);
             prepared_statement.setInt(3, UniversityID);*/
            //  prepared_statement.setString(3, currentDateString);
            //prepared_statement.setString(4, currentDateString);
            result = prepared_statement.executeQuery();
            if (result.isBeforeFirst()) {
                System.err.println("isBeforeFirst");

            }
            if (!result.isBeforeFirst()) {
                System.err.println("!isBeforeFirst");

            }
            while (result.next()) {
                subjectObject[0] = result.getString("OpenFromDate");
                subjectObject[1] = result.getString("OpenToDate");
                subjectObject[2] = result.getString("Term");
                System.out.println("OpeningFromDate:" + subjectObject[0] + "<br/>");
                System.out.println("OpeningToDate:" + subjectObject[1] + "<br/>");

                System.out.println("Term:" + subjectObject[2] + "<br/>");

            }

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            System.err.println("ViewSpecialFaculityCourse Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return subjectObject;
    }
    //End of ViewSpecialFaculityCourse Method

    // DeleteCourseMethods
    public int DeleteCourseMethod(int CourseID, String AdminMail) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase DeleteCommentMethodDatabaseObject = MyDatabase.getInstance();
            connection = DeleteCommentMethodDatabaseObject.connect_to_DB();

            String sql = "Delete from `subject` where `ID`=? and `AddedBy`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, CourseID);
            prepared_statement.setString(2, AdminMail);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Delete Course Method Error:" + ex.getMessage());
        }

        return rowAffected;
    }
    //End of Delete Course Method

    // UpdateOpenCourseMethod
    public int UpdateOpenCourseMethod(int CourseID, String Term,
            int LevelID, int DepartmentID, String OpenBy,
            String DateFrom, String DateTo,
            String OpenCourseInDate, String OpenFromDate, String OpenToDate
    ) /* String DayFrom, String MonthFrom,
     String DayTo, String MonthTo,
     String OpenForYear,*/ {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase DeleteCommentMethodDatabaseObject = MyDatabase.getInstance();
            connection = DeleteCommentMethodDatabaseObject.connect_to_DB();

            String sql = "UPDATE `subject` SET `Term`=? ,`LevelID`=? ,`DepartmentID`=? ,`OpenBy`=? ,`DateFrom`=?,`DateTo`=?,`OpenCourseInDate`=?,`OpenFromDate`=?,`OpenToDate`=? where `ID`=?";
            prepared_statement = connection.prepareStatement(sql);
            /* prepared_statement.setString(1, DayFrom);
             prepared_statement.setString(2, MonthFrom);
             prepared_statement.setString(3, DayTo);
             prepared_statement.setString(4, MonthTo);
             prepared_statement.setString(5, OpenForYear);*/
            prepared_statement.setString(1, Term);
            prepared_statement.setInt(2, LevelID);
            prepared_statement.setInt(3, DepartmentID);
            prepared_statement.setString(4, OpenBy);
            prepared_statement.setString(5, DateFrom);
            prepared_statement.setString(6, DateTo);
            prepared_statement.setString(7, OpenCourseInDate);
            prepared_statement.setString(8, OpenFromDate);
            prepared_statement.setString(9, OpenToDate);
            prepared_statement.setInt(10, CourseID);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Update Open Course Method Error:" + ex.getMessage());
        }
        return rowAffected;
    }
    //End of Update Open Course Method

    public ResultSet ViewMyUsers(int FacultyID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyUsersDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyUsersDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `user` WHERE `FacultyID`=? and `UserTypeID` <>2  and `UserTypeID`<>3  and `UserTypeID` <>1  order by `UserTypeID` asc  ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, FacultyID);
            // prepared_statement.setInt(2, userType);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(ex.getMessage() + "ViewMyUsers");

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

    public ResultSet ViewMyUsersByUserType(int FacultyID, int userTypeID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyUsersByUserTypeDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyUsersByUserTypeDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `user` WHERE `FacultyID`=? and `UserTypeID`=?  ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, FacultyID);
            prepared_statement.setInt(2, userTypeID);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("" + ex.getMessage() + " in ViewMyUsersByUserType");

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

//Add Instructors
    public int AddInstructorsMethod(
            int SubjectCode, int UserID,
            String Semester,
            String SubjectInstructorRegisterDate, String CourseOpeningFrom,
            String CourseOpeningTo, String AddedBy) {
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddInstructorsMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddInstructorsMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `subjectteachingstuff`(`SubjectID`, `UserID`, `semester`, `SubjectInstructorRegisterDate`, `CourseOpeningFrom`, `CourseOpeningTo`, `AddedBy`) VALUES  (?,?,?,?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);//Remove it and you will have java.lang.NullPointerException =D
            prepared_statement.setInt(1, SubjectCode);
            prepared_statement.setInt(2, UserID);
            prepared_statement.setString(3, Semester);
            prepared_statement.setString(4, SubjectInstructorRegisterDate);
            prepared_statement.setString(5, CourseOpeningFrom);
            prepared_statement.setString(6, CourseOpeningTo);
            prepared_statement.setString(7, AddedBy);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);
            System.out.println("AddInstructorsMethod rowAffected:" + rowAffected);

        } catch (SQLException ex) {
            System.err.println("AddInstructorsMethod Error:" + ex.getMessage());
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
    //End of Add Course Method

    public ArrayList<MyPerson> ViewMyTeachingStuffForSpecialCourse(String CourseOpeningFrom, String CourseOpeningTo, int SubjectCode, int UserTypeID) {
        MyPerson my_person = null;

        ArrayList<MyPerson> my_personsArrayList = new ArrayList<MyPerson>();
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyTeachingStuffForSpecialCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyTeachingStuffForSpecialCourseDatabaseObject.connect_to_DB();
            String sql = "select distinct `user`.`Code`,`FName`,`LName`,`UserID`,`subject`.`ID`,`Mail`,`SubjectID`,`subjectteachingstuff`.`CourseOpeningFrom`,`subjectteachingstuff`.`CourseOpeningTo`,`semester`,`SubjectName` from `subject`,`user`,`subjectteachingstuff` where `user`.`Code`=`subjectteachingstuff`.`UserID` and `subject`.`ID`=`subjectteachingstuff`.`SubjectID` and `SubjectID`=? and `subjectteachingstuff`.`CourseOpeningFrom`=? and `subjectteachingstuff`.`CourseOpeningTo`=? and `user`.`UserTypeID`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, SubjectCode);
            prepared_statement.setString(2, CourseOpeningFrom);
            prepared_statement.setString(3, CourseOpeningTo);
            prepared_statement.setInt(4, UserTypeID);

            result = prepared_statement.executeQuery();
            System.out.println("SubjectCode:" + SubjectCode);
            System.out.println("CourseOpeningFrom:" + CourseOpeningFrom);
            System.out.println("CourseOpeningTo:" + CourseOpeningTo);
            System.out.println("UserTypeID:" + UserTypeID);
            while (result.next()) {
                my_person = new MyPerson();
                my_person.setFName(result.getString("FName"));
                my_person.setCode(result.getInt("user.Code"));
                my_person.setUserEmail(result.getString("Mail"));
                my_person.setLName(result.getString("LName"));
                my_personsArrayList.add(my_person);
                System.out.println("FName:" + result.getString("FName"));
                System.out.println("Mail:" + result.getString("Mail"));

            }

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("" + ex.getMessage() + "ViewMyTeachingStuffForSpecialCourse");

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyUsers)");
             }*/
        }

        return my_personsArrayList;
    }

// ViewRatings
    public ArrayList<String[]> ViewRatings(int FaculityID, int InstructorID) {
        ArrayList<String[]> my_ratingsArrayList = new ArrayList<String[]>();

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewRatingsDatabaseObject = MyDatabase.getInstance();
            connection = ViewRatingsDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `rating` WHERE `FacultyID`=? and `InstructorID`=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, FaculityID);
            prepared_statement.setInt(2, InstructorID);
            result = prepared_statement.executeQuery();
            if (!result.isBeforeFirst()) {
                System.out.println("!result.isBeforeFirst()");
            } else {
                while (result.next()) {
                    String[] ratings = new String[5];

                    ratings[0] = String.valueOf(result.getInt("ID"));
                    ratings[1] = MyPerson.ReturnMyUniversityCodeString(result.getInt("StudentID"));
                    ratings[2] = String.valueOf(result.getInt("RatingGrade"));
                    ratings[3] = result.getString("Positives");
                    ratings[4] = result.getString("Negatives");
                    my_ratingsArrayList.add(ratings);
                    System.out.println(result.getInt("ID"));
                    System.out.println(result.getInt("InstructorID"));
                    System.out.println(result.getString("Positives"));
                    System.out.println(result.getString("Negatives"));
                }

            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewRatings Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return my_ratingsArrayList;
    }
    //End of ViewRatings Method

    public ResultSet ViewMyUsersByUserTypeRatings(int FacultyID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewMyUsersByUserTypeRatingsDatabaseObject = MyDatabase.getInstance();
            connection = ViewMyUsersByUserTypeRatingsDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `user` WHERE `FacultyID`=? and `UserTypeID`=5 or `UserTypeID`=4 or `UserTypeID`=3  ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, FacultyID);
            // prepared_statement.setInt(2, userTypeID);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("" + ex.getMessage() + " in ViewMyUsersByUserTypeRatings");

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

    public ResultSet ViewFacultyCourses(int UniversityID, int FacultyID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `subject` WHERE `UniversityID`=? and `FaculityID`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UniversityID);
            prepared_statement.setInt(1, FacultyID);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ViewFacultyCourses Error:" + ex.getMessage() + "");

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
