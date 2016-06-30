package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import models.MyPerson;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mohamed
 */
public class UploadExcelStudentsFile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            if (session.getAttribute("UserObject") != null) {
                out.println("<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>");
                out.println("<center>");
                MyPerson p = (MyPerson) session.getAttribute("UserObject");
                File f = null;
                try {
                    MultipartRequest m = new MultipartRequest(request, "D:/");
                    out.println("<font color='blue'>successfully uploaded" + "</font><br/>");

                    // out.println(m.getContentType("fname"));
                    f = m.getFile("fname");
                    if (f.length() < 1048576) {

                        /*     out.println(f.getName());
                         out.println(f.getPath());
                         out.println(f.length());
                         */
                        if (m.getContentType("fname").equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                            try {
                                String path = f.getPath();

                                String messages = this.readFromExcel(path, p);
                                out.println("<font color='blue'>successfully reading</font>" + "<br/>");
                                messages += "<center><a href='MyAccount.jsp?page=3FaculityAdminAddStudents' >Home</a></center>";

                                out.println(messages);

                            } catch (Exception ex) {
                                out.println("<font color='red'>failed reading" + ex.getMessage() + "</font>");
                                out.println("<script type='text/javascript' > alert('failed reading Excel Error:" + ex.getMessage() + " ');history.back();</script>");

                            }
                        }//end of type=xlsx
                        else {

                            out.println("<script type='text/javascript' > alert('file must be .xlsx please ^_^ ');history.back();</script>");
                        }

                    }//end of  iflength of file <1048576 or not 
                    else {

                        out.println("<script type='text/javascript' > alert('file length must be less than 1048576 please ^_^ ');history.back();</script>");
                    }
                } catch (Exception ex) {
                    //out.println("uploading Excel Error:" + ex.getMessage());
                    out.println("<script type='text/javascript' > alert('uploading Excel Error:" + ex.getMessage() + " ');history.back();</script>");

                } finally {
                    if (f.exists()) {
                        f.delete();
                    }

                }
                out.println("</center>");

            }//end of if session is exit or not
            else {
                RequestDispatcher RD = request.getRequestDispatcher(response.encodeURL("index.jsp"));
                RD.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String readFromExcel(String path, MyPerson p) {
        String messages = "";
        String ColumnsMesages = "";

        try {
            FileInputStream file;
            file = new FileInputStream(new File(path));

//Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

//Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

//Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int rowCount = 0;
            boolean isEverythingIsOK = true;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                rowCount++;
                System.out.println("rcount:" + rowCount);

                if (rowCount > 1) {
//For each row, iterate through all the columns
                    int Code = 0;
                    String Fname = "", Lname = "", Email = "";
                    String password = "";
                    int userType = 0;
                    int level = 0;
                    String gender = "";
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int ColumnCount = 0;
                    int rowCountMesages = rowCount - 1;

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        ColumnCount++;

//Check the cell type and format accordingly
                        System.out.println("ccount:" + ColumnCount);

                        /*  switch (cell.getCellType()) {
                         case Cell.CELL_TYPE_NUMERIC:
                         System.out.println(cell.getNumericCellValue() + "");
                         System.out.println("numeric type case:" + Cell.CELL_TYPE_NUMERIC);// numeric type case:0
                         System.out.println("type:" + cell.getCellType());//type:0
                         break;
                         case Cell.CELL_TYPE_STRING:
                         System.out.println(cell.getStringCellValue());

                         System.out.print("string type case:" + Cell.CELL_TYPE_STRING + "");//string type case:0
                         System.out.println("type:" + cell.getCellType());//type:1

                         break;
                         }*/
                        if (ColumnCount == 1) {
                            if (cell.getCellType() == 0) {
                                Code = (int) cell.getNumericCellValue();
                                password = String.valueOf(Code);
                                System.out.println("Code:" + Code);
                                System.out.println("pass:" + password);

                            } else {
                                isEverythingIsOK = false;
                                ColumnsMesages += "<font color='red'>First Column is Code must be an integer please  in student number (" + rowCountMesages + ")</font><br/>";
                            }
                        } else if (ColumnCount == 2) {
                            if (cell.getCellType() == 1) {

                                Fname = cell.getStringCellValue();
                                System.out.println("Fname:" + Fname);
                            } else {
                                isEverythingIsOK = false;
                                ColumnsMesages += " <font color='red'>Column number 2  is Fname  must be a String please  in student number (" + rowCountMesages + ")</font><br/>";
                            }
                        } else if (ColumnCount == 3) {
                            if (cell.getCellType() == 1) {

                                Lname = cell.getStringCellValue();
                                System.out.println("Lname:" + Lname);
                            } else {
                                isEverythingIsOK = false;
                                ColumnsMesages += "<font color='red'> Column number 3 is Lname  must be a String please  in student number (" + rowCountMesages + ")</font><br>";
                            }
                        } /*else if(ColumnCount==4){
                         String Pass=cell.getStringCellValue();
                         System.out.println("Pass:"+Pass);

                         }*/ else if (ColumnCount == 4) {
                            if (cell.getCellType() == 1) {

                                Email = cell.getStringCellValue();
                                System.out.println("Email:" + Email);
                            } else {
                                isEverythingIsOK = false;
                                ColumnsMesages += " <font color='red'>Column number 4  is Email  must be a String please  in student number (" + rowCountMesages + ")</font><br/>";
                            }
                        } else if (ColumnCount == 5) {
                            if (cell.getCellType() == 1) {

                                gender = cell.getStringCellValue();
                                System.out.println("gender:" + gender);
                            } else {
                                isEverythingIsOK = false;
                                ColumnsMesages += "<font color='red'> Column number 5  is gender  must be a String please  in student number (" + rowCountMesages + ")</font><br/>";
                            }
                        } else if (ColumnCount == 6) {
                            if (cell.getCellType() == 0) {

                                userType = (int) cell.getNumericCellValue();
                                System.out.println("userType:" + userType);
                            } else {
                                isEverythingIsOK = false;
                                ColumnsMesages += " <font color='red'> Column  number 6 is userType  must be an integer please  in student number (" + rowCountMesages + ")</font><br/>";
                            }
                        } else if (ColumnCount == 7) {
                            if (cell.getCellType() == 0) {

                                level = (int) cell.getNumericCellValue();
                                System.out.println("level:" + level);
                            } else {
                                isEverythingIsOK = false;
                                ColumnsMesages += "<font color='red'> Column number 7 is level  must be an integer please  in student number (" + rowCountMesages + ")</font><br/>";

                            }
                        }
                    }//end of celIterator

                    int rowAffected = 0;
                    if (isEverythingIsOK) {
                        try {
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                            String RegistrationDate = sdf1.format(new Date());
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
                            String MyUniversityCodeString = sdf2.format(new Date()) + p.getFaculityID() + Code;

                            rowAffected = p.RegisterUser(Code, Fname, Lname, Email, password, userType, 1, gender, p.getFaculityID(), p.getUniversityID(), RegistrationDate, 1, MyUniversityCodeString);

                            if (rowAffected > 0) {
//response.sendRedirect("MyAccount.jsp?page=CourseViewPOSTContents&POSTID=" +POSTID+"&Subject_Code="+Subject_Code+"");
//response.sendRedirect("MyAccount.jsp?page=viewPost&POSTID=" +POSTID+"&Subject_Code="+Subject_Code+"");
                                messages += "<font color='blue'>Student(" + rowCountMesages + ")was Adding Successfully  ^_^</font>" + "<br/>";
                                System.out.println("<script type='text/javascript' > alert('Student was Adding Successfully  ^_^ ');history.back();</script>");
                            } else {
                                messages += "<font color='red'> Student(" + rowCountMesages + ")was Adding Failed  ^_^</font>" + "<br/>";

                                System.out.println("<script type='text/javascript' > alert('Student was Failed ^_^ ');history.back();</script>");
                            }

                        } catch (Exception ex) {
                            System.err.println("Add Students Error" + ex.getMessage());
                            messages += "<font color='red'>Adding Students Error" + ex.getMessage() + "</font><br/>";
                            messages += "<center><a href='index.jsp' >Home</a></center>";
                        }
                    }//end of if IsEverythingIsOk Or Not
                    System.out.println("");

                }//end of if this is not first row
            }//end of while rowIterator
            file.close();
        }//end of try 
        catch (Exception e) {
            e.printStackTrace();
            messages += "<font color='red'>" + e.getMessage() + "</font><br/>";
        }
        messages += ColumnsMesages;

        return messages;
    }//end of read function
}
