<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Props Page</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> 
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="d-flex p-3">
		<div class="flex-fill">
			<h1>Project: <c:out value="${project.title}" /></h1>
			Project lead: <c:out value="${project.teamLeader.firstName}" />
		</div>
		<div>
			<a href="/dashboard">Back to Dashboard</a>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="offset-1 col-8">
				<form:form action="/projects/${project.id}/tasks" modelAttribute="task" class="form" method="post">
					<form:input type="hidden" path="createdBy" value="${user.id}"/>
					
					<div class="row my-2">
						<form:label for="description" path="description" class="col-2 col-form-label">Add a task ticket for this team:</form:label>
						<div class="col-10">
							<form:textarea path="description" class="form-control"/>
						 	<form:errors path="description" class="error"/>
						</div>
					</div>

					<div class="row my-2">
						<div class="col-12 d-flex justify-content-end">
							<a href="/dashboard" class="btn btn-primary">Cancel</a>
							<input type="submit" value="Submit" class="btn btn-primary ms-2"/>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<c:forEach var="task" items="${tasks}">
				<fmt:formatDate value="${task.createdAt}" pattern="h:mma MMM d" var="fmtdate" />
				<p>Added by <c:out value="${task.createdBy.firstName}" /> at <c:out value="${fmtdate}" />:</p>
				<p><c:out value="${task.description}" /></p>
			</c:forEach>
		</div>
	</div>
</body>
</html>