<%--
  Created by IntelliJ IDEA.
  User: StepLuch
  Date: 29.06.15
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Maven + Spring MVC Web Project Example</h1>

<h2>Message : ${message}</h2>
<h2>Counter : ${counter}</h2>


<form action="${pageContext.request.contextPath}/myservlet" method="post">
  <input type="submit" name="button1" value="Button 1" />
  <input type="submit" name="button2" value="Button 2" />
  <input type="submit" name="button3" value="Button 3" />
</form>
</body>
</html>
