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

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* Run insert query in MySQL */
        String query
                = "INSERT INTO user (`FirstName`, `LastName`, `Address`,"
                + "`City`, `State`, `ZipCode`, `Telephone`, `Email`, `Password`, Preferences, Rating) VALUES ('"
                + request.getParameter("firstname")
                + "', '"
                + request.getParameter("lastname")
                + "', '"
                + request.getParameter("address")
                + "', '"
                + request.getParameter("city")
                + "', '"
                + request.getParameter("state")
                + "', '"
                + request.getParameter("zipcode")
                + "', '"
                + request.getParameter("telephone")
                + "', '"
                + request.getParameter("email")
                + "', '"
                + request.getParameter("password")
                + "', '', 'never');";
        ResultSet rs;
        boolean valid = fbConnector.excuteUpdate(query);
        if (valid) {
            System.out.println("Register success");
            query = "SELECT * FROM User WHERE email='"
                    + request.getParameter("email")
                    + "'";
            rs = fbConnector.excuteQuery(query);
            try {
                if (rs.next()) {
                    query = "INSERT INTO UserPages(Owner, PostCount) VALUES ("
                            + rs.getInt("userid")
                            + ", 0);";
                    valid = fbConnector.excuteUpdate(query);
                    if(valid){
                        System.out.println("Paged added");
                    }
                }
            } catch (SQLException ex) {

            }
            try {
                fbConnector.close();
            } catch (SQLException e) {

            }
        }
        /* Forward user back to login page */
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
