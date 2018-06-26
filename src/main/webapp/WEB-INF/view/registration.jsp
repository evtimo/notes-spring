<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Registration</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/resources/css/vendors/bootstrap.min.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/common-form.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/signup.css" />

</head>
<body class="text-center">
    <form:form modelAttribute="user" method="post" cssClass="form-signin">

        <form:hidden path="id" />

        <h2 class="h3 mb-3 font-weight-normal"><spring:message code="signin.button.registration" /></h2>

        <label for="inputUsername" class="sr-only"><spring:message code="label.username" /></label>
        <spring:bind path="username">
            <spring:message var="inputUsername" code="input.username.placeholder" />
            <form:input id="inputUsername" cssClass="form-control" type="text" path="username" placeholder="${inputUsername}" autofocus="true" />
            <form:errors path="username" cssClass="alert alert-danger"/>
        </spring:bind>

        <label for="inputPassword" class="sr-only"><spring:message code="label.password" /></label>
        <spring:bind path="password">
            <spring:message var="inputPassword" code="input.password.placeholder" />
            <form:input id="inputPassword" cssClass="form-control" type="password" path="password" placeholder="${inputPassword}" />
            <form:errors path="password" cssClass="alert alert-danger"/>
        </spring:bind>

        <label for="confirmPassword" class="sr-only"><spring:message code="label.confirm" /></label>
        <spring:bind path="confirmPassword">
            <spring:message var="confirmPassword" code="input.confirm.placeholder" />
            <form:input id="confirmPassword" cssClass="form-control" type="password" path="confirmPassword" placeholder="${confirmPassword}" />
            <form:errors path="confirmPassword" cssClass="alert alert-danger"/>
        </spring:bind>

        <button type="submit" class="btn btn-lg btn-primary btn-block"><spring:message code="button.submit" /></button>

        <a type="button" class="btn btn-lg btn-danger btn-block" href="${contextPath}/"><spring:message code="button.back" /></a>

    </form:form>

</body>
</html>
