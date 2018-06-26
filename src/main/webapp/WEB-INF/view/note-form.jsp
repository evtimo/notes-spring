<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>Note form</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/resources/css/vendors/bootstrap.min.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/note-form.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/signup.css" />
</head>
<body>

    <nav class="navbar navbar-expand navbar-dark bg-dark-blue">
        <a class="navbar-brand" href="${contextPath}/"><spring:message code="appname" /></a>

        <div class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
            <spring:message var="save" code="button.save" />
            <input form="note-form" type="submit" value="${save}" class="btn btn-success">
            <a class="btn btn-outline-danger ml-10" type="button" href="${contextPath}/"><spring:message code="button.back" /></a>
        </div>
    </nav>

    <main role="main" class="text-white">
        <form:form id="note-form" action="saveNote" modelAttribute="note" method="post" cssClass="form-signin">

            <form:hidden path="id" />

            <label for="inputTitle" class="form-text"><spring:message code="label.title" /></label>
            <spring:bind path="title">
                <spring:message var="titlePlaceholder" code="input.title.placeholder" />
                <form:input id="inputTitle" cssClass="form-control bg-dark-blue" type="text" path="title" placeholder="${titlePlaceholder}" autofocus="true" maxlength="20" />
                <form:errors path="title" cssClass="alert alert-danger"/>
            </spring:bind>
            <label for="inputText" class="form-text"><spring:message code="label.text" /></label>
            <spring:bind path="text">
                <spring:message var="textPlaceholder" code="input.textarea.placeholder" />
                <form:textarea id="inputText" cssClass="form-control bg-dark-blue" type="text" path="text" placeholder="${textPlaceholder}" rows="15" />
                <form:errors path="text" cssClass="alert alert-danger"/>
            </spring:bind>

        </form:form>
    </main>

</body>
</html>
