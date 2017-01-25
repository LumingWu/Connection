<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${pageContext.request.contextPath}/resources/LoginPage/LoginPageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.employee != null}">
            <jsp:forward page="employeehome.jsp" />  
        </c:if>
        <div class="center-wrapper">
            <form action="LogInEmployeeServlet" method="post">
                <span>SSN</span>
                <br></br>
                <input type="text" name="ssn"></input>
                <br></br>
                <span>Password</span>
                <br></br>
                <input type="text" name="password"></input>
                <br></br>
                <input type="submit" value="Log In"></input>
                <br></br>
            </form>
        </div>
    </body>
</html>
