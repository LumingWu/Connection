<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FriendPage</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${pageContext.request.contextPath}/resources/ScrollablePageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.user == null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <div class="center-wrapper">
            <br></br>
            <form action="GoToHomeServlet">
                <input type="submit" value="Home"></input>
            </form>
            <br></br>
            <form action="LogOutServlet">
                <input type="submit" value="Log Out"></input>
            </form>
            <br></br>
            <div class="message-box" id="message-box">
                <c:forEach var="p" items="${friendposts}">
                    ${p.date}
                    <p>
                        ${p.content}
                    </p>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
