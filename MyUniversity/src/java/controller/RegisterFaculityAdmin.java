/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class RegisterFaculityAdmin extends HttpServlet {

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
                if (p.getUserType() == 2) {
                    String Code = request.getParameter("Code");
                    String Mail = request.getParameter("Mail");
                    String FName = request.getParameter("FName");
                    String LName = request.getParameter("LName");
                    String Gender = request.getParameter("Gender");
                    String FacultyID = request.getParameter("FacultyID");
                    String Password = request.getParameter("Password");
                    String ConfirmPassword = request.getParameter("ConfirmPassword");
                    boolean isEveryThingOKAll = true;
                    int CodeInteger = 0, facultyIDInteger = 0;

                    if (Code == null || Code.isEmpty()
                            || FacultyID == null || FacultyID.isEmpty()
                            || Mail == null || Mail.isEmpty()
                            || Gender == null || Gender.isEmpty()
                            || Password == null || Password.isEmpty()
                            || FName == null || FName.isEmpty()
                            || LName == null || LName.isEmpty()
                            || ConfirmPassword == null || ConfirmPassword.isEmpty()) {

                        out.println("<script type='text/javascript' > alert('Please fill all fields   ^_^ ');history.back();</script>");
                        isEveryThingOKAll = false;
                    } else {
                        /* out.println("Password:" + Password + "<br/>");
                         out.println("ConfirmPassword:" + ConfirmPassword + "<br/>");
                         out.println("Type:" + Type + "<br/>");
                         out.println("Code:" + Code + "<br/>");
                         out.println("Mail:" + Mail + "<br/>");
                         out.println("FName:" + FName + "<br/>");
                         out.println("LName:" + LName + "<br/>");
                         */
                        if (Password.equals(ConfirmPassword)) {
                            //out.println("Password.equals(ConfirmPassword)");
                            isEveryThingOKAll = true;

                        } else {

                            isEveryThingOKAll = false;
                            out.println("<script type='text/javascript' > alert('Password Doesn't match     ^_^ ');history.back();</script>");
                            out.println("<font color='red'>Password Doesn't match    ^_^</font>");
                            out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                        }
                        /*   if (isEveryThingOKAll) {
                         if (Password.equals(ConfirmPassword)) {
                         out.println("<script type='text/javascript' > alert('Password Doesn't match ^_^ ');history.back();</script>");

                         isEveryThingOKAll = false;

                         }
                         }*/
                        if (isEveryThingOKAll) {
                            try {
                                CodeInteger = Integer.parseInt(Code);
                                facultyIDInteger = Integer.parseInt(FacultyID);
                            } catch (Exception ex) {
                                out.println("<script type='text/javascript' > alert('Code is an integer please   ^_^ ');history.back();</script>");
                                System.out.println("Code in Register User:" + ex.getMessage());
                                isEveryThingOKAll = false;

                            }
                        }

                        if (isEveryThingOKAll) {

                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");

                            String RegistrationDate = sdf1.format(new Date());
                            String MyUniversityCodeString = sdf2.format(new Date()) + p.getFaculityID() + Code.trim();
                            //  out.println("MyUniversityCodeString" + MyUniversityCodeString);
                            //  int RegistrationDateCodeInteger =Integer.parseInt(RegistrationDateCodeString.trim());
                            //out.println("RegistrationDateCodeInteger" + RegistrationDateCodeInteger);

                            int rowAffected = 0;
                            try {
                                rowAffected = p.RegisterUser(CodeInteger, FName, LName, Mail, Password, 3, 6, Gender,facultyIDInteger, p.getUniversityID(), RegistrationDate, 1, MyUniversityCodeString);
                                if (rowAffected > 0) {
                                    // out.println("<script type='text/javascript' > alert('User was Adding successfully ^_^ ');history.back();</script>");
                                    response.sendRedirect("MyAccount.jsp?page=2UniversityAdminViewUsers");

                                } else {
                                    out.println("<script type='text/javascript' > alert('User Registration  was  Failed ^_^ ');history.back();</script>");
                                }

                            } catch (Exception ex) {
                                System.err.println("RegisterUser Servlet Error:" + ex.getMessage());
                                out.println("RegisterUser Servlet Error:" + ex.getMessage());
                                out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                            }
                        }//  password matches,code are correct

                    }//if fields are not empty
                } //end of is this is faculity admin or not
                else {
                    out.println("<br/><br/><br/><br/><br/>");
                    out.println("<font color='blue'><h1>You shouldn't be here ^_^ </h1></font>");
                    out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

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

}
