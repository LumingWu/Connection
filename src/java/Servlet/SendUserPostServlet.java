package Servlet;

import Beans.User;
import FbConnector.fbConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendUserPostServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String post_content = request.getParameter("post-content");
        User user = (User) request.getSession().getAttribute("user");
        String query = "SELECT * FROM UserPages WHERE `owner`="
                + user.getUserid();
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        if (rs != null) {
            try {
                if (rs.next()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    int pageid = rs.getInt("pageid");
                    query = "INSERT INTO UserPosts (Date, Content, CommentCount, Likes, PostBy, PostsOn) VALUES ('"
                            + sdf.format(new Date())
                            + "', '"
                            + post_content
                            + "', 0, 0, "
                            + user.getUserid()
                            + ", "
                            + pageid
                            + ");";
                    System.out.println("Query : " + query);
                    boolean transaction = fbConnector.excuteUpdate(query);
                    if (transaction) {
                        System.out.println("Post added");
                    } else {
                        System.out.println("Post not added");
                    }
                }
            } catch (SQLException ex) {

            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException e) {

                }
            }
        }
        request.getRequestDispatcher("GoToPostListServlet").forward(request, response);
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
