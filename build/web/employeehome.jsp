<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${pageContext.request.contextPath}/resources/ScrollablePageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.employee == null}">
            <c:redirect url="employee.jsp"></c:redirect>
        </c:if>
        <div class="center-wrapper">
            <form action="LogOutEmployeeServlet">
                <input type="submit" value="Log Out"></input>
            </form>
            <br></br>
            <form action="EmployeeAdvertisementServlet">
                <input type="submit" value="Advertisement"></input>
            </form>
            <br></br>
            <form action="ViewTransactionServlet">
                <input type="submit" value="View Transaction"></input>
            </form>
            <br></br>
            <form action="CustomerServlet">
                <input type="submit" value="Customer"></input>
            </form>
            <br></br>
            <form action="MailingListServlet">
                <input type="submit" value="Mailing List"></input>
            </form>
            <br></br>
            <form action="CustomerGroupServlet">
                <input type="submit" value="Customer Groups"></input>
            </form>
            <br></br>
            <form action="AccountHistoryServlet">
                <input type="submit" value="Account History"></input>
            </form>
            <br></br>
            <form action="BestSellerServlet">
                <input type="submit" value="Best Selling Items"></input>
            </form>
            <br></br>
        </div>
    </body>
</html>
