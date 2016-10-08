<%@	page language="java" contentType="text/html;	charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:page title="Listagem	de	Produtos">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<spring:message code="users.welcome" arguments="${user.nome}" />
	</sec:authorize>
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
					</c:forEach></td>
				<td><c:url value="/produtos/${produto.id}" var="linkDetalhar" />
					<a href="${linkDetalhar}">Detalhar</a></td>
			</tr>
		</c:forEach>
	</table>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:url value="/produtos/form" var="formLink" />
		<a href="${formLink}"> Cadastrar novo produto </a>
	</sec:authorize>
	<br />
	<sec:authorize access="isAuthenticated()">
		<c:url value="/logout" var="logOut" />
		<a href="${logOut}"> Sair do sistema (logout) </a>
	</sec:authorize>
</cdc:page>
