<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
         <c:if test="${command != null}">
            <title><c:out value='${command}' /></title>
         </c:if>
         <c:if test="${command == null}">
             <title>Users Store Application</title>
          </c:if>
</head>
<body>
    <center>
        <h1>Users Management</h1>
        <h2>
            <a href="controller?action=createUser">Add New User</a>
            &nbsp;&nbsp;&nbsp;
            <a href="controller?action=listUsers">List All Users</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Password</th>
                <th>E-mail</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td>
                        <a href="controller?action=updateUser&id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="controller?action=deleteUser&id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>