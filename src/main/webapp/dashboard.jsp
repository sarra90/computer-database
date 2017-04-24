	<!DOCTYPE html>
<%@page import="com.excilys.model.Computer"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List , com.excilys.model.Company"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> Application -
				Computer Database </a>
		</div>
	</header>
	<%
		List<Computer> list = (ArrayList<Computer>) request.getAttribute("list");
		Integer numberOfComputers = (Integer) request.getAttribute("numberOfComputers");
		Integer noOfPages = (Integer) request.getAttribute("noOfPages");
		
	%>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${ numberOfComputers }</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer.jsp">AddComputer</a>
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers  -->
				<tbody id="results">
					<c:forEach var="element" items="${ list }">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="editComputer.html" onclick="">${element.getName()}</a></td>
							<td>${ element.getIntroduced() }</td>
							<td>${ element.getDisconstinued() }</td>
							<td>${ element.getManufacturer().getName() }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<%--For displaying Previous link except for the 1st page --%>


		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${currentPage != 1}">
					<li><a href="dashboard?page=${currentPage - 1}&recordsPerPage=${recordsPerPage}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span></a>
                    </li>
				</c:if>
				<c:forEach begin="1" end="${noOfPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<li><a class="btn disabled" href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="dashboard?page=${i}&recordsPerPage=${recordsPerPage}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages}">
				<li><a href="dashboard?page=${currentPage + 1}&recordsPerPage=${recordsPerPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span></a>
                </li>
			</c:if> 
			</ul>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default"><a href="dashboard?recordsPerPage=50">50</a></button>
				<button type="button" class="btn btn-default"><a href="dashboard?recordsPerPage=100">100</a></button>
				<button type="button" class="btn btn-default"><a href="dashboard?recordsPerPage=150">150</a></button>
			</div>
		</div>

	</footer>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>

</body>
</html>