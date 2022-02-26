<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<title>Project Manager Dashboard</title>
</head>
<body>

	<div class="d-flex p-3">
		<div class="me-5">
			<h1>Welcome, ${user.firstName}</h1>
		</div>
		<div class="ms-5 d-flex align-items-center">
			<a href="/logout">logout</a>
		</div>
	</div>

	<div class="d-flex px-3 align-items-end">
		<div class="flex-fill">
			<h5>All Projects</h5>
		</div>
		<div class="text-end">
			<a href="/projects/new" class="btn btn-primary">+ NEW PROJECT</a>
		</div>
	</div>

	<div class="w-100 px-3">

		<table class="table">
			<thead>
				<tr>
					<td>PROJECT</td>
					<td>TEAM LEAD</td>
					<td>DUE DATE</td>
					<td>ACTIONS</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${nonUserProjects}">
					<tr>
						<td>
							<a href="/projects/${project.id}"><c:out value="${project.title}" /></a>
						</td>
						<td><c:out value="${project.teamLeader.firstName}" /></td>
						<td><c:out value="${project.dueDate}" /></td>
						<td><a href="/projects/${project.id}/join">Join team</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="w-100 px-3">
		<h5>Your Projects</h5>
		<table class="table">
			<thead>
				<tr>
					<td>PROJECT</td>
					<td>TEAM LEAD</td>
					<td>DUE DATE</td>
					<td>ACTIONS</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="project" items="${userProjects}">
					<tr>
						<td><a href="/projects/${project.id}"><c:out
									value="${project.title}" /></a></td>
						<td><c:out value="${project.teamLeader.firstName}" /></td>
						<td><c:out value="${project.dueDate}" /></td>
						<td>
							<c:choose>
								<c:when test="${user.id == project.teamLeader.id}">
									<a href="/projects/edit/${project.id}">edit</a>
								</c:when>
								<c:otherwise>
									<a href="/projects/${project.id}/leave">Leave team</a> 
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>