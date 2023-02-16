<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .tb {
            border-collapse: collapse
        }

        .tb th, .tb td {
            padding: 5px;
            border: solid 1px black
        }

        .red {
            color: red;
        }

        .green {
            color: green;
        }
    </style>
</head>
<body>

<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>


<table class="tb">
    <tr>
        <th>Date and Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${meals}">

        <c:if test="${meal.isExcess()}">
            <tr class="red">
        </c:if>
        <c:if test="${!meal.isExcess()}">
            <tr class="green">
        </c:if>
        <td>${meal.getDateTime().format(formatter)}</td>
        <td>${meal.getDescription()}</td>
        <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>