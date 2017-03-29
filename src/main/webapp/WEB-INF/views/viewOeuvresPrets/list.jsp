<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Catalogue des oeuvres prêt"/>
</jsp:include>

<h1>Catalogue des oeuvres de prêt</h1>
<br>
<a class="btn btn-info" href="add">
    <span class="glyphicon glyphicon-plus"></span> Ajout d'une oeuvre
</a>

<table id="table_oeuvres">
    <thead>
    <tr>
        <th>Titre</th>
        <th>Propriétaire</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${oeuvres}" var="oeuvre">
        <tr>
            <td>${oeuvre.titreOeuvrepret}</td>
            <td>${oeuvre.proprietaire.nomComplet}</td>
            <td>
                <a href="detailPret?id=${oeuvre.idOeuvrepret}" role="button"><span
                        class="glyphicon glyphicon-eye-open"></span></a>
                <a href="editPret?id=${oeuvre.idOeuvrepret}" role="button"><span
                        class="glyphicon glyphicon-pencil"></span></a>
                <a href="deletePret?id=${oeuvre.idOeuvrepret}" role="button"><span
                        class="glyphicon glyphicon-remove"></span></a>
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
        $('a[data-toggle=modal], button[data-toggle=modal]').click(function () {
            $('#idOeuvrepret').val($(this).data('id'));
        });
    });
</script>
