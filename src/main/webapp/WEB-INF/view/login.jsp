<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap -->
        <link rel="stylesheet" href="${contextPath}/resources/css/vendors/bootstrap.min.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/common-form.css" />
        <link rel="stylesheet" href="${contextPath}/resources/css/signin.css" />

        <title>Welcome to Notes</title>
    </head>
    <body class="text-center">
        <form class="form-signin" method="post" action="${contextPath}/login">
            <h1 class="h3 mb-3 font-weight-normal"><spring:message code="signin.h1" /></h1>

            <label for="inputUsername" class="sr-only"><spring:message code="label.username" /></label>
            <spring:message var="usernamePlaceholder" code="input.username.placeholder" />
            <input type="text" name="username" id="inputUsername" class="form-control" placeholder="${usernamePlaceholder}" required autofocus>

            <label for="inputPassword" class="sr-only"><spring:message code="label.password" /></label>
            <spring:message var="passwordPlaceholder" code="input.password.placeholder" />
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="${passwordPlaceholder}" required>

            <spring:message var="incorrect" code="Incorrect" />
            <div class="${error != null ? 'alert error alert-danger' : ''}">${error != null ? incorrect : ''}</div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="signin.button.submit" /></button>

            <spring:message var="logoutMessage" code="loggedOutSuccess" />
            <div class="${message != null ? 'alert alert-success mt-10' : ''}">${message != null ? logoutMessage : ''}</div>

            <a class="mt-5 mb-3 text-muted" href="${contextPath}/registration"><spring:message code="signin.button.registration" /></a>
        </form>
    </body>
</html>