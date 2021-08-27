<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    <title><fmt:message key="login.title"/></title>
</head>

<body class="p-0 m-0">
<%--      th:style="'background: url(../img/desk.jpg) no-repeat center center fixed; background-size: 100% 100%;'">--%>
<%--<img src="${contextPath}/public/img/desc.jpg">--%>
<div>
    <nav class="navbar navbar-dark bg-dark navbar-expand-lg">
        <div class="container-fluid">
            <div>
                <a href="?lang=en" class="btn btn-outline-success">English</a>
                <a href="?lang=uk" class="btn btn-outline-success">Українська</a>
            </div>
            <div class="col-2">
                <a class="btn btn-outline-success" type="button" href="${contextPath}/registration">
                    <fmt:message key="login.sign.up">sign in</fmt:message> </a>
            </div>
        </div>
    </nav>
</div>

<div class="card text-center container  mt-5 w-50 shadow-lg">
    <div class="card-header"> <fmt:message key="login.title"> SIGN IN </fmt:message></div>
    <c:if test="${requestScope.login_error != null}">
        <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.login_error}"/></div>
    </c:if>

    <div class="card-body">
        <form method="POST" class="register-form" id="login-form" action="${contextPath}/login">
            <div class="form-group">
                <label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                <input type="text" name="email" id="email"
                       placeholder="<fmt:message key="login.email.placeholder">email</fmt:message>">
            </div>
            <div class="form-group">
                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                <br> <input type="password" name="password" id="password"
                            placeholder="<fmt:message key="login.password.placeholder">password</fmt:message>">
            </div>
            <button class="btn btn-lg btn-info" type="submit" ><fmt:message key="login.login">Log In </fmt:message>
            </button>
        </form>
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
