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
import javax.servlet.http.HttpSession;
import models.MyPerson;

/**
 *
 * @author mohamed
 */
public class MyLogin extends HttpServlet {

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
                String user_mail = request.getParameter("UserEmail");
                String user_password = request.getParameter("UserPassword");

                if (user_mail == null || user_mail.isEmpty() || user_password == null || user_password.isEmpty()) {
                    out.println("<script type='text/javascript' > alert('Please fill all fields  ^_^ ');history.back();</script>");

                } else {

                    //Connect to DB
                    MyPerson p = new MyPerson();
                    // Person p2=new Person();
                    p = p.MyLoginFunction(user_mail, user_password);
           //out.println("<br>"+p.getName());
                    //UserObject=DBObject.selectalluserStatement(user_email, user_password);
                    // out.println(UserObject.getEmail());
                    if (p.getUserEmail() == null || p.getUserEmail().isEmpty()) {

                        out.println("<script type='text/javascript' > alert('User email or Password not valid,please try again  ');history.back();</script>");
                        //    response.sendRedirect("InvalidRequest.jsp");
                    } else {
                        out.println("<br>" + p.getFName());
                        HttpSession session = request.getSession();
                        session.setAttribute("UserObject", p);
                        response.sendRedirect(response.encodeURL("MyAccount.jsp"));

                        /* RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeURL("index.jsp"));
                         dispatcher.forward(request, response);*/
                    }
                }//end of else text fields empty or not           
            }//end of try
            catch (Exception ex) {
                out.println(ex.getMessage());
                out.println("<script type='text/javascript' > alert('Something wrong is happened,please try again  ');history.back();</script>");

            }//end of catch
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
