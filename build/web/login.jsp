<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <!-- CSS -->
        <link href="${pageContext.request.contextPath}/resources/LoginPage/LoginPageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.user != null}">
            <jsp:forward page="home.jsp" />  
        </c:if>
        <div class="center-wrapper">
            <form action="LogInServlet" method="post">
                <span>Email</span>
                <br></br>
                <input type="text" name="email"></input>
                <br></br>
                <span>Password</span>
                <br></br>
                <input type="text" name="password"></input>
                <br></br>
                <input type="submit" value="Log In"></input>
                <br></br>
            </form>
            <form action="GoToRegisterServlet" method="get">
                <input type="submit" value="Register"></input>
            </form>
        </div>
    </body>
</html>
