<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"rel="stylesheet">
    </head>
    <body>
        <h1>Title: <c:out value="${book.title}" /></h1>
        <h2>Author: <c:out value="${book.author}" /></h2>
        <h2>Rating: <c:out value="${book.rating}" /></h2>
        <img src="<c:out value="${book.coverArt}" />" />
    </body>

</html>