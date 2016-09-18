<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Cadastro de livros</title>
</head>

<body>

	<form:form action="${spring:mvcUrl('saveProduto').build()}" method="post" commandName="produto">

		<div>
				<label for="titulo">Titulo:</label>
				<form:input path="titulo" id="titulo" />
				<form:errors path="titulo"/>
			</div>
			
			<div>
				<label for="descricao">Descrição:</label>
				<form:textarea rows="10" cols="20" path="descricao" id="descricao"></form:textarea>
				<form:errors path="descricao"/>
			</div>
			
			<div>
				<label for="numeroPaginas">Número de Páginas:</label>
				<form:input path="numeroPaginas" id="numeroPaginas" />
				<form:errors path="numeroPaginas"/>
			</div>

		<div>
			<label for="releaseDate">Data de lançamento</label> <input
				type="date" name="dataLancamento" id="dataLancamento" />
			<form:errors path="dataLancamento" />
		</div>

		<c:forEach items="${tipos}" var="tipoLivro" varStatus="status">
				<div>
					<label for="preco_${tipoLivro}">${tipoLivro}:</label> 
					<input type="text"   name="precos[${status.index}].valor" id="preco_${tipoLivro}" /> 
					<input type="hidden" name="precos[${status.index}].tipoLivro" value="${tipoLivro}" />
				</div>
			</c:forEach>

		<div>
				<input type="submit" value="Enviar" />
			</div>
		
	</form:form>


</body>

</html>