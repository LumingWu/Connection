package Servlet;

import Beans.User;
import Beans.UserPosts;
import FbConnector.fbConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToPostListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        LinkedList<UserPosts> temp_posts = new LinkedList<UserPosts>();
        String query = "SELECT P2.postid, P2.date, P2.content FROM UserPages P1, UserPosts P2 WHERE P1.`owner`="
                + user.getUserid()
                + " AND P1.pageid = P2.postson ORDER BY `Date` DESC";
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        if (rs != null) {
            try {
                while (rs.next()) {
                    UserPosts post = new UserPosts();
                    post.setPostid(rs.getInt("postid"));
                    post.setDate(rs.getString("date"));
                    System.out.println("Postid: " + post.getPostid() + " Post date: " + post.getDate());
                    post.setContent(rs.getString("content"));
                    temp_posts.add(post);
                }
            } catch (SQLException ex) {

            }
            try {
                fbConnector.close();
            } catch (SQLException e) {

            }
        }
        if(temp_posts.isEmpty()){
            request.getSession().setAttribute("posts", null);
        }
        else{
            request.getSession().setAttribute("posts", temp_posts.toArray(new UserPosts[temp_posts.size()]));
        }
        request.getRequestDispatcher("personal.jsp").forward(request, response);
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
