package Servlet;

import Beans.Employee;
import FbConnector.fbConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerGroupServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        String query = "SELECT U.userid, G.groupname FROM (SELECT DISTINCT A.userid"
                + " FROM Sales S, Accountnumber A WHERE S.employeeid='"
                + employee.getSsn()
                + "' AND S.accountnumber=A.accountnumber) U, Groups G, Participated P"
                + " WHERE U.userid=P.user AND P.groups=G.groupid";
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        LinkedList<String[]> pairs = new LinkedList<String[]>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    String[] pair = new String[2];
                    pair[0] = "" + rs.getInt("userid");
                    pair[1] = rs.getString("groupname");
                    System.out.println("Pair: " + pair[0] + " " + pair[1]);
                    pairs.add(pair);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException e) {

                }
            }
        }
        if (pairs.isEmpty()) {
            request.getSession().setAttribute("pairs", null);
        } else {
            request.getSession().setAttribute("pairs", pairs.toArray(new String[pairs.size()][2]));
        }
        request.getRequestDispatcher("customergroup.jsp").forward(request, response);
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
