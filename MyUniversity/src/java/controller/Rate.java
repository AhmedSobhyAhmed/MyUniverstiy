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
import models.ModelOfStudent;
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class Rate extends HttpServlet {

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
                if (p.getUserType() == 6) {
                    String rateGrade = request.getParameter("rateGrade");
                    String Positives = request.getParameter("Positives");
                    String Negatives = request.getParameter("Negatives");
                    String CodeInteger = request.getParameter("Code");
                    int code = 0, rateGradeInteger = 0;
                    boolean isEverythingIsOk = true;
                  /*  out.println("<br/>rateGrade" + rateGrade);
                    out.println("<br/>Positives" + Positives);
                    out.println("<br/>Negatives:" + Negatives);
                    out.println("<br/>CodeInteger:" + CodeInteger);

                    */
                    if (rateGrade == null || rateGrade.isEmpty()
                            || Positives == null || Positives.isEmpty()
                            || Negatives == null || Negatives.isEmpty()
                            || CodeInteger == null || CodeInteger.isEmpty()) {
                        out.println("<script type='text/javascript' > alert('Please fill all fields^_^ ');history.back();</script>");
                        isEverythingIsOk = false;
                    } else {
                        ModelOfStudent modelOfStudent = new ModelOfStudent();
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String RateDate = sdf1.format(new Date());
                        try {

                            code = Integer.parseInt(CodeInteger);
                            rateGradeInteger = Integer.parseInt(rateGrade);
                            // out.println(Subject_Code);
                        } catch (Exception ex) {
                            isEverythingIsOk = false;
                            out.println("<script type='text/javascript' > alert('Code and grade are  an integer value please   ^_^ ');history.back();</script>");
                            System.out.println("Post ID Delet user ErrorServlet Error:" + ex.getMessage());
                        }

                        if (isEverythingIsOk) {
                            if (rateGradeInteger < 0 || rateGradeInteger > 10) {
                                isEverythingIsOk = false;
                                out.println("<script type='text/javascript' > alert('Grades between 0 and 10 please   ^_^ ');history.back();</script>");
                                System.out.println("Grades between 0 and 10 please   ^_^");
                                out.println("Grades between 0 and 10 please   ^_^");
                                out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                            }
                        }
                        if (isEverythingIsOk) {

                            int rowAffected;
                            try {

                                rowAffected = modelOfStudent.rateInstructor(code, p.getCode(), rateGradeInteger, Positives, Negatives, RateDate, p.getFaculityID());
                                if (rowAffected > 0) {
                                    out.println("<script type='text/javascript' > alert('Rating was Adding successfully ^_^ ');history.back();</script>");
                                    // response.sendRedirect("MyAccount.jsp?page=CoursePosts&Subject_Code="+Subject_Code+"");

                                } else {
                                    out.println("<script type='text/javascript' > alert('Rating Registration was  Failed ^_^ ');history.back();</script>");
                                }

                            } catch (Exception ex) {
                                System.err.println("Addfaculty Servlet Error:" + ex.getMessage());
                                out.println("Addfaculty Servlet Error:" + ex.getMessage());
                                out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                            }
                        }//end of isEveryThingOK or not

                    }//if all fields is empty or not
                }//end of is student or not
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
