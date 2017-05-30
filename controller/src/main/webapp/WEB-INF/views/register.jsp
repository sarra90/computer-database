<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
					<a class="navbar-brand"><spring:message code="app.title"/></a>
				</div>
	</header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Create new account</h1>

                    <form action="register" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="username">user name</label>
                                <input type="text" class="form-control" id="name" value="${username}" name= "name" placeholder="user name">
                            </div>
                            <div class="form-group">
                                <label for="password">password</label>
                                <input type="password" class="form-control" id="password" value="${password}" name="password" placeholder="password ">
                            </div>
                            <div class="form-group">
                                <label for="password">confirm password</label>
                                <input type="password" class="form-control" id="password" value="${password}" name="confirmpassword" placeholder="confirm password">
                            </div>
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="register" class="btn btn-primary">
                            or
                            <a href="login" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>