package Servlet;

import Beans.Message;
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

public class GoToMessagePageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int friend_index = Integer.parseInt(request.getParameter("friendindex"));
        User user = (User) request.getSession().getAttribute("user");
        User friend = ((User[]) request.getSession().getAttribute("friends"))[friend_index];
        request.getSession().setAttribute("friend", friend);
        /* Get the messages of between user and friend order by oldest to newest and use subject as friend name
        Friend name comes from the object friend above. */
        String query = "SELECT * FROM Messages WHERE (sender="
                + user.getUserid()
                + " AND receiver="
                + friend.getUserid()
                + ") OR (sender="
                + friend.getUserid()
                + " AND receiver="
                + user.getUserid()
                + ") ORDER BY `Date` ASC";
        System.out.println("Query : " + query);
        LinkedList<Message> temp_messages = new LinkedList<Message>();
        ResultSet rs = fbConnector.excuteQuery(query);
        try {
            if (rs != null) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setMessageid(rs.getInt("messageid"));
                    message.setDate(rs.getString("date"));
                    message.setSubject(rs.getString("subject"));
                    message.setContent(rs.getString("content"));
                    message.setSender(rs.getInt("sender"));
                    message.setReceiver(rs.getInt("receiver"));
                    temp_messages.add(message);
                }
                if (temp_messages.size() == 0) {
                    request.getSession().setAttribute("messages", null);
                } else {
                    request.getSession().setAttribute("messages", temp_messages.toArray(new Message[temp_messages.size()]));
                }
            }
        } catch (SQLException ex) {
            
        } finally {
            try {
                fbConnector.close();
            } catch (SQLException e) {

            }
        }
        request.getRequestDispatcher("message.jsp").forward(request, response);
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
