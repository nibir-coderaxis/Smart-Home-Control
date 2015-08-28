/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class LogOut extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String str = request.getParameter("SignOut");
        try {
            if(str.equals("Sign Out"))
            {
            request.getSession().invalidate();
            
             try{
                    setFiles();
              }
              catch (Exception ex) {
                         System.out.print(ex);
                }
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request,response);
            }
            
        } finally {
            out.close();
        }
    }
    
    
     public void setFiles() throws Exception
    {
            String file1 = "C:/WebApplication/web/user1_Light1.txt";
            String file2 = "C:/WebApplication/web/user1_Light2.txt";
            String file3 = "C:/WebApplication/web/user1_Light3.txt";
            
            
               FileWriter filewriter1 = new FileWriter(file1, false);
               filewriter1.write("null");
               filewriter1.close();
            
         
               FileWriter filewriter2 = new FileWriter(file2, false); 
               filewriter2.write("null");
               filewriter2.close();
            
           
               FileWriter filewriter3 = new FileWriter(file3, false);
               filewriter3.write("null");
               filewriter3.close();
               
               
            String file_1 = "C:/WebApplication/web/user1_Light1_Home_status.txt";
            String file_2 = "C:/WebApplication/web/user1_Light2_Home_status.txt";
            String file_3 = "C:/WebApplication/web/user1_Light3_Home_status.txt";     
            
            
            
            
               FileWriter filewriter_1 = new FileWriter(file_1, false);
               filewriter_1.write("No Status!");
               filewriter_1.close();
            
         
               FileWriter filewriter_2 = new FileWriter(file_2, false); 
               filewriter_2.write("No Status!");
               filewriter_2.close();
            
           
               FileWriter filewriter_3 = new FileWriter(file_3, false);
               filewriter_3.write("No Status!");
               filewriter_3.close();
               
            
            
            
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
