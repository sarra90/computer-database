
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
<script type="text/javascript">
function deleteElements(){
	var checkboxes = document.getElementsByName('cb');
	var selected = [];
	for (var i=0; i<checkboxes.length;i++){
		if(checkboxes[i].checked){
			selected.push(checkboxes[i].value);
		}
	}
	var deleteform = document.getElementById('deleteForm');
	var inputselection = document.getElementById('selection');
	inputselection.value = selected;
	deleteform.submit();
}
</script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
					<a class="navbar-brand" href="dashboard"><spring:message code="app.title"/></a>
				
				<div class="collapse navbar-collapse navbar-right col-lg-2 col-md-3">
					<a class="navbar-brand" href="${pageContext.request.contextPath}/listUser">List user</a>
				</div>
				
				<div>
					<form id="logout" action="${pageContext.request.contextPath}/login?logout=" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
				
					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<div class="collapse navbar-collapse navbar-right col-lg-2 col-md-3">
						<a class="navbar-brand"
							href="login?logout">Logout</a>
							</div>
					</c:if>
				
				<div class="collapse navbar-collapse navbar-right col-lg-2 col-md-3">
					<a class="navbar-brand" href="?language=en">English</a>|<a class="navbar-brand" href="?language=fr">French</a>
				</div>
				
				</div>
			
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${ numberOfComputers }<spring:message code="app.nbComputer"/></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">

						<input type="search" id="search" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addcomputer">AddComputer</a>
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm"
			action="${pageContext.request.contextPath}/deleteComputer?${_csrf.parameterName}=${_csrf.token}" method="POST">
			<input type="hidden" id="selection" name="selection" value="">
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
								id="deleteSelected" onclick="deleteElements();"> <i
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
					<c:forEach var="element" items="${ list.content }">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${element.getId()}"></td>
							<td><a id="${element.getId()}"
								href="editComputer?id=${element.getId()}">${element.getName()}</a></td>
							<td>${ element.getIntroduced() }</td>
							<td>${ element.getDisconstinued() }</td>
							<td>${ element.getCompany().getName() }</td>
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
					<li><a
						href="dashboard?page=${currentPage - 1}&recordsPerPage=${recordsPerPage}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				<c:forEach begin="1" end="${noOfPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<li><a class="btn disabled" href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="dashboard?page=${i}&recordsPerPage=${recordsPerPage}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages}">
					<li><a
						href="dashboard?page=${currentPage + 1}&recordsPerPage=${recordsPerPage}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				</c:if>
			</ul>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default">
					<a href="dashboard?recordsPerPage=50">50</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="dashboard?recordsPerPage=100">100</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="dashboard?recordsPerPage=150">150</a>
				</button>
			</div>
		</div>

	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>