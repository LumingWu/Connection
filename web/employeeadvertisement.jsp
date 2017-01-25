<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advertisement</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${pageContext.request.contextPath}/resources/ScrollablePageStyles.css" rel="styleSheet" type="text/css"/>
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
            <c:forEach var="a" items="${advertisements}">
                <form action="DeleteAdvertisementServlet">
                    <input type="hidden" name="advertisementid" value="${a.advertisementid}"></input>
                    Advertisement ID: <span>${a.advertisementid}</span>
                    Item Name: <span>${a.itemname}</span>
                    Company: <span>${a.company}</span>
                    Employee ID: <span>${a.employeeid}</span>
                    Content: <span>${a.content}</span>
                    Type: <span>${a.type}</span>
                    Unit Price: <span>${a.unitprice}</span>
                    Units: <span>${a.units}</span>
                    <input type="submit" value="Delete"></input>
                </form>
                <br></br>
            </c:forEach>
            <form action="AddAdvertisementServlet">
                Item Name: <input type="text" name="itemname"></input>
                Company: <input type="text" name="company"></input>
                <input type="hidden" name="employeeid" value="${sessionScope.employee.ssn}">
                Content: <input type="text" name="content"></input>
                Type: <input type="text" name="type"></input>
                Unit Price: <input type="text" name="unitprice"></input>
                Units: <input type="text" name="units"></input>
                <input type="submit" value="Add"></input>
            </form>
        </div>
    </body>
</html>
