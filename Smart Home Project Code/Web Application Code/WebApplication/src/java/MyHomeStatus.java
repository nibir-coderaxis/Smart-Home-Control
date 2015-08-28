/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class MyHomeStatus extends HttpServlet {

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
        String Light = request.getParameter("Light");
        try {
            /* TODO output your page here. You may use following sample code. */
            String[] parts = Light.split("#");
            String L1 = parts[0]; 
            String L2 = parts[1]; 
            String L3 = parts[2];
            

            String file1 = "C:/WebApplication/web/user1_Light1_Home_status.txt";
            String file2 = "C:/WebApplication/web/user1_Light2_Home_status.txt";
            String file3 = "C:/WebApplication/web/user1_Light3_Home_status.txt";
            
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            BufferedReader reader3 = new BufferedReader(new FileReader(file3));
            
            
            String LR1;
            String LR2;
            String LR3;

            LR1= reader1.readLine();
            LR2= reader2.readLine();
            LR3= reader3.readLine();
            
            if(!L1.equals(LR1)){
               FileWriter filewriter1 = new FileWriter(file1, false);
               filewriter1.write(L1);
               filewriter1.close();
            }
            if(!L2.equals(LR2)){
               FileWriter filewriter2 = new FileWriter(file2, false); 
               filewriter2.write(L2);
               filewriter2.close();
            }
            if(!L3.equals(LR3)){
               FileWriter filewriter3 = new FileWriter(file3, false);
               filewriter3.write(L3);
               filewriter3.close();
            }
            
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
