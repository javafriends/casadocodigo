<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listagem de Produtos</title>
</head>
<body>
	<table>
		<tr>
			<th>Título</th>
			<th>Valores</th>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.titulo}</td>
				<td><c:forEach items="${produto.precos}" var="preco">
						[${preco.valor} - ${preco.tipoLivro}]
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>