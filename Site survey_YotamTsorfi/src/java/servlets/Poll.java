package servlets;

import dbconnectivity.DBException;
import dbconnectivity.PollManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Poll servlet - request: client vote , response: poll result 
 * @author: Yotam Tsorfi
 */

//-------------------------------------------------
public class Poll extends HttpServlet {

    protected PollManager pollManager; //responsible to DB connection
    
    
    //---------------------------------------------
    /**
     * Poll constructor - init PollManager:  
     * Table Name: ex3
     * User Name: root
     * Password: 
     */
    public Poll(){
        pollManager = new PollManager("ex3","root","");
    }
    //---------------------------------------------
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Poll Result</title>");            
            out.println("</head>");
            out.println("<body>");        
           
            
            printBody(out, request);   
            
            
            
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }
//---------------------------------------------
     /**
     * print html body - print poll result or error message.
     *
     * @param out html PrintWriter
     * @param request servlet request
     */
    protected void printBody(PrintWriter out, HttpServletRequest request)
    {
        try{
            //connect to server
            pollManager.connectToDB();
            
            //insert user vote to the DB
            pollManager.insertVoteToDB(request.getParameter("pollname"), request.getParameter("userchoice")); 
 
            out.println(setHeader("Thank you for voting!"));
            
            //print poll result
            out.println("<p>" + pollManager.getPollResult(request.getParameter("pollname")));
            
            //close connection with the server
            pollManager.close();
        }
        catch(DBException exp){
            pollManager.closeNoThrow();
            out.println(setHeader(exp.getMessage()));
        } 
    }
//---------------------------------------------
    /**
     *
     * @param str header string
     * @return string surrounded with <h1></h1>
     */
    protected String setHeader(String str)
    {
        return "<h1>" + str + "</h1>";
    }
  //---------------------------------------------  
    
    
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
        return "receive vote and show the poll result";
    }// </editor-fold>
}
