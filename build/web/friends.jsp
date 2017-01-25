<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friends</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${pageContext.request.contextPath}/resources/ScrollablePageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.user == null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <div class="center-wrapper">
            <form action="GoToHomeServlet">
                <input type="submit" value="Home"></input>
            </form>
            <br></br>
            <form action="LogOutServlet">
                <input type="submit" value="Log Out"></input>
            </form>
            <br></br>
            <c:forEach var="friend" items="${friends}" varStatus="loop">
                <div class="friend-box">
                    <br></br>
                    Name: ${friend.firstname} ${friend.lastname}
                    <br></br>
                    <form action="GoToFriendPersonalPageServlet">
                        <input type="hidden" name="friendindex" value="${loop.index}"></input>
                        <input type="submit" value="Page"></input>
                    </form>
                    <br></br>
                    <form action="GoToMessagePageServlet">
                        <input type="hidden" name="friendindex" value="${loop.index}"></input>
                        <input type="submit" value="Message"></input>
                    </form>
                    <br></br>
                    <hr></hr>
                </div>
            </c:forEach>
            <form action="AddFriendServlet">
                <input type="text" name="friendemail" placeholder="Friend's Email"></input>
                <input type="submit"></input>
            </form>
        </div>
    </body>
</html>
