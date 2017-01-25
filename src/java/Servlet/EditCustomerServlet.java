package Servlet;

import FbConnector.fbConnector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCustomerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query;
        if(request.getParameter("delete") == null){
            query = "UPDATE User SET firstname='"
                    + request.getParameter("firstname")
                    + "', lastname='"
                    + request.getParameter("lastname")
                    + "', address='"
                    + request.getParameter("address")
                    + "', city='"
                    + request.getParameter("city")
                    + "', state='"
                    + request.getParameter("state")
                    + "', zipcode='"
                    + request.getParameter("zipcode")
                    + "', telephone='"
                    + request.getParameter("telephone")
                    + "', email='"
                    + request.getParameter("email")
                    + "', preferences='"
                    + request.getParameter("preferences")
                    + "', rating='"
                    + request.getParameter("rating")
                    + "' WHERE userid="
                    + request.getParameter("userid");
        }
        else{
            query = "DELETE FROM User WHERE userid=" + request.getParameter("userid");
        }
        System.out.println("Query : " + query);
        boolean rs = fbConnector.excuteUpdate(query);
        if(rs){
            System.out.println("Update success");
        }
        else{
            System.out.println("Update fail");
        }
        request.getRequestDispatcher("CustomerServlet").forward(request, response);
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
