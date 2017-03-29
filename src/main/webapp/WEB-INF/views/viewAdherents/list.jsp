<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Affichage des adhérants"/>
</jsp:include>
<h2>Liste des Adhérents</h2>
<a class="btn btn-success" href="add" role="button">Ajouter adherent</a>
<table id="table_adherents">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Ville</th>
        <th>Actions</th>
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
                <a href="editAdherent?idAdherent=${item.idAdherent}"><span class="glyphicon glyphicon-pencil"></span></a>
                <a href="deleteAdherent?idAdherent=${item.idAdherent}"><span class="glyphicon glyphicon-remove"></span></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>

<script>
    $(function () {
        $('#table_adherents').DataTable({
            language: fr_language
        });
    });
</script>
