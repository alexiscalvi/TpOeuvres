<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<jsp:include page="../header.jsp">
	<jsp:param name="titre" value="Affichage adhérants"/>
</jsp:include>

	<script>
		$(document).ready(function () {
			$('#tableAdherents').DataTable({
			    language: fr_language,
			});
		});
	</script>

	<h2>Tableau des Adhérents ! <a class="btn btn-success" href="add" role="button">Ajouter un adherent</a></h2>

	<table id="tableAdherents">
		<thead>
		<tr>
			<th>Numero</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Ville</th>
			<th>Modifier</th>
			<th>Supprimer</th>
		</tr>
		</thead>

		<tbody>
		<c:forEach items="${mesAdherents}" var="item">
			<tr>
				<td>${item.idAdherent}</td>
				<td>${item.nomAdherent}</td>
				<td>${item.prenomAdherent}</td>
				<td>${item.villeAdherent}</td>
				<td>
					<a href="edit?idAdherent=${item.idAdherent}"
					   class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
				</td>
				<td>
					<a href="delete?idAdherent=${item.idAdherent}"
					   class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

<jsp:include page="../footer.jsp">
	<jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
