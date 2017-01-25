package Servlet;

import Beans.Employee;
import FbConnector.fbConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInEmployeeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("ssn").equals("") || request.getParameter("password").equals("")) {
            response.sendRedirect("employee.jsp");
        } else {
            try {
                String query = "SELECT * FROM Employee WHERE ssn='"
                        + request.getParameter("ssn")
                        + "' AND `password`='"
                        + request.getParameter("password")
                        + "'";
                System.out.println("Query : " + query);
                ResultSet rs = fbConnector.excuteQuery(query);
                if (rs != null && rs.next()) {
                    Employee employee = new Employee();
                    employee.setSsn(rs.getString("ssn"));
                    employee.setPassword(rs.getString("password"));
                    request.getSession().setAttribute("employee", employee);
                }
                else{
                    request.getSession().setAttribute("employee", null);
                }
            } catch (SQLException ex) {

            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException ex) {
                }
            }
            request.getRequestDispatcher("employeehome.jsp").forward(request, response);
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
