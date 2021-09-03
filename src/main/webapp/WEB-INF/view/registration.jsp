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
    <title><fmt:message key="reg.title"/></title>
</head>
<body class="p-0 m-0">
<%--<img src="${contextPath}/public/img/desc.jpg">--%>
<%--      th:style="'background: url(../img/desk.jpg) no-repeat center center fixed; background-size: 100% 100%;'">--%>

<div>
    <nav class="navbar navbar-dark bg-dark navbar-expand-lg">
        <div class="container-fluid">
            <div>
                <a href="?lang=en" class="btn btn-outline-success">English</a>
                <a href="?lang=uk" class="btn btn-outline-success">Українська</a>
            </div>
            <form action="${contextPath}/logout" name="logout" method="POST">
                <a class="btn btn-outline-success" href="${contextPath}/login"> <fmt:message key="back.button"> Back </fmt:message></a>
                <button type="submit" class="btn btn-outline-success" name="logout" value="logout">
                <fmt:message key="login.out">Log out</fmt:message></button>
            </form>
                   </div>
    </nav>
</div>

<div class="card text-center container  mt-5 w-50 shadow-lg">
    <h2 class="card-header"><fmt:message key="reg.header.title">Create your account</fmt:message></h2>
    <div class="col py-3">
        <form action="#" method="POST" class="register-form" id="register-form" action="${contextPath}/registration">
            <div class="form-group">
                <label for="firstName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                <br> <input type="text"
                            name="firstName"
                            id="firstName"
                            value="${requestScope.first_name}"
                            placeholder="<fmt:message key="reg.first.name.placeholder">firstName</fmt:message>"
                            required>
                <c:if test="${requestScope.first_name_error != null}">
                    <div class="alert alert-danger" role="alert"><c:out value="${requestScope.first_name_error}"/></div>
                </c:if>
            </div>
            <div class="form-group">
                <br> <label for="lastName"><i class="zmdi zmdi-account-o"></i></label>
                <input type="text"
                       name="lastName"
                       id="lastName"
                       value="${requestScope.last_name}"
                       placeholder="<fmt:message key="reg.last.name.placeholder">lastName</fmt:message>" required>
                <c:if test="${requestScope.errors.get('last_name_error') != null}">
                    <div class="alert alert-danger" role="alert"><fmt:message
                            key="${requestScope.errors.get('last_name_error')}"/></div>
                </c:if>
            </div>
            <c:if test="${requestScope.userAlreadyExistsMessage == true}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert"><fmt:message
                        key="valid.login.email.not.unique"/>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <div class="form-group">
                <label for="email"><i class="zmdi zmdi-email"></i></label>
                <br> <input type="email"
                            name="email"
                            id="email"
                            value="${requestScope.email}"
                            placeholder="<fmt:message key="reg.email.placeholder">email</fmt:message>" required>
                <c:if test="${requestScope.errors.get('email_error') != null}">
                    <div class="alert alert-danger" role="alert"><fmt:message
                            key="${requestScope.errors.get('email_error')}"/></div>
                </c:if>
            </div>
            <div class="form-group">
                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                <br> <input type="password"
                            name="password"
                            id="password"
                            value="${requestScope.password}"
                            placeholder="<fmt:message key="reg.password.placeholder">password</fmt:message>" required>
                <c:if test="${requestScope.errors.get('password_error') != null}">
                    <div class="alert alert-danger" role="alert"><fmt:message
                            key="${requestScope.errors.get('password_error')}"/></div>
                </c:if>
            </div>


            <div class="form-group">
                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                <br> <input type="password"
                            name="matchingPassword"
                            id="matchingPassword"
                            value="${requestScope.matchingPassword}"
                            placeholder="<fmt:message key="reg.confirm.password.placeholder">matchingPassword</fmt:message>"
                            required>
                <c:if test="${requestScope.errors.get('matching_password_error') != null}">
                    <div class="alert alert-danger" role="alert"><fmt:message
                            key="${requestScope.errors.get('matching_password_error')}"/></div>
                </c:if>
            </div>
            <div>
                <br>
                <button class="btn btn-outline-success" type="submit">
                <fmt:message key="login.sign.up">Sign up</fmt:message></button>
            </div>
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
