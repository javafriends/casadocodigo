<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listagem de Produtos</title>
</head>
<body>
	<p>${mensagem}</p>

	<table>
		<tr>
			<th>Título</th>
			<th>Preços</th>
			<th>Detalhes</th>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.titulo}</td>
				<td><c:forEach items="${produto.precos}" var="preco">
						[ ${preco.tipoLivro}: R$ ${preco.valor} ]
					</c:forEach>
				</td>
				<td>
					<c:url value="/produtos/${produto.id}" var="linkDetalhar" />
					<a href="${linkDetalhar}">Detalhar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>