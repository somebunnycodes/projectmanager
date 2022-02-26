<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Project Manager</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> 
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="d-flex p-3">
		<div class="flex-fill">
			<h1>Create a Project</h1>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="offset-1 col-8">
				<form:form action="/projects" modelAttribute="project" class="form" method="post">
					<form:input type="hidden" path="teamLeader" value="${user.id}"/>
					
					<div class="row my-2">
						<form:label for="title" path="title" class="col-2 col-form-label">Project Title:</form:label>
						<div class="col-10">
							<form:input type="text" path="title" class="form-control"/>
						 	<form:errors path="title" class="error"/>
						</div>
					</div>
					
					<div class="row my-2">
						<form:label for="description" path="description" class="col-2 col-form-label">Project Description:</form:label>
						<div class="col-10">
							<form:textarea path="description" class="form-control"/>
							<form:errors path="description" class="error"/>
						</div>
					</div>
					
					<div class="row my-2">
						<form:label for="dueDate" path="dueDate" class="col-2 col-form-label">Due Date:</form:label>
						<div class="col-10">
							<form:input type="date" path="dueDate" class="form-control"/>
							<form:errors path="dueDate" class="error"/>
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
	</div>
</body>
</html>