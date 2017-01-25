<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Best Items</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${pageContext.request.contextPath}/resources/LoginPage/LoginPageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.employee == null}">
            <c:redirect url="employee.jsp"></c:redirect>
        </c:if>
        <div class="center-wrapper">
            <form action="GoToHomeEmployeeServlet">
                <input type="submit" value="Home"></input>
            </form>
            <br></br>
            <form action="LogOutEmployeeServlet">
                <input type="submit" value="Log Out"></input>
            </form>
            <br></br>
            <c:forEach var="i" items="${itemranks}">
                Item Name: <span>${i.itemname}</span>
                Revenue: <span>${i.unitprice}</span>
                <br></br>
            </c:forEach>
        </div>
    </body>
</html>
