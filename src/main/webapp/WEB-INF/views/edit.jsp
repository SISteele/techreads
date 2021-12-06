<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"rel="stylesheet">
    </head>
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