/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
public class CreateTranscript extends HttpServlet {

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
        // try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        HttpSession session = request.getSession();
        if (session.getAttribute("UserObject") != null) {
            MyPerson p = (MyPerson) session.getAttribute("UserObject");
            try {
                this.CreateTranscript(p);//,out);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            try {
                this.DownloadTranscript(response, p);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            RequestDispatcher RD = request.getRequestDispatcher(response.encodeURL("index.jsp"));
            RD.forward(request, response);
        }

        //}
    }

    private void CreateTranscript(MyPerson p) {//,PrintWriter out){
        Document document = new Document();

        String name = p.getFName() + p.getLName();

        try {
            if (p.getUserType() == 6) {

                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\" + name + ".pdf"));
                document.open();

                PdfPTable table = new PdfPTable(8); // 8 columns.
                table.setWidthPercentage(100); //Width 100%
                table.setSpacingBefore(10f); //Space before table
                table.setSpacingAfter(10f); //Space after table

                //Set Column widths
                float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f,1f,1f};
                table.setWidths(columnWidths);
                PdfPCell cell1 = new PdfPCell(new Paragraph("Subject Name"));
                cell1.setBorderColor(BaseColor.BLUE);
                cell1.setPaddingLeft(10);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell cell2 = new PdfPCell(new Paragraph("Subject Code"));
                cell2.setBorderColor(BaseColor.BLUE);
                cell2.setPaddingLeft(10);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell cell3 = new PdfPCell(new Paragraph("Written Grade"));
                cell3.setBorderColor(BaseColor.BLUE);
                cell3.setPaddingLeft(10);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell cell4 = new PdfPCell(new Paragraph("Midterm Grade"));
                cell4.setBorderColor(BaseColor.BLUE);
                cell4.setPaddingLeft(10);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell cell5 = new PdfPCell(new Paragraph("Final Exam Grade"));
                cell5.setBorderColor(BaseColor.BLUE);
                cell5.setPaddingLeft(10);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell cell6 = new PdfPCell(new Paragraph("Final Grade"));
                cell6.setBorderColor(BaseColor.BLUE);
                cell6.setPaddingLeft(10);
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell cell7 = new PdfPCell(new Paragraph("Term"));
                cell7.setBorderColor(BaseColor.BLUE);
                cell7.setPaddingLeft(10);
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PdfPCell cell8 = new PdfPCell(new Paragraph("Registration Date"));
                cell8.setBorderColor(BaseColor.BLUE);
                cell8.setPaddingLeft(10);
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);

                ModelOfStudent modelOfStudent = new ModelOfStudent();
                ResultSet rs = modelOfStudent.ViewMyCourses(p.getCode());

                //    if(!rs.next()){out.println("<font color='blue'>There is no Courses Untill Now ^_^ </font>");}
                while (rs.next()) {

                    table.addCell(rs.getString("SubjectName"));
                    table.addCell(rs.getString("ID"));
                    table.addCell(rs.getString("WritenGrade"));
                    table.addCell(rs.getString("MidtermGrade"));
                    table.addCell(rs.getString("FinalExamGrade"));
                    table.addCell(rs.getString("FinalGrade"));
                    table.addCell(rs.getString("Term"));
                    table.addCell(rs.getString("StudentRegisterSubjectDate"));

                    /* String SubjectRegisterDate=String.valueOf(rs.getDate("SubjectRegisterDate"));
                     table.addCell(SubjectRegisterDate);
                     */
                }

                //To avoid having the cell border and the content overlap, if you are having thick cell borders
                //cell1.setUserBorderPadding(true);
                //cell2.setUserBorderPadding(true);
                //cell3.setUserBorderPadding(true);
                document.add(new Paragraph("University: " + MyPerson.ReturnUniversityName(p.getCode())));
                document.add(new Paragraph("Faculity: " + MyPerson.ReturnFaculityName(p.getCode())));
                document.add(new Paragraph("Student Name: " + p.getFName() + " " + p.getMName() + " " + p.getLName()));
                document.add(new Paragraph("Level: " + MyPerson.ReturnLevelName(p.getCode())));
                document.add(new Paragraph("Department: " + MyPerson.ReturnDepartmentName(p.getCode())));

                document.add(table);
                System.out.println("<script type='text/javascript' > alert('Successfull Creating Transcript ^_^ ');history.back();</script>");

                //out.println("Successfull Creating Transcript ^_^");
                document.close();
                writer.close();
            }//end of if student
        } catch (Exception e) {
            System.out.println("<script type='text/javascript' > alert('Failed Creating Transcript ^_^ Error:" + e.getMessage() + "  ');history.back();</script>");

            // out.println(""+e.getMessage());
            e.printStackTrace();
        }
    }

    private void DownloadTranscript(HttpServletResponse response, MyPerson p) throws FileNotFoundException, IOException {
        try {
            String name = p.getFName() + p.getLName();

            response.setContentType("application/pdf");
            //set the header and also the Name by which user will be prompted to save
            response.setHeader("Content-Disposition", "attachment;filename=" + name + ".pdf");
            File file = new File("D:\\" + name + ".pdf");
            FileInputStream fileIn = new FileInputStream(file);
            ServletOutputStream out2 = response.getOutputStream();

            byte[] outputByte = new byte[4096];
//copy binary contect to output stream
            while (fileIn.read(outputByte, 0, 4096) != -1) {
                out2.write(outputByte, 0, 4096);
            }
            fileIn.close();
            out2.flush();
            out2.close();
            System.out.println("<script type='text/javascript' > alert('Successfull Downloading Transcript ^_^ ');history.back();</script>");

        } catch (Exception ex) {
            System.out.println("<script type='text/javascript' > alert('Failed Downloading Transcript ^_^ Error:" + ex.getMessage() + "  ');history.back();</script>");

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
