<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<title>Login</title>
	</head>
	
	<body>
		<h3>Efetue Login na Casa do Codigo</h3>
		<form:form servletRelativeAction="/login">
			
			<table>
				<tr>
					<td>Login:</td>
					<td><input type="text" name='username' value='' /> </td>
				</tr>
				
				<tr>
					<td>Senha:</td>
					<td><input type="password" name='password' value='' /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Logar" /> 
					</td>
				</tr>
			
			</table>
			
		</form:form>
	</body>

</html>