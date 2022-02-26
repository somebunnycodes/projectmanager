<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Project Details</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="d-flex p-3">
		<div class="flex-fill">
			<h1>Project Details</h1>
		</div>
		<div>
			<a href="/dashboard">Back to Dashboard</a>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row">
			<div class="offset-1 col-8">
				<div class="row my-2">
					<div class="col-2">Project:</div>
					<div class="col-10">
						<c:out value="${project.title}" />
					</div>
				</div>
				
				<div class="row my-2">
					<div class="col-2">Description:</div>
					<div class="col-10">
						<c:out value="${project.description}" />
					</div>
				</div>
				
				<div class="row my-2">
					<div class="col-2">Due Date:</div>
					<div class="col-10">
						<c:out value="${project.dueDate}" />
					</div>
				</div>

				<div class="row my-2">
					<div class="col-12">
						<a href="/projects/${project.id}/tasks">See tasks!</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>