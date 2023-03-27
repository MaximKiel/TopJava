<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
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
        <c:forEach items="${allMeals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr>
                <c:if test="${!meal.excess}">
                    <td style="color:#008000"><%=meal.getDate().toLocalDate()%> <%=meal.getDate().toLocalTime()%></td>
                    <td style="color:#008000"><%=meal.getDescription()%></td>
                    <td style="color:#008000"><%=meal.getCalories()%></td>
                </c:if>
                <c:if test="${meal.excess}">
                    <td style="color:#FF0000"><%=meal.getDate().toLocalDate()%> <%=meal.getDate().toLocalTime()%></td>
                    <td style="color:#FF0000"><%=meal.getDescription()%></td>
                    <td style="color:#FF0000"><%=meal.getCalories()%></td>
                </c:if>
                <td><a href="meals?&action=update">Update</a></td>
                <td><a href="meals?&action=delete">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>