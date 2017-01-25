<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <!-- CSS -->
        <link href="${pageContext.request.contextPath}/resources/RegisterPage/RegisterPageStyles.css" rel="styleSheet" type="text/css"/>
    </head>
    <body>
        <div class="center-wrapper">
            <form action="RegisterServlet" method="post">
                <span>First Name:</span>
                <input type="text" name="firstname"></input>
                <span>Last Name:</span>
                <input type="text" name="lastname"></input>
                <br></br>
                <span>Address:</span>
                <input type="text" name="address"></input>
                <span>City:</span>
                <input type="text" name="city"></input>
                <span>State:</span>
                <input type="text" name="state"></input>
                <span>Zip Code:</span>
                <input type="text" name="zipcode"></input>
                <br></br>
                <span>Telephone:</span>
                <input type="text" name="telephone"></input>
                <br></br>
                <span>Email:</span>
                <input type="text" name="email"></input>
                <span>Password:</span>
                <input type="text" name="password"></input>
                <br></br>
                <input type="submit" value="Register"></input>
            </form>
        </div>
    </body>
</html>
