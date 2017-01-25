<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${pageContext.request.contextPath}/resources/ScrollablePageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.user == null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <input type="hidden" id="username" value="${sessionScope.user.firstname} ${sessionScope.user.lastname}"></input>
        <div class="center-wrapper">
            <form action="GoToHomeServlet">
                <input type="submit" value="Home"></input>
            </form>
            <br></br>
            <form action="LogOutServlet">
                <input type="submit" value="Log Out"></input>
            </form>
            <br></br>
            <div class="message-box" id="message-box">
                <c:forEach var="m" items="${messages}">
                    ${m.sender == friend.userid?friend.firstname:user.firstname}
                    <p>
                        ${m.content}
                    </p>
                    <br></br>
                </c:forEach>
            </div>
            <textarea class="send-message-box" id="message"></textarea>
            <button onclick="sendMessage()">Send</button>
            </div>
        </div>
        <script>
            var messagebox = document.getElementById("message-box");
            var message = document.getElementById("message");
            var username = document.getElementById("username").value;
            var request;
            function sendMessage(){
                request = new XMLHttpRequest();
                request.open("GET", "SendMessageServlet?message=" + message.value, true);
                request.onreadystatechange = updateMessage;
                request.send(null);
            }
            function updateMessage(){
                if(request.readyState === 4 && request.status === 200){
                    var update = document.createElement("p");
                    update.textContent = request.responseText;
                    if(update.textContent !== ""){
                        messagebox.appendChild(document.createTextNode(username));
                        messagebox.appendChild(update);
                        messagebox.appendChild(document.createElement("br"));
                    }
                }
            }
        </script>
    </body>
</html>
