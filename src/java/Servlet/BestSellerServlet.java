package Servlet;

import Beans.Advertisements;
import Beans.Employee;
import Beans.Sales;
import FbConnector.fbConnector;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BestSellerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        String query = "SELECT I.itemname, SUM(I.unitprice * S.units) AS revenue FROM (SELECT A.advertisementid, A.itemname, A.unitprice"
                + " FROM Advertisements A WHERE A.employeeid='"
                + employee.getSsn()
                + "') I, Sales S WHERE I.advertisementid=S.advertisementid GROUP BY I.advertisementid"
                + " ORDER BY SUM(I.unitprice * S.units) DESC";
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        LinkedList<Advertisements> ads = new LinkedList<Advertisements>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    Advertisements a = new Advertisements();
                    a.setItemname(rs.getString("itemname"));
                    a.setUnitprice(rs.getDouble("revenue"));
                    ads.add(a);
                }
            } catch (SQLException ex) {

            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException e) {

                }
            }
        }
        if (ads.isEmpty()) {
            request.getSession().setAttribute("itemranks", null);
        } else {
            request.getSession().setAttribute("itemranks", ads.toArray(new Advertisements[ads.size()]));
        }
        request.getRequestDispatcher("itemrank.jsp").forward(request, response);
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
