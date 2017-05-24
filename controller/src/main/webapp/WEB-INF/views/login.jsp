<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h3>JournalDEV Tutorials</h3>

	<c:if test="${not empty error}"><div>${error}</div></c:if>
	<c:if test="${not empty message}"><div>${message}</div></c:if>
<div class = "container">
	<div class="wrapper">
	<form  class="form-signin" name='login' action="<c:url value='/login' />" method='POST'>
	<h3 class="form-signin-heading">Welcome Back! Please Sign In</h3>
		<table>
			<tr>
				<td>Login:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	</div>
</div></body>
</html>

