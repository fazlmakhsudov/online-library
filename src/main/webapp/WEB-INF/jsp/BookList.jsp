<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
     <c:if test="${command != null}">
        <title><c:out value='${command}' /></title>
     </c:if>
     <c:if test="${command != null}">
         <title>Books Store Application</title>
      </c:if>
</head>
<body>
    <center>
        <h1>Books Management</h1>
        <h2>
            <a href="controller?action=createBook">Add New Book</a>
            &nbsp;&nbsp;&nbsp;
            <a href="controller?action=listBooks">List All Books</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td>
                        <a href="controller?action=updateBook&id=<c:out value='${book.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="controller?action=deleteBook&id=<c:out value='${book.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>