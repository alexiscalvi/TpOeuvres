<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="viewTemplates/header.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
<br>
<h1>Médiathèque de POLYTECH !</h1>
<h2>Gestion de l'exposition 2016</h2>
<br>
<br>
<br>
<br>
<p>Sélectionnez la fonctionnalité voulue :</p>
<div class="row">
    <ul>
        <li><a href="viewAdherents/list">Lister les adhérents</a></li>
        <li><a href="viewProprietaires/list">Lister les propriétaires</a></li>
        <li><a href="viewOeuvresVentes/list">Consulter le catalogue des Oeuvres à vendre</a></li>
        <li><a href="viewOeuvresPrets/list">Consulter le catalogue des Oeuvres à prêter</a></li>
        <li><a href="#" onclick="close_window();">Quitter</a></li>
    </ul>
</div>
<jsp:include page="viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>