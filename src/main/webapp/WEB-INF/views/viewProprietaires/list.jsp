<%--
  Created by IntelliJ IDEA.
  User: seljo
  Date: 20/02/2017
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Liste des propriétaires"/>
</jsp:include>

<script>
    $(document).ready(function () {
        $('#tablePropriétaires').DataTable({
            language: fr_language
        });
    });
</script>

    <h1>Tableau des Propriétaires ! <a class="btn btn-success" href="add" role="button">Ajouter un propriétaire</a></h1>
    <div class="flashMessages">${flashMessages}</div>
    <table id="tablePropriétaires">
        <thead>
        <th>Numero</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Actions</th>

        </thead>

        <c:forEach items="${owners}" var="item">
            <tr>
                <td>${item.idProprietaire}</td>
                <td>${item.nomProprietaire}</td>
                <td>${item.prenomProprietaire}</td>
                <!-- Modifier button -->
                <td>
                    <a href="editer?idProprietaire=${item.idProprietaire}"
                       class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="delete?idProprietaire=${item.idProprietaire}"
                       class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
                </td>
            </tr>
        </c:forEach>
    </table>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
