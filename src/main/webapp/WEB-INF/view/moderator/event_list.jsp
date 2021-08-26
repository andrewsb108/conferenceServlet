<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <title>Events list</title>
</head>

<body>
<%--        th:style="'background: url(../img/desk.jpg) no-repeat center center fixed; background-size: 100% 100%;'">--%>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container-fluid">
        <div>
            <a href="?lang=en" class="btn btn-outline-success">English</a>
            <a href="?lang=uk" class="btn btn-outline-success">Українська</a>
        </div>
        <div sec:authorize="hasAuthority('MODERATOR')">
            <a class="btn btn-outline-success" href="${contextPath}/index">
               <fmt:message key="index.event.list">Index page</fmt:message></a>
        </div>
        <form action="${contextPath}/logout" name="logout" method="POST">
            <button type="submit" class="btn btn-outline-success" name="logout" value="logout">
                    <fmt:message key="login.out">Log out</fmt:message></button>
        </form>
    </div>
</nav>

<div class="container w-75 bg-light">
    <div class="row my-5">
        <div class="col py-3 shadow rounded">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"> <fmt:message key="event.crete.title">Title</fmt:message></th>
                    <th scope="col"> <fmt:message key="event.scheduled.date">ScheduledDate</fmt:message></th>
                    <th scope="col"> <fmt:message key="event.edit.button">Edits</fmt:message></th>
                    <th scope="col"> <fmt:message key="event.delete.button">Delete</fmt:message></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                <c:forEach var="event" items="${requestScope.events}">
                    <td><span>${event.title}</span></td>
                    <td><span>${event.scheduledDate}</span></td>

                    <td>
                        <a class="btn btn-outline-success" href="${contextPath}/event/edit/{id}(id=${event.getId})}">
                           <fmt:message key="event.edit.button">Edit</fmt:message></a>
                    </td>
                    <td>
                        <a class="btn btn-outline-danger" href="${contextPath}/event/delete/{id}(id=${event.getId})}">
                           <fmt:message key="event.delete.button">Delete</fmt:message></a>
                    </td>
                </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>