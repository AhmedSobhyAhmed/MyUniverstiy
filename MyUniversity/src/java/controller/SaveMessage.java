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
import models.ModelOfFaculityAdmin;
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class SaveMessage extends HttpServlet {

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

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String MessageDate = sdf1.format(new Date());

                String RecieverID = request.getParameter("RecieverID");
                //  String SenderID = request.getParameter("SenderID");
                String Message = request.getParameter("Message");

                boolean isEveryThingOk = true;
                int RecieverIDInteger = 0, SenderIDInteger = 0;
                if (RecieverID == null || RecieverID.isEmpty()
                        || Message == null || Message.isEmpty()) {
                    isEveryThingOk = false;
                    out.println("<script type='text/javascript' > alert('Please fill all fields^_^ ');history.back();</script>");

                } else {

                    try {
                        RecieverIDInteger = Integer.parseInt(RecieverID);
                        // SenderIDInteger=Integer.parseInt(SenderID);

                    } catch (Exception ex) {
                        out.println("<script type='text/javascript' > alert('Code must be an integer please ^_^ ');history.back();</script>");

                    }

                    if (isEveryThingOk) {

                        int rowAffected;
                        try {

                            rowAffected = p.SaveMessage(p.getCode(), Message, RecieverIDInteger, MessageDate);
                            if (rowAffected > 0) {
                               // out.println("<script type='text/javascript' > alert('Mesage was Sending successfully ^_^ ');history.back();</script>");
                                 response.sendRedirect("Chat2.jsp?to="+RecieverID);

                            } else {
                                out.println("<script type='text/javascript' > alert('Message Sending was  Failed ^_^ ');history.back();</script>");
                            }

                        } catch (Exception ex) {
                            System.err.println("SaveMessage Servlet Error:" + ex.getMessage());
                            out.println("SendMessage Servlet Error:" + ex.getMessage());
                            out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                        }
                    }//end of isEveryThingOK or not

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
