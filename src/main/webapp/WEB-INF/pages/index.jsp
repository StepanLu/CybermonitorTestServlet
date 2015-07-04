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

<form action="${pageContext.request.contextPath}/getHistory" method="post">
  Object id: <input type="text" name="object_id"><br>
  From: <input type="text" name="from"><br>
  To: <input type="text" name="to"><br>
  <input type="submit" name="submit"><br>
</form>

</body>
</html>
