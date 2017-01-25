package Servlet;

import Beans.User;
import FbConnector.fbConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFriendServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String friendemail = request.getParameter("friendemail");
        String query = "SELECT * FROM User WHERE email='" + friendemail + "'";
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        try {
            if (rs.next()) {
                int userid2 = rs.getInt("userid");
                query = "INSERT INTO Friend (UserId1, UserId2) VALUES ('"
                        + ((User) request.getSession().getAttribute("user")).getUserid()
                        + "', '"
                        + userid2
                        + "');";
                System.out.println("Query : " + query);
                boolean transaction = fbConnector.excuteUpdate(query);
                if (transaction) {
                    System.out.println("Friend added");
                } else {
                    System.out.println("Friend not added");
                }
            }
        } catch (SQLException ex) {

        } finally {
            try {
                fbConnector.close();
            } catch (SQLException e) {

            }
        }
        /* Forward user back to the same page as refresh page */
        request.getRequestDispatcher("GoToFriendListServlet").forward(request, response);
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
