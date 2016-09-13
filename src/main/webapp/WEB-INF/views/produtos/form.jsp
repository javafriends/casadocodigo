<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Cadastro de livros</title>
</head>

<body>

	<c:url value="/produtos" var="url" />
	<form action="${url}" method="post">
		
			<div>
				<label for="titulo">Titulo:</label>
				<input type="text" name="titulo" id="titulo" />
			</div>
			
			<div>
				<label for="descricao">Descrição:</label>
				<textarea rows="10" cols="20" name="descricao" id="descricao"></textarea>
			</div>
			
			<div>
				<label for="numeroPaginas">Número de Páginas:</label>
				<input type="text" name="numeroPaginas" id="numeroPaginas">
			</div>
			
			<div>
				<input type="submit" value="Enviar" />
			</div>
		
	</form>


</body>

</html>