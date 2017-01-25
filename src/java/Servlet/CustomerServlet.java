package Servlet;

import Beans.Employee;
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

public class CustomerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        String query = "SELECT DISTINCT U.userid, U.firstname, U.lastname, U.address, U.city"
                + ", U.state, U.zipcode, U.telephone, U.email, U.password, U.preferences, U.rating"
                + " FROM AccountNumber A, User U, Sales S WHERE S.employeeid='"
                + employee.getSsn()
                + "' AND S.accountnumber=A.accountnumber AND A.userid=U.userid";
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        LinkedList<User> customers = new LinkedList<User>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    User customer = new User();
                    customer.setUserid(rs.getInt("userid"));
                    customer.setAddress(rs.getString("address"));
                    customer.setCity(rs.getString("city"));
                    customer.setState(rs.getString("state"));
                    customer.setZipcode(rs.getString("zipcode"));
                    customer.setFirstname(rs.getString("firstname"));
                    customer.setLastname(rs.getString("lastname"));
                    customer.setTelephone(rs.getString("telephone"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPreferences(rs.getString("preferences"));
                    customer.setRating(rs.getString("rating"));
                    customers.add(customer);
                }
            } catch (SQLException ex) {

            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException e) {
                    
                }
            }
        }
        if(customers.isEmpty()){
            request.getSession().setAttribute("customers", null);
        }
        else{
            request.getSession().setAttribute("customers", customers.toArray(new User[customers.size()]));
        }
        request.getRequestDispatcher("customer.jsp").forward(request, response);
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
