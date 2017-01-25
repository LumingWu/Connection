package Servlet;

import Beans.User;
import FbConnector.fbConnector;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("email").isEmpty() || request.getParameter("password").isEmpty()) {
            response.sendRedirect("login.jsp");
        } else {
            boolean valid = true;
            try {
                String query = "SELECT * FROM User U WHERE U.email='"
                        + request.getParameter("email")
                        + "' AND U.password='"
                        + request.getParameter("password")
                        + "'";
                ResultSet rs = fbConnector.excuteQuery(query);
                if (rs != null && rs.next()) {
                    User u = new User();
                    u.setUserid(rs.getInt("userid"));
                    u.setAddress(rs.getString("address"));
                    u.setCity(rs.getString("city"));
                    u.setState(rs.getString("state"));
                    u.setZipcode(rs.getString("zipcode"));
                    u.setFirstname(rs.getString("firstname"));
                    u.setLastname(rs.getString("lastname"));
                    u.setTelephone(rs.getString("telephone"));
                    u.setEmail(rs.getString("email"));
                    u.setPreferences(rs.getString("preferences"));
                    u.setRating(rs.getString("rating"));
                    request.getSession().setAttribute("user", u);
                }
                else{
                    valid = false;
                }
            } catch (SQLException e) {
                valid = false;
            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException e) {
                    valid = false;
                }
            }
            if (valid) {
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
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
