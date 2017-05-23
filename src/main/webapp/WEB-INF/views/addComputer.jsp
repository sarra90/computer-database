<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application -
				Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form:form method="post" action="${pageContext.request.contextPath}/addcomputer" commandName = "computerDto">
						<fieldset>
							<div class="form-group">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label for="computerName">Computer name</label> <form:input
									type="text" class="form-control" id="name" path="name"
									placeholder="Computer name"/>
									<form:errors path="name" class="control-label" />
							</div>
							</div>
							<div class="form-group">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label for="introduced">Introduced date</label> <form:input
									type="date" class="form-control" id="introduced" path="introduced"
									placeholder="Introduced date"/>
									<form:errors path="introduced" class="control-label" />
							
								</div>
							</div>
							
							<div class="form-group">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label for="disconstinued">Discontinued date</label> <form:input
									type="date" class="form-control" id="disconstinued" path="disconstinued"
									placeholder="Discontinued date"/>
									<form:errors path="disconstinued" class="control-label" />
									
							</div>
							</div>
							
							<div class="form-group">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label for="companyId">Company</label> <form:select
									class="form-control" id="companyId" path="idCompany">
									<form:option value="-1" label="--choose a company--" selected="selected"></form:option>
									<c:forEach var="element" items="${ list }">
										<form:option value="${element.getId()}">${element.getName()}</form:option>
									</c:forEach>
								</form:select>
								</div>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>