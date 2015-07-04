<%--
  Created by IntelliJ IDEA.
  User: StepLuch
  Date: 30.06.15
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
  <script>
    function dateDiff(start_time) {
      var now = new Date();
      return now.getTime() - start_time;
    }

    function current_time() {
      var now = new Date();
      return now.getTime();
    }

    function print_error(text) {
      console.error(text)
    }

    $( document ).ready(function() {
      console.error(dateDiff(${time_start_transfer}))
      print_error()
    });

  </script>
  <title></title>
</head>
<body>

<h2>Track info on object # ${obj_id}</h2>

<b>Start time:</b> ${time_start_transfer}<br>
<b> Curent time:</b> <span id="current_time"></span> <br><br><br>

<b>Transfer time:</b> <span id="load_time"> </span><br>
<b> Array length:</b> ${track.length()} <br><br><br>
</body>
</html>