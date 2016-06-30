/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ModelOfProfessor;

/**
 *
 * @author Badr
 */
public class addgrade extends HttpServlet {

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
            try {
                int WritenGrade = Integer.parseInt(request.getParameter("WritenGrade"));
                int MidtermGrade = Integer.parseInt(request.getParameter("MidtermGrade"));
                int FinalExamGrade = Integer.parseInt(request.getParameter("FinalExamGrade"));
                int FinalGrade = Integer.parseInt(request.getParameter("FinalGrade"));

                int studentid = Integer.parseInt(request.getParameter("StudentID"));
                int Subject_Code;
                Subject_Code = Integer.parseInt(request.getParameter("SubjectID"));
                ModelOfProfessor prof = new ModelOfProfessor();
                int rowAffected;
                try {
                    rowAffected = prof.addgrade(WritenGrade, MidtermGrade, FinalExamGrade, FinalGrade, Subject_Code, studentid);

                    if (rowAffected > 0) {
                        //out.println("<script type='text/javascript' > alert('Post was success ^_^ ');history.back();</script>");
                        response.sendRedirect("MyAccount.jsp?page=CourseGrades&Subject_Code=" + Subject_Code + "");
                      //  out.println("<script type='text/javascript' > alert('Post was Adding Successfully  ^_^ ');history.back();</script>");

                    } else {
                        out.println("<script type='text/javascript' > alert('Updating Grade is Failed ^_^ ');history.back();</script>");
                    }

                } catch (Exception ex) {
                    System.err.println("AddGrade Servlet Error:" + ex.getMessage());
                    out.println("AddGrade Servlet Error:" + ex.getMessage());
                    out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                out.println("<center><a href='index.jsp' >Home</a></center>");
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
