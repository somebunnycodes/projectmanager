<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
   	<title>Project Manager</title>
</head>
<body>
	
	<div class="d-flex p-3">
		<div class="flex-fill">
			<h1>Project Manager</h1>
			<h5>A place for teams to manage projects</h5>
		</div>
	</div>
	
    <div class="container-fluid">
        <div class="row mt-3">
            <div class="col-4 offset-1">        
                <div class="card">
                    <div class="card-header">
                    	<h2>Register</h2>
                    </div>
                    <div class="card-body">
                        <form:form action="/register" method="POST" modelAttribute="newUser">
                            <div class="mb-3 form-group">
                                <form:label class="form-label" path="firstName">First Name:</form:label>
                                <form:input class="form-control" path="firstName"/>
                                <form:errors path="firstName"/>
                            </div>
                            <div class="mb-3 form-group">
                                <form:label class="form-label" path="lastName">Last Name:</form:label>
                                <form:input class="form-control" path="lastName"/>
                                <form:errors path="lastName"/>
                            </div>
                            <div class="mb-3 form-group">
                                <form:label class="form-label" path="email">Email:</form:label>
                                <form:input class="form-control" path="email"/>
                                <form:errors path="email"/>
                            </div>
                            <div class="mb-3 form-group">
                                <form:label class="form-label" path="password">Password:</form:label>
                                <form:password class="form-control" path="password"/>
                                <form:errors path="password"/>
                            </div>
                            <div class="mb-3 form-group">
                                <form:label class="form-label" path="confirm">Confirm Password:</form:label>
                                <form:password class="form-control" path="confirm"/>
                                <form:errors path="confirm"/>
                            </div>
                            <div class="mb-3">
                                <input class="btn btn-primary" type="submit" value="Register">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="col-4 offset-2">        
                <div class="card">
                    <div class="card-header">
                        <h2>Login</h2>
                    </div>
                    <div class="card-body">
                        <form:form action="/login" method="POST" modelAttribute="newLogin">
                            <div class="mb-3 form-group">
                                <form:label class="form-label" path="email">Email:</form:label>
                                <form:input class="form-control" path="email"/>
                                <form:errors path="email"/>
                            </div>
                            <div class="mb-3 form-group">
                                <form:label class="form-label" path="password">Password:</form:label>
                                <form:password class="form-control" path="password"/>
                                <form:errors path="password"/>
                            </div>
                            <div class="mb-3">
                                <input class="btn btn-primary" type="submit" value="Login">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>