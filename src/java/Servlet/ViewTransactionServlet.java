package Servlet;

import Beans.Employee;
import Beans.Sales;
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

public class ViewTransactionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        String query = "SELECT S.transactionid, S.date, S.advertisementid, S.units, S.accountnumber FROM Sales S"
                + " WHERE S.employeeid='"
                + employee.getSsn()
                + "' ORDER BY `Date` DESC";
        System.out.println("Query : " + query);
        ResultSet rs = fbConnector.excuteQuery(query);
        LinkedList<Sales> sales = new LinkedList<Sales>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    Sales sale = new Sales();
                    sale.setTransactionid(rs.getInt("transactionid"));
                    sale.setDate(rs.getString("date"));
                    sale.setAdvertisementid(rs.getInt("advertisementid"));
                    sale.setUnits(rs.getInt("units"));
                    sale.setAccountnumber(rs.getString("accountnumber"));
                    sales.add(sale);
                }
            } catch (SQLException ex) {

            } finally {
                try {
                    fbConnector.close();
                } catch (SQLException e) {
                    
                }
            }
        }
        if(sales.isEmpty()){
            request.getSession().setAttribute("sales", null);
        }
        else{
            request.getSession().setAttribute("sales", sales.toArray(new Sales[sales.size()]));
        }
        request.getRequestDispatcher("saleslist.jsp").forward(request, response);
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
