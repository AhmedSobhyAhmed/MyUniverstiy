
<%@page import="Database.MyDatabase"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View File</title>
    </head>
    <body>
        
        <%  
            String File_id=(request.getParameter("id"));
            String File_Type=(request.getParameter("File_Type"));
            
            Blob file = null;
            byte[ ] fileData = null ;
            byte[] fileBytes = null;
           String name;
           String type;
           String MyRealFileType="";
            try
            {    
                Connection connection = null;
                MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
                connection = ViewCourseDatabaseObject.connect_to_DB();
            
                String sqlString = "SELECT * FROM matrerial WHERE ID = '"+File_id+"'";
                Statement myStatement = connection.createStatement();
                
                ResultSet rs=myStatement.executeQuery(sqlString);
                
                if (rs.next()) 
                {
                  fileBytes=  rs.getBytes("file");
                    file = rs.getBlob("file");
                    fileData = file.getBytes(1,(int)file.length());
                    name=rs.getString("Filename");
                    type=rs.getString("Type");
                } else 
                {
                    out.println("file not found!");
                    return;
                }
                
                if(File_Type.equals("Word")){
                    response.setContentType( "application/msword" );
                    MyRealFileType="docx";
                    out.println("Word");

                }
               // response.setContentType("application/force-download");
                else if(File_Type.equals("Pdf")){
                      out.println("PDF");
                      MyRealFileType="pdf";

                response.setContentType("application/pdf");
            }
                else if (File_Type.equals("PPt"))
{
    MyRealFileType="ppt";
  response.setContentType("application/ppt"); 
  out.println("PPT");

}
                else if (File_Type.equals("Text"))
{
    MyRealFileType="txt";
response.setContentType( "text/plain" );

out.println("Text");
}
                
                  //response.setContentType("application/ppt");
                  String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", name+"."+MyRealFileType);
				response.setHeader(headerKey, headerValue);
                //response.setHeader("Content-Disposition", "badtr");
                response.setHeader("cache-control", "must-revalidate");
                response.setContentLength(fileData.length);
              
               ServletOutputStream outs = response.getOutputStream();
                outs.write(fileBytes);
                
                outs.flush();
                
            } catch (SQLException ex) {Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);} 
        %>
        
        
        <div style="position: relative">
            <div style="position: fixed ; bottom: 0 ; width:100% ; text-align:center ">
               
            </div>
        </div>
        
    </body>
</html>