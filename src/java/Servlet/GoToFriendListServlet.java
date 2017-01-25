package Servlet;

import Beans.User;
import FbConnector.fbConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToFriendListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        LinkedList<User> temp_friends = new LinkedList<User>();
        /* Get all users that are friend of the current user */
        try {
            String query = "SELECT U2.userid, U2.firstname, U2.lastname "
                    + "FROM User U1, User U2, Friend F WHERE U1.userid="
                    + user.getUserid()
                    + " AND U1.userid = F.userid1 AND U2.userid = F.userid2"
                    + " UNION SELECT U2.userid, U2.firstname, U2.lastname "
                    + "FROM User U1, User U2, Friend F WHERE U1.userid="
                    + user.getUserid()
                    + " AND U1.userid = F.userid2 AND U2.userid = F.userid1";
            ResultSet rs = fbConnector.excuteQuery(query);
            if (rs != null) {
                while (rs.next()) {
                    User friend = new User();
                    friend.setUserid(rs.getInt("userid"));
                    friend.setFirstname(rs.getString("firstname"));
                    friend.setLastname(rs.getString("lastname"));
                    temp_friends.add(friend);
                }
            }
        } catch (SQLException ex) {

        } finally {
            try {
                fbConnector.close();
            } catch (SQLException e) {

            }
        }
        if (temp_friends.isEmpty()) {
            request.getSession().setAttribute("friends", null);
        } else {
            request.getSession().setAttribute("friends", temp_friends.toArray(new User[temp_friends.size()]));
        }
        request.getRequestDispatcher("friends.jsp").forward(request, response);
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
