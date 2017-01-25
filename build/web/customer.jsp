<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customers</title>
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
            <c:forEach var="c" items="${customers}">
                <form action="EditCustomerServlet">
                    <input type="hidden" name="userid" value="${c.userid}"></input>
                    <span>UserID ${c.userid}</span>
                    First Name 
                    <input type="text" name="firstname" value="${c.firstname}"></input>
                    Last Name 
                    <input type="text" name="lastname" value="${c.lastname}"></input>
                    Address 
                    <input type="text" name="address" value="${c.address}"></input>
                    City 
                    <input type="text" name="city" value="${c.city}"></input>
                    State 
                    <input type="text" name="state" value="${c.state}"></input>
                    Zip Code 
                    <input type="text" name="zipcode" value="${c.zipcode}"></input>
                    Telephone 
                    <input type="text" name="telephone" value="${c.telephone}"></input>
                    Email 
                    <input type="text" name="email" value="${c.email}"></input>
                    Password 
                    <input type="text" name="password" value="${c.password}"></input>
                    Preferences 
                    <input type="text" name="preferences" value="${c.preferences}"></input>
                    Rating 
                    <input type="text" name="rating" value="${c.rating}"></input>
                    <input type="checkbox" name="delete">Delete</input>
                    <input type="submit" value="Change"></input>
                </form>
                <br></br>
            </c:forEach>
        </div>
    </body>
</html>
