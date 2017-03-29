<%--
  Created by IntelliJ IDEA.
  User: Matthieu
  Date: 25/02/2017
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Catalogue des oeuvres prêt"/>
</jsp:include>

    <script>
        $(document).ready(function () {
            $('#tableOeuvres').DataTable({
                language: fr_language
            });

            $('a[data-toggle=modal], button[data-toggle=modal]').click(function () {
                $('#idOeuvrepret').val($(this).data('id'));
            });
        });
    </script>
    <h1>
        Catalogue des oeuvres en prêt
        <br>
        <a class="btn btn-info" href="add">
            <span class="glyphicon glyphicon-plus"></span> Ajout d'une nouvelle Oeuvre
        </a>
    </h1>

    <div class="flashMessages">${flashMessages}</div>

    <table id="tableOeuvres">
        <thead>
        <tr>
            <th>Titre oeuvre</th>
            <th>Propriétaire</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${oeuvreprets}" var="oeuvrepret">
            <tr>
                <td>${oeuvrepret.titreOeuvrepret}</td>
                <td>${oeuvrepret.proprietaire.nomComplet}</td>
                <td>
                    <a href="detail?idOeuvrepret=${oeuvrepret.idOeuvrepret}"
                       class="btn btn-warning" role="button"><span class="glyphicon glyphicon-eye-open"></span></a>
                    <a href="editer?idOeuvrepret=${oeuvrepret.idOeuvrepret}"
                       class="btn btn-success" role="button"><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="delete?idOeuvrepret=${oeuvrepret.idOeuvrepret}"
                       class="btn btn-danger" role="button"><span class="glyphicon glyphicon-remove"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
