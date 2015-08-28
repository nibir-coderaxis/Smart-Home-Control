/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class BtnClickRes extends HttpServlet {

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
        String L1On = request.getParameter("Light1On");
        String L2On = request.getParameter("Light2On");
        String L3On = request.getParameter("Light3On");
        
        String L1Off = request.getParameter("Light1Off");
        String L2Off = request.getParameter("Light2Off");
        String L3Off = request.getParameter("Light3Off");
        try {
            /* TODO output your page here. You may use following sample code. */
           
            String file1 = "C:/WebApplication/web/user1_Light1.txt";
            String file2 = "C:/WebApplication/web/user1_Light2.txt";
            String file3 = "C:/WebApplication/web/user1_Light3.txt";
            
            
            if(L1On!=null){
               FileWriter filewriter1 = new FileWriter(file1, false);
               filewriter1.write(L1On);
               filewriter1.close();
            }
            if(L2On!=null){
               FileWriter filewriter2 = new FileWriter(file2, false); 
               filewriter2.write(L2On);
               filewriter2.close();
            }
            if(L3On!=null){
               FileWriter filewriter3 = new FileWriter(file3, false);
               filewriter3.write(L3On);
               filewriter3.close();
            }
            
            if(L1Off!=null){
               FileWriter filewriter1 = new FileWriter(file1, false);
               filewriter1.write(L1Off);
               filewriter1.close();
            }
            if(L2Off!=null){
               FileWriter filewriter2 = new FileWriter(file2, false); 
               filewriter2.write(L2Off);
               filewriter2.close();
            }
            if(L3Off!=null){
               FileWriter filewriter3 = new FileWriter(file3, false); 
               filewriter3.write(L3Off);
               filewriter3.close();
            }
            
          /*  out.println(L1On);
            out.println(L2On);
            out.println(L3On);
            out.println("<br/> <br/>");
            out.println(L1Off);
            out.println(L2Off);
            out.println(L3Off);
           */
            
            RequestDispatcher rd = request.getRequestDispatcher("user1.jsp");
            rd.include(request, response);

        } finally {
            out.close();
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
