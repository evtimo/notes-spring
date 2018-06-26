<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>Notes</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/resources/css/vendors/bootstrap.min.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/notes-list.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/ionicons.min.css" />
</head>
<body>

    <nav class="navbar navbar-expand navbar-dark bg-dark-blue">
        <a class="navbar-brand" href="${contextPath}/"><spring:message code="appname" /></a>

        <div class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
            <a type="button" class="btn btn-success" href="${contextPath}/showFormForAdd"><spring:message code="button.addNote" /></a>
            <c:url var="logoutUrl" value="/logout"/>
            <form class="form-inline" action="${logoutUrl}" method="post">
                <spring:message var="logout" code="button.logOut" />
                <input class="btn btn-outline-danger ml-10" type="submit" value="${logout}" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </nav>

    <main role="main">
        <div class="notes-columns card-columns d-inline-block p-3">
            <c:forEach var="note" items="${notes}">
                <div class="card text-center text-white bg-dark-blue mb-3" style="max-width: 18rem;">
                    <div class="card-header">${note.title}</div>
                    <div class="card-body">
                        <p class="card-text">${note.text.length() > 85 ? note.text.substring(0, 85).concat("...") : note.text}</p>
                    </div>
                    <div class="card-footer">
                        <div class="btn-group" role="group">
                            <c:url var="updateLink" value="/showFormForUpdate"><c:param name="id" value="${note.id}" /></c:url>
                            <c:url var="deleteLink" value="/deleteNote"><c:param name="id" value="${note.id}" /></c:url>

                            <a type="button" class="btn btn-success" href="${updateLink}"><spring:message code="button.updateNote" /></a>
                            <a type="button" class="btn btn-success" href="${deleteLink}"><spring:message code="button.deleteNote" /></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>

</body>
</html>