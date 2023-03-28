<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        .normal{color: green;}
        .exceeded{color: red;}
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
<a href="meals?&action=save">Add Meal</a>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${meal.excess ? 'exceeded' : 'normal'}">
                <td><%=TimeUtil.toString(meal.getDate())%></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?&action=update">Update</a></td>
                <td><a href="meals?&action=delete">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>