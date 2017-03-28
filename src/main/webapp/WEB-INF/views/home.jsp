<%@ page language="java" contentType="text/html; charset=UTF-8"	 pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>


	<h1>Médiathèque de POLYTECH ! </h1>
	<h2>Gestion de l'exposition 2016</h2>
	<h3>Sélectionnez la fonctionnalité voulue :</h3>
	<div class="row">
		<ul>
			<li><a href="adherent/liste">Lister les adhérents</a></li>
			<li><a href="proprietaire/liste">Lister les propriétaires</a></li>
			<li><a href="oeuvrevente/liste">Consulter le catalogue des Oeuvres à vendre</a></li>
			<li><a href="oeuvrepret/liste">Consulter le catalogue des Oeuvres à prêter</a></li>
			<li><a href="#" onclick="close_window();return false;">Quitter</a></li>
		</ul>
	</div>

<jsp:include page="footer.jsp">
	<jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
