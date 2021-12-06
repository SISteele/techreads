<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"rel="stylesheet">
    </head>
    <body>
        <h1>Books</h1>
        <a class="btn btn-light" href="<c:url value="/books/add" />">Add Books</a>

        <c:if test="${not empty books}">
            <c:forEach var="book" items="${books}">
                <div class="row">
                    <div class="col-sm">
                        <h3><c:out value="${book.title}" /></h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm">
                        <img src="<c:out value="${book.coverArt}" />" style="max-width:300px" />
                    </div>
                    <div class="col-sm">
                        <div class="btn-group">
                            <a class="btn" href="books/details/<c:out value="${book.id}"/>">Details</a>
                            <a class="btn" href="books/edit/<c:out value="${book.id}"/>">Edit</a>
                            <a class="btn btn-danger" href="books/remove/<c:out value="${book.id}"/>">Remove</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>

    </body>
</html>