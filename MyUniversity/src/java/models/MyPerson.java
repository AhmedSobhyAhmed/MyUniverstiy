/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Database.MyDatabase;
import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author mohamed
 */
public class MyPerson {

    protected String FName, LName, MName, UserEmail, Pass, Address, gender, date, BirthDate, MyUniversityCodeString;
    private String msg = "";
    protected int Code, UserType, levelID, SSN, faculityID, universityID, DepartmentID;

    public String getMyUniversityCodeString() {
        return MyUniversityCodeString;
    }

    public void setMyUniversityCodeString(String MyUniversityCodeString) {
        this.MyUniversityCodeString = MyUniversityCodeString;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getMName() {
        return MName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int UserType) {
        this.UserType = UserType;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public int getFaculityID() {
        return faculityID;
    }

    public void setFaculityID(int faculityID) {
        this.faculityID = faculityID;
    }

    public int getUniversityID() {
        return universityID;
    }

    public void setUniversityID(int universityID) {
        this.universityID = universityID;
    }

    public static String ReturnFaculityName(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String FacultyName = "";
        try {
            MyDatabase ReturnFaculityNameDatabaseObject = MyDatabase.getInstance();
            connection = ReturnFaculityNameDatabaseObject.connect_to_DB();
            String sql = "select FacultyName from user,faculty where user.FacultyID=faculty.ID and user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                FacultyName = result.getString("FacultyName");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnFacultyName Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return FacultyName;
    }

    public static String ReturnFaculityNameFromCourses(int facultyID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String FacultyName = "";
        try {
            MyDatabase ReturnFaculityNameDatabaseObject = MyDatabase.getInstance();
            connection = ReturnFaculityNameDatabaseObject.connect_to_DB();
            String sql = "select FacultyName from faculty where faculty.ID=?  ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, facultyID);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                FacultyName = result.getString("FacultyName");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnFacultyName Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return FacultyName;
    }

    public static String ReturnUniversityName(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String UniversityName = "";
        try {
            MyDatabase ReturnUniversityNameDatabaseObject = MyDatabase.getInstance();
            connection = ReturnUniversityNameDatabaseObject.connect_to_DB();
            String sql = "select UniversityName from user,university where user.UniversityID=university.ID and user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                UniversityName = result.getString("UniversityName");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnUniversityName Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return UniversityName;
    }

    public static String ReturnLevelName(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String LevelName = "";
        try {
            MyDatabase ReturnLevelNameDatabaseObject = MyDatabase.getInstance();
            connection = ReturnLevelNameDatabaseObject.connect_to_DB();
            String sql = "select Level from user,userlevel where user.LevelID=userlevel.ID and user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                LevelName = result.getString("Level");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ReturnLevelName Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return LevelName;
    }

    public static String ReturnLevelNameFromUserLevelTable(int levelID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String LevelName = "";
        try {
            MyDatabase ReturnLevelNameFromUserLevelTableDatabaseObject = MyDatabase.getInstance();
            connection = ReturnLevelNameFromUserLevelTableDatabaseObject.connect_to_DB();
            String sql = "select Level from userlevel where userlevel.ID=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, levelID);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                LevelName = result.getString("Level");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("ReturnLevelNameFromUserLevelTable Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return LevelName;
    }

    public static String ReturnDepartmentName(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String DepartmentName = "";
        try {
            MyDatabase ReturnDepartmentNameDatabaseObject = MyDatabase.getInstance();
            connection = ReturnDepartmentNameDatabaseObject.connect_to_DB();
            String sql = "select DepartmentName from user,user_department where user.DepartmentID=user_department.ID and user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                DepartmentName = result.getString("DepartmentName");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnDepartmentName Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return DepartmentName;
    }

    public static String ReturnUserTypeName(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String UserType = "";
        try {
            MyDatabase ReturnDepartmentNameDatabaseObject = MyDatabase.getInstance();
            connection = ReturnDepartmentNameDatabaseObject.connect_to_DB();
            String sql = "select UserType from user,usertype where user.UserTypeID=usertype.ID and user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                UserType = result.getString("UserType");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnUserTypeName Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return UserType;
    }

    public static int ReturnUserTypeID(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int UserTypeID = 0;
        try {
            MyDatabase ReturnUserTypeIDDatabaseObject = MyDatabase.getInstance();
            connection = ReturnUserTypeIDDatabaseObject.connect_to_DB();
            String sql = "select UserTypeID from user where  user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                UserTypeID = result.getInt("UserTypeID");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnUserTypeID Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return UserTypeID;
    }

    public static String ReturnMyUniversityCodeString(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String MyUniversityCodeString = "";
        try {
            MyDatabase ReturnMyUniversityCodeString = MyDatabase.getInstance();
            connection = ReturnMyUniversityCodeString.connect_to_DB();
            String sql = "select MyUniversityCodeString from user where  user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                MyUniversityCodeString = result.getString("MyUniversityCodeString");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnMyUniversityCodeString Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return MyUniversityCodeString;
    }

    public static String ReturnUserName(int student_code) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String UserName = "";
        try {
            MyDatabase ReturnDepartmentNameDatabaseObject = MyDatabase.getInstance();
            connection = ReturnDepartmentNameDatabaseObject.connect_to_DB();
            String sql = "select * from user where  user.Code=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, student_code);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                UserName = result.getString("FName") + "" + result.getString("MName") + "" + result.getString("LName");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnUserName Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return UserName;
    }

    public static String ReturnDepartmentNameFromUserDepartmentTable(int departmentID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String DepartmentName = "";
        try {
            MyDatabase ReturnDepartmentNameFromUserDepartmentTableDatabaseObject = MyDatabase.getInstance();
            connection = ReturnDepartmentNameFromUserDepartmentTableDatabaseObject.connect_to_DB();
            String sql = "select DepartmentName from user_department where user_department.ID =? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, departmentID);
            result = prepared_statement.executeQuery();

            while (result.next()) {
                DepartmentName = result.getString("DepartmentName");
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ReturnDepartmentNameFromUserDepartmentTable Error:" + ex.getMessage());

        } finally {
            /*try {
             connection.close();
             // result.close();
             prepared_statement.close();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage() + "Can't Close ResultSet(ViewMyCourses)");
             }*/
        }

        return DepartmentName;
    }

    public ResultSet ViewDepartmentNames() {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String DepartmentName = "";
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `user_department` WHERE 1 ";
            prepared_statement = connection.prepareStatement(sql);
            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ViewDepartmentNames Error:" + ex.getMessage());

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

    public ResultSet ViewLevelNames() {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String DepartmentName = "";
        try {
            MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `userlevel` WHERE 1";
            prepared_statement = connection.prepareStatement(sql);
            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println(" ViewLevelNames Error:" + ex.getMessage());

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

    public int RegisterUser(int Code, String FName, String LName,
            String UserEmail, String Pass, int UserType, int levelID,
            String gender, int FaculityID, int UniversityID,
            String RegistrationDate, int DepartmentIDMethod, String MyUniversityCodeString) {

        int rowAffected = 0;
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;

        try {
            MyDatabase RegisterUsersDatabaseObject = MyDatabase.getInstance();
            connection = RegisterUsersDatabaseObject.connect_to_DB();
            String sql = "INSERT INTO `user`(`Code`, `Mail`, `FName`, `LName`, `Password`, `FacultyID`, `UniversityID`, `UserTypeID`, `Gender`,`LevelID`,`RegistrationDate`,`DepartmentID`,`MyUniversityCodeString`)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, Code);
            prepared_statement.setString(2, UserEmail);

            prepared_statement.setString(3, FName);
            prepared_statement.setString(4, LName);
            prepared_statement.setString(5, Pass);
            prepared_statement.setInt(6, FaculityID);
            prepared_statement.setInt(7, UniversityID);
            prepared_statement.setInt(8, UserType);
            prepared_statement.setString(9, gender);
            prepared_statement.setInt(10, levelID);
            prepared_statement.setString(11, RegistrationDate);
            prepared_statement.setInt(12, DepartmentIDMethod);
            prepared_statement.setString(13, MyUniversityCodeString);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println(" Registration Successfuly");

        } catch (SQLException ex) {
            // Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed Registration" + ex.getMessage());
            //this.registerUsersmsg += "Failed Regestration" + ex.getMessage() + "\r\n";

            //            JOptionPane.showMessageDialog(null,ex);
        }
        return rowAffected;
    }//end of register

    public MyPerson MyLoginFunction(String mail, String Pass) {
        MyPerson p = null;
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            p = new MyPerson();
            MyDatabase LoginDatabaseObject = MyDatabase.getInstance();
            connection = LoginDatabaseObject.connect_to_DB();
            String sql = "select * from user where `Mail`=? and `Password`=? and `Approve`=? ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setString(1, mail);
            prepared_statement.setString(2, Pass);
            prepared_statement.setString(3, "Yes");

            result = prepared_statement.executeQuery();

            while (result.next()) {
                p.setUserEmail(result.getString("Mail"));
                p.setFName(result.getString("FName"));
                p.setLName(result.getString("LName"));
                p.setMName(result.getString("MName"));
                p.setPass(result.getString("Password"));
                p.setAddress(result.getString("Address"));
                p.setBirthDate(result.getString("BirthDate"));
                p.setGender(result.getString("Gender"));
                p.setDate(result.getString("RegistrationDate"));
                p.setCode(result.getInt("Code"));
                p.setFaculityID(result.getInt("FacultyID"));
                p.setUniversityID(result.getInt("UniversityID"));
                p.setUserType(result.getInt("UserTypeID"));
                p.setLevelID(result.getInt("LevelID"));
                p.setSSN(result.getInt("SSn"));
                p.setDepartmentID(result.getInt("DepartmentID"));
                p.setMyUniversityCodeString(result.getString("MyUniversityCodeString"));
            }
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("" + ex.getMessage() + "LoginError");

        }
        return p;
    }

    //End of Login
//chat
    public int SaveMessage(int SenderID, String Message, int RecieverID, String MessageDate) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddCommentMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddCommentMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `chat`(`UserID`, `Message`, `RecieverID`, `MessageDate`) VALUES (?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, SenderID);
            prepared_statement.setString(2, Message);
            prepared_statement.setInt(3, RecieverID);
            prepared_statement.setString(4, MessageDate);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("ChatMethod Error:" + ex.getMessage());
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

    //end of chat
    //Add Posts
    public int AddPostMethod(int UserID, String Post, int SubjectPostID, String RegistrationPostDate) throws SQLException {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddPostMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddPostMethodDatabaseObject.connect_to_DB();

            String sql = "insert into post (UserID,Post,SubjectPostID,RegistrationPostDate) values(?,?,?,?)";

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UserID);
            prepared_statement.setString(2, Post);
            prepared_statement.setInt(3, SubjectPostID);
            prepared_statement.setString(4, RegistrationPostDate);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);
        } catch (SQLException ex) {
            System.err.println("AddPostMethod Error:" + ex.getMessage());
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
    //End of Add Post Method
    //AddPNewsMethod

    public int AddPNewsMethod(int UserID, String Post, int UniversityID, int FacultyID, String PostDate) throws SQLException {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddPostMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddPostMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `news`(`Post`, `PostDate`, `UserID`, `UniversityID`, `FacultyID`) VALUES (?,?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setString(1, Post);
            prepared_statement.setString(2, PostDate);
            prepared_statement.setInt(3, UserID);
            prepared_statement.setInt(4, UniversityID);
            prepared_statement.setInt(5, FacultyID);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);
        } catch (SQLException ex) {
            System.err.println("AddPNewsMethod Error:" + ex.getMessage());
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
    //End of Add News Method 

    //Add CommentMethod
    public int AddCommentMethod(int UserID, String commentContent, int PostID, String RegistrationCommentDate) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AddCommentMethodDatabaseObject = MyDatabase.getInstance();
            connection = AddCommentMethodDatabaseObject.connect_to_DB();

            String sql = "INSERT INTO `comment`(`PostID`, `UserID`, `commentContent`,`RegistrationCommentDate`) VALUES (?,?,?,?)";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, PostID);
            prepared_statement.setInt(2, UserID);
            prepared_statement.setString(3, commentContent);
            prepared_statement.setString(4, RegistrationCommentDate);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("AddCommentMethod Error:" + ex.getMessage());
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
    //End of Add Comment Method

    //Delete CommentMethod
    public int DeleteCommentMethod(int CommentID, int UserID) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase DeleteCommentMethodDatabaseObject = MyDatabase.getInstance();
            connection = DeleteCommentMethodDatabaseObject.connect_to_DB();

            String sql = "Delete from `comment` where `comment`.`CommentID`=? and `comment`.`UserID`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, CommentID);
            prepared_statement.setInt(2, UserID);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Delete Comment Error:" + ex.getMessage());
        }
        return rowAffected;
    }
    //End of Delete Comment Method

    //Delete PostMethod
    public int DeletePostMethod(int PostID, int UserID) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase DeletePostMethodDatabaseObject = MyDatabase.getInstance();
            connection = DeletePostMethodDatabaseObject.connect_to_DB();

            String sql = "Delete from `Post` where `Post`.`PostID`=? and `Post`.`UserID`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, PostID);
            prepared_statement.setInt(2, UserID);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Delete Post Error:" + ex.getMessage());
        }
        return rowAffected;
    }

    //End of Delete Post Method
    //Delete PostMethod
    public int DeleteNewsMethod(int PostID, int UserID) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase DeleteNewsMethodDatabaseObject = MyDatabase.getInstance();
            connection = DeleteNewsMethodDatabaseObject.connect_to_DB();

            String sql = "Delete from `news` where `news`.`ID`=? and `news`.`UserID`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, PostID);
            prepared_statement.setInt(2, UserID);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Delete News Error:" + ex.getMessage());
        }
        return rowAffected;
    }

    //End of Delete News Method
//Delete User
    public int DeleteUserMethod(int UserID) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase DeleteUserMethodDatabaseObject = MyDatabase.getInstance();
            connection = DeleteUserMethodDatabaseObject.connect_to_DB();

            String sql = "Update `user`  set  `Approve` ='No' where `Code`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UserID);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Delete User Error:" + ex.getMessage());
        }
        return rowAffected;
    }

    //End of Delete user Method
//Allow User
    public int AllowUserMethod(int UserID) {
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        int rowAffected = -1;
        try {
            MyDatabase AllowUserMethodDatabaseObject = MyDatabase.getInstance();
            connection = AllowUserMethodDatabaseObject.connect_to_DB();

            String sql = "Update `user`  set  `Approve` ='Yes' where `Code`=?";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UserID);
            rowAffected = prepared_statement.executeUpdate();
            System.out.println("# of users " + MyDatabase.count);

        } catch (SQLException ex) {
            System.err.println("Allow User Error:" + ex.getMessage());
        }
        return rowAffected;
    }
    //End of Allow user Method

    //View Course Posts
    public ResultSet ViewCoursePosts(int Subject_ID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String sql = "select * from `post`,`user`,`subject` where `post`.`UserID`=`user`.`Code` and `subject`.`ID`=`post`.`SubjectPostID` and `subject`.`ID`=? ORDER BY `post`.`PostID` DESC";
        try {
            MyDatabase ViewCoursePostsDatabaseObject = MyDatabase.getInstance();
            connection = ViewCoursePostsDatabaseObject.connect_to_DB();

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, Subject_ID);
            result = prepared_statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ViewCoursePosts Error:" + ex.getMessage());
        }
        return result;

    }
    //End of ViewCoursePosts
    // ViewNews

    public ResultSet ViewNews(int UniversityID, int FacultyID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String sql = "select * from `news`,`user` where `news`.`UserID`=`user`.`Code`and `news`.`UniversityID`=?  and `news`.`FacultyID`=? ORDER BY `news`.`ID` DESC";
        try {
            MyDatabase ViewNewsatabaseObject = MyDatabase.getInstance();
            connection = ViewNewsatabaseObject.connect_to_DB();

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, UniversityID);
            prepared_statement.setInt(2, FacultyID);

            result = prepared_statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ViewNews Error:" + ex.getMessage());
        }
        return result;

    }
   //End of ViewNews

    //ViewCoursePostContent
    public ResultSet ViewCoursePostContent(int Subject_ID, int POSTID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String sql = "select * from post,user,subject where post.UserID=user.Code and subject.ID=post.SubjectPostID and  subject.ID=? and post.PostID=?";
        try {
            MyDatabase ViewCoursePostContentDatabaseObject = MyDatabase.getInstance();
            connection = ViewCoursePostContentDatabaseObject.connect_to_DB();

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, Subject_ID);
            prepared_statement.setInt(2, POSTID);

            result = prepared_statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ViewCoursePostContent Error:" + ex.getMessage());
        }
        return result;

    }
    //End of ViewCoursePostContent

//View Comments
    public ResultSet ViewCoursePostComments(int PostID) throws SQLException {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        String sql = "select `comment`.`commentContent`,`comment`.`CommentID`,`comment`.`UserID`,`comment`.`RegistrationCommentDate`,`user`.`Code`,`user`.`UserTypeID`,`user`.`FName`,`user`.`LName`,`user`.`Mail`,`user`.`Code`,`post`.`PostID` from `comment`,`user`,`post` where `comment`.`UserID`=`user`.`Code` and `comment`.`PostID`=`post`.`PostID` and `post`.`PostID`=? ";
        try {
            MyDatabase ViewCoursePostCommentsDatabaseObject = MyDatabase.getInstance();
            connection = ViewCoursePostCommentsDatabaseObject.connect_to_DB();

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, PostID);
            result = prepared_statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ViewCoursePostComments Error:" + ex.getMessage());
        }
        return result;

    }//End of View Comments

    public int EditProfile(int Code, String FName, String MName, String LName, String UserEmail, String Pass, String Address, String BirthDateFunction) {

        int rowAffected = 0;
        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;

        try {
            MyDatabase RegisterUsersDatabaseObject = MyDatabase.getInstance();
            connection = RegisterUsersDatabaseObject.connect_to_DB();
            String sql = "UPDATE `user` SET `Mail`=?,`FName`=?,`LName`=?,`MName`=?,`Password`=? ,`BirthDate`=?,`Address`=? WHERE `Code`=?";

            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setString(1, UserEmail);
            prepared_statement.setString(2, FName);
            prepared_statement.setString(3, LName);
            prepared_statement.setString(4, MName);
            prepared_statement.setString(5, Pass);
            prepared_statement.setString(6, Address);
            prepared_statement.setString(7, BirthDateFunction);

            prepared_statement.setInt(8, Code);

            rowAffected = prepared_statement.executeUpdate();
            System.out.println(" Edit Profile Successfuly");

        } catch (SQLException ex) {
            // Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed Editing Profile" + ex.getMessage());
            //this.registerUsersmsg += "Failed Regestration" + ex.getMessage() + "\r\n";

            //            JOptionPane.showMessageDialog(null,ex);
        }
        return rowAffected;
    }//end of register

    public ResultSet ViewAllUsers(int FacultyID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewAllUsersDatabaseObject = MyDatabase.getInstance();
            connection = ViewAllUsersDatabaseObject.connect_to_DB();
            String sql = "SELECT * FROM `user` order by `FName` asc  ";
            prepared_statement = connection.prepareStatement(sql);
            // prepared_statement.setInt(1, FacultyID);
            // prepared_statement.setInt(2, userType);

            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("" + ex.getMessage() + ":ViewAllUsers");

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

    public ResultSet ViewmyMessages(int UserID, int RecieverID) {

        ResultSet result = null;
        PreparedStatement prepared_statement = null;
        Connection connection = null;
        try {
            MyDatabase ViewmyMessagesDatabaseObject = MyDatabase.getInstance();
            connection = ViewmyMessagesDatabaseObject.connect_to_DB();
            String sql = "SELECT `MessageDate`,`Message`,`RecieverID`,`UserID`,`TimeStamp` FROM `chat` WHERE (`RecieverID`=? and `UserID`=?) or (`RecieverID`=? and `UserID`=?) Order by `MessageDate` ASC  ";
            prepared_statement = connection.prepareStatement(sql);
            prepared_statement.setInt(1, RecieverID);
            prepared_statement.setInt(2, UserID);
            prepared_statement.setInt(3, UserID);
            prepared_statement.setInt(4, RecieverID);
            result = prepared_statement.executeQuery();

        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // System.out.println("SQLException:Can't Login");
            System.err.println("" + ex.getMessage() + ":ViewmyMessages");

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

}//End of My Person
