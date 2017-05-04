<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List , com.excilys.model.Company"%>
<%@ page pageEncoding="UTF-8"%>

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
	<%
		List<Company> list = (ArrayList<Company>) request.getAttribute("list");
		String erreur = (String) request.getAttribute("erreur");
		//String erreurIntroduced = (String) request.getAttribute("erreurIntroduced");
		//String erreurDiscontinued = (String) request.getAttribute("erreurDiscontinued");
	%>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="addcomputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="name" name="name"
									placeholder="Computer name">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced" name="introduced"
									placeholder="Introduced date">
								<c:if test = "${erreurIntroduced != null}">      
									<div class="alert alert-danger" role="alert">${ erreurIntroduced }</div>
								</c:if>
							</div>
							
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued" name="discontinued"
									placeholder="Discontinued date">
									<c:if test = "${erreurDiscontinued != null}">      
  										<div class="alert alert-danger" role="alert">${ erreurDiscontinued }</div>  
									</c:if> 
							</div>
							
							<span class="erreur">${ erreur }</span>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="-1" label="--choose a company--" selected="selected"></option>
									<c:forEach var="element" items="${ list }">
										<option value="${element.getId()}">${element.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>