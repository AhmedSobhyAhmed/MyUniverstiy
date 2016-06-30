package controller;

import Database.MyDatabase;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;

//@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)

public class UploadFileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Subject_Code = 0;
        try {
            Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
        } catch (Exception ex) {

            System.out.println("" + ex.getMessage());
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        InputStream inputStream = null;

        Random rand = new Random();
        int n = rand.nextInt(9999) + 1;
        String idTemp = (String.valueOf(n));

        String title = (request.getParameter("title"));
        String File_Type = (request.getParameter("File_Type"));
        Part filePart = request.getPart("file_uploaded");

        /* if (filePart != null) 
         {
         System.out.println(filePart.getName());
         System.out.println(filePart.getSize());
         System.out.println(filePart.getContentType());

         inputStream = filePart.getInputStream();
         }*/
        if (title == null) {
            out.println("<script type='text/javascript' > alert('Please choose file  ^_^ ');history.back();</script>");
        }
        if (File_Type == null) {
            out.println("<script type='text/javascript' > alert('Please choose file  ^_^ ');history.back();</script>");

        }

        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            inputStream = filePart.getInputStream();

            try {
                Connection connection = null;
                MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
                connection = ViewCourseDatabaseObject.connect_to_DB();
                out.println(Subject_Code);

                String sql = "INSERT INTO matrerial (ID, Filename, file,Type,SubjectID) values (?, ?, ?,?,?)";
                out.println(sql);

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, idTemp);
                statement.setString(2, title);

                if (inputStream != null) {
                    statement.setBinaryStream(3, inputStream, (int) filePart.getSize());
                }
                statement.setString(4, File_Type);
                int id = 2;
                statement.setInt(5, Subject_Code);

                int row = statement.executeUpdate();
                if (row > 0) {
                        //out.println("File uploaded!!!");
                    //   out.println("<script type='text/javascript' > alert('File uploaded Successfully  ^_^ ');history.back();</script>");
                    response.sendRedirect("MyAccount.jsp?page=upload_form&Subject_Code=" + Subject_Code + "");

                       // conn.close();
                       // RequestDispatcher rs = request.getRequestDispatcher(".jsp");
                    // rs.include(request, response);
                } else {
                    out.println("<script type='text/javascript' > alert('File not uploaded   ^_^ ');history.back();</script>");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            out.println("<script type='text/javascript' > alert('Please choose file  ^_^ ');history.back();</script>");

        }
    }

}
