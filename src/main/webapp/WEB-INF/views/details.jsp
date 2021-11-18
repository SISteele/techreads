<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <body>
        <h1>Title: <c:out value="${book.title}" /></h1>
        <h2>Author: <c:out value="${book.author}" /></h2>
        <h2>Rating: <c:out value="${book.rating}" /></h2>
        <img src="<c:out value="${book.coverArt}" />" />
    </body>

</html>