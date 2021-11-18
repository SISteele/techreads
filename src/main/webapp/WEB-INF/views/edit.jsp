<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <body>
        <form:form method="post" modelAttribute="bookForm" action="${pageContext.request.contextPath}/books">
        <label>Title: <form:input path="title" type="text"/></label><br>
        <label>Author :<form:input path="author" type="text" /></label><br>
        <label>Rating: <form:input path="rating" type="text" /></label><br>
        <label>Cover Art URL: <form:input path="coverArt" type="text" /></label><br>
        <form:input path="id" type="hidden" />
        <button type="submit">Edit</button>
    </form:form>

    </body>


</html>