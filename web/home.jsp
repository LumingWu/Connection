<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:if test="${sessionScope.user == null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <div class="center-wrapper">
            <form action="GoToFriendListServlet">
                <input type="submit" value="Friend List"></input>
            </form>
            <br></br>
            <form action="GoToPostListServlet">
                <input type="submit" value="Post List"></input>
            </form>
            <br></br>
            <form action="GoToGroupListServlet">
                <input type="submit" value="Group List"></input>
            </form>
            <br></br>
            <form action="LogOutServlet">
                <input type="submit" value="Log Out"></input>
            </form>
            <br></br>
            <span>Email: ${user.email}</span>
            <br></br>
            <span>First Name: ${user.firstname}</span>
            <br></br>
            <span>Last Name: ${user.lastname}</span>
            <br></br>
            <span>Address: ${user.address}</span>
            <br></br>
            <span>City: ${user.city}</span>
            <br></br>
            <span>Zip Code: ${user.zipcode}</span>
            <br></br>
            <span>Telephone: ${user.telephone}</span>
        </div>
    </body>
</html>
