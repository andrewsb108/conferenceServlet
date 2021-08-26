<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fml" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet"
          crossorigin="anonymous"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body class="p-0 m-0">
<%--      th:style="'background: url(../img/desk.jpg) no-repeat center center fixed; background-size: 100% 100%;'">--%>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container-fluid">
        <div>
            <a href="?lang=en" class="btn btn-outline-success">English</a>
            <a href="?lang=uk" class="btn btn-outline-success">Українська</a>
        </div>
        <form action="${contextPath}/logout" name="logout" method="POST">
            <a class="btn btn-outline-success" href="${contextPath}/event/all"> <fmt:message key="back.button">Back</fmt:message></a>
            <button type="submit" class="btn btn-outline-success" name="logout" value="logout">
                    <fmt:message key="login.out">Log out</fmt:message>
            </button>
        </form>
    </div>
</nav>
<div class="card text-center container  mt-5 w-50 shadow-lg">
    <h2 class="card-header"> <fmt:message key="event.edit">Edit event</fmt:message></h2>
    <div class="col py-3">
        <form method="POST" class="register-form" id="register-form"
              action="${contextPath}/event/update/{eventId}(eventId=${event.getId()})"
<%--              th:object="${event}">--%>
            <h5 class="text-danger" th:text="${error_message}"></h5>

            <div class="form-group">
                <br> <label for="title"><i class="zmdi zmdi-account-o"></i></label>
                <input type="text"
                       value="${requestScope.title}"
                       name="title"
                       id="title"
                       placeholder="<fmt:message key="event.crete.title">Title</fmt:message>" required>
<%--                <small class="text-danger" th:if="${#fields.hasErrors('title')}"--%>
<%--                       th:errors="*{title}">Validation error--%>
<%--                </small>--%>
            </div>

            <div class="form-group">
                <label for="scheduledDate"><i class="zmdi zmdi-email"></i></label>
                <br> <input type="datetime-local"
                            name="scheduledDate"
                            id="scheduledDate"
                            value="${requestScope.scheduledDate}"
                            placeholder="<fmt:message key="event.scheduled.date">ScheduledDate</fmt:message>" required>
<%--                <small class="text-danger" th:if="${#fields.hasErrors('scheduledDate')}"--%>
<%--                       th:errors="*{scheduledDate}">Validation error--%>
<%--                </small>--%>
            </div>
            <div>
                <br>
                <button class="btn btn-outline-success" type="submit"> <fmt:message key="event.edit.button">Edit</fmt:message></button>
            </div>
        </form>
    </div>
</div>

<div class="card text-center container  mt-5 w-50 shadow-lg">
    <h2 class="card-header" th:text="#{topic.topics}">Topics</h2>

    <div class="col">
        <table class="table">
            <tbody>
            <tr th:each="topicItem : ${topics}">
                <td th:text="${topicItem.getTitle()}"/>
                <td>
                    <a class="btn btn-outline-success" th:href="@{/event/edit/{eventId}/topic/{topicId}(eventId=${event.getId()},
                        topicId=${topicItem.id})}" th:text="#{topic.assign.speaker}">Choose</a>
                </td>
            </tr>
            <tr>
                <form method="POST" class="register-form"
                      action="${contextPath}/event/{id}/topic/add(id=${event.getId()})"
<%--                      th:object="${topic}">--%>
                    <td>
                        <div class="form-group">
                            <br> <label for="title"><i class="zmdi zmdi-account-o"></i></label>
                            <input type="text"
<%--                                   th:field="*{title}"--%>
                                   value="${requestScope.title}"
                                   placeholder="<fmt:message key="topic.title">Title</fmt:message>" required>
                        </div>
                    </td>
                    <td>
                        <br>
                        <button class="btn btn-outline-success" type="submit">
                            <fmt:message key="topic.create.title">Create</fmt:message>
                        </button>
                    </td>
                </form>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>