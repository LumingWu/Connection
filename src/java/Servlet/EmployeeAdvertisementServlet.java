package Servlet;

import Beans.Advertisements;
import Beans.Employee;
import FbConnector.fbConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeAdvertisementServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        String query = "SELECT * FROM Advertisements WHERE employeeid='"
                + employee.getSsn()
                + "'";
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        LinkedList<Advertisements> ads = new LinkedList<Advertisements>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    Advertisements ad = new Advertisements();
                    ad.setAdvertisementid(rs.getInt("advertisementid"));
                    ad.setItemname(rs.getString("itemname"));
                    ad.setCompany(rs.getString("company"));
                    ad.setEmployeeid(rs.getString("employeeid"));
                    ad.setContent(rs.getString("content"));
                    ad.setType(rs.getString("type"));
                    ad.setUnitprice(rs.getDouble("unitprice"));
                    ad.setUnits(rs.getInt("units"));
                    ads.add(ad);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeAdvertisementServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException e) {

                }
            }
        }
        if(ads.isEmpty()){
            request.getSession().setAttribute("advertisements", null);
        }
        else{
            request.getSession().setAttribute("advertisements", ads.toArray(new Advertisements[ads.size()]));
        }
        request.getRequestDispatcher("employeeadvertisement.jsp").forward(request, response);
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
