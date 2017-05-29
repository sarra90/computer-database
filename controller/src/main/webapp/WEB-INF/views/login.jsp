
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link href="./static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="./static/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="./static/css/main.css" rel="stylesheet" media="screen">
</head>

<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<form class="form-horizontal" id="loginform" name='login'
						action="<c:url value='/login' />" method='POST'>
						<c:if test="${not empty error}">
							<div class="alert alert-danger">
								<p>${error}</p>
							</div>
						</c:if>
						<c:if test="${not empty message}">
							<div class="alert alert-success">
								<p>${message}</p>
							</div>
						</c:if>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								class="form-control" id="username" name="username"
								placeholder="Enter Username" required>
						</div>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Enter Password" required>
						</div>
						<div class="etc-login-form">
						<p>new user? <a href="${pageContext.request.contextPath}/register">create new account</a></p>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="form-actions">
						<div class="col-md-3 col-md-offset-3 ">
							<input type="submit"
								class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
