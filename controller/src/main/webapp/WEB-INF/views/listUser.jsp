
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="./static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="./static/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="./static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"><spring:message
					code="app.title" /></a>

			<div class="collapse navbar-collapse navbar-right col-lg-2 col-md-3">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/listUser">List user</a>
			</div>

			<div>
				<form id="logout"
					action="${pageContext.request.contextPath}/login?logout="
					method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<div class="collapse navbar-collapse navbar-right col-lg-2 col-md-3">
					<a class="navbar-brand" href="login?logout">Logout</a>
				</div>
			</c:if>

			<div class="collapse navbar-collapse navbar-right col-lg-2 col-md-3">
				<a class="navbar-brand" href="?language=en">English</a>|<a
					class="navbar-brand" href="?language=fr">French</a>
			</div>

		</div>

	</header>

	<section id="main">


		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>user name</th>
						<th>role</th>
						<th>activated</th>

					</tr>
				</thead>
				<tbody id="results">
					<c:forEach var="element" items="${ list}">
						<tr>
							<td>${ element.getUsername() }</td>
							<td><c:forEach var="elt" items="${element.getRoles()}">${ elt.getRole()}
							</c:forEach></td>
							<td>
								<c:if test="${ element.isEnabled() eq true}">
								<input type="checkbox" name="validate" checked="checked"/>
								</c:if>
								
								<c:if test="${ element.isEnabled() eq false}">
								<input type="checkbox" name="validate"/>
								</c:if>
							${ element.isEnabled() }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>