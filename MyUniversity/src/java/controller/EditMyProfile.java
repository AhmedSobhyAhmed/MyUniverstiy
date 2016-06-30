/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class EditMyProfile extends HttpServlet {

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
                MyPerson p = (MyPerson) session.getAttribute("UserObject");
                String FName = request.getParameter("FName"),
                        MName = request.getParameter("MName"),
                        LName = request.getParameter("LName"),
                        Password = request.getParameter("Password"),
                        ConfirmPassword = request.getParameter("ConfirmPassword"),
                        Mail = request.getParameter("Mail"),
                        Address = request.getParameter("Address"),
                        BDate = request.getParameter("BDate");
                boolean isAnyThingIsNotEmpty = true, isPasswordEqualConfirom = true;
                
                if (FName.isEmpty() || FName == null
                        || MName.isEmpty() || MName == null
                        || LName.isEmpty() || LName == null
                        || Password.isEmpty() || Password == null
                        || ConfirmPassword.isEmpty() || ConfirmPassword == null
                        || Mail.isEmpty() || Mail == null
                        || Address.isEmpty() || Address == null
                        || BDate.isEmpty() || BDate == null) {
                    isAnyThingIsNotEmpty = false;
                    out.println("<script type='text/javascript' > alert('Please Fill all fields ^_^  ');history.back();</script>");
                }
                if (isAnyThingIsNotEmpty) {
                    if (!Password.equals(ConfirmPassword)) {
                        isPasswordEqualConfirom = false;
                        out.println("<br/>");
                        out.println("<br/>");
                        out.println("<br/>");
                        out.println("<br/>");
                        out.println("<br/>");
                        out.println("<br/>");
                        
                        out.println("<center>");
                        out.println("<font color='red' >Password doesn't match ^_^</font>");
                        out.println("<a href='MyAccount.jsp?page=EditProfile' >Back ^_^ </a>");
                        
                        out.println("</center>");
                        
                        out.println("<script type='text/javascript' > alert('Password doesn't match ^_^  ');history.back();</script>");
                        
                    }
                }
                
                if (isAnyThingIsNotEmpty && isPasswordEqualConfirom) {
                    out.println("Fname=" + FName);
                    out.println("Mname=" + MName);
                    
                    out.println("Lname=" + LName);
                    
                    out.println("Mail=" + Mail);
                    
                    out.println("Password=" + Password);
                    out.println("Address=" + Address);
                    
                    out.println("BDate=" + BDate);
                    
                    int rowAffected;
                    try {
                        rowAffected = p.EditProfile(p.getCode(), FName, MName, LName, Mail, Password, Address, BDate);
                        if (rowAffected > 0) {
                            p.setFName(FName);
                            p.setMName(MName);
                            p.setLName(LName);
                            p.setPass(Password);
                            p.setUserEmail(Mail);
                            p.setAddress(Address);
                            p.setBirthDate(BDate);
                            response.sendRedirect("MyAccount.jsp?page=EditProfile");
                            //               out.println("<script type='text/javascript' > alert('Editting Profile Done Successfull ^_^ ');history.back();</script>");

                        } else {
                            out.println("<script type='text/javascript' > alert('Editting Profile is Failed ^_^ ');history.back();</script>");
                        }
                        
                    } catch (Exception ex) {
                        System.err.println("DeletePost Servlet Error:" + ex.getMessage());
                        out.println("<script type='text/javascript' > alert('Editting Profile Servlet Error:'" + ex.getMessage() + " ');history.back();</script>");
                        
                        out.println("Editting Profile Servlet Error:" + ex.getMessage());
                        
                        out.println("<center><a href='MyAccount.jsp' >Home</a></center>");
                        
                    }
                }
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
