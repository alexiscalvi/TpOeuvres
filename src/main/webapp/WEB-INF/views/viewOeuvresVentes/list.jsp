<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Catalogue des oeuvres"/>
</jsp:include>
<h1>
    Liste des oeuvres
    <br>
    <a class="btn btn-info" href="add">
        Ajout d'une nouvelle Oeuvre
    </a>
</h1>
<div class="flashMessages">${flashMessages}</div>

<table id="table_oeuvres">
    <thead>
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>Etat</th>
        <th>Prix</th>
        <th>Propriétaire</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${oeuvres}" var="oeuvre">
        <tr>
            <td>${oeuvre.idOeuvrevente}</td>
            <td>${oeuvre.titreOeuvrevente}</td>
            <td>${oeuvre.etatOeuvrevente}</td>
            <td>${oeuvre.prixOeuvrevente}</td>
            <td>${oeuvre.proprietaire.nomComplet}</td>
            <td>
                <c:if test="${oeuvre.etatOeuvrevente == 'L'}">
                    <a href="#reservationModal" data-toggle="modal" data-id="${oeuvre.idOeuvrevente}"
                       class="btn btn-info" role="button"><span
                            class="glyphicon glyphicon-shopping-cart"></span></a>
                </c:if>
                <c:if test="${oeuvre.etatOeuvrevente != 'L'}">
                    <a href="#" class="disabled btn btn-info" role="button"><span
                            class="glyphicon glyphicon-shopping-cart"></span></a>
                </c:if>
                <a href="detail?id=${oeuvre.idOeuvrevente}"
                   class="btn btn-warning" role="button"><span class="glyphicon glyphicon-eye-open"></span></a>
                <a href="edit?id=${oeuvre.idOeuvrevente}"
                   class="btn btn-success" role="button"><span class="glyphicon glyphicon-pencil"></span></a>
                <a href="delete?id=${oeuvre.idOeuvrevente}"
                   class="btn btn-danger" role="button"><span class="glyphicon glyphicon-remove"></span></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="container">
    <!-- Modal -->
    <div class="modal fade" id="reservationModal" role="dialog">
        <form class="form-horizontal" action="liste" method="post">
            <div class="modal-dialog">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Réservation d'une oeuvre destinée à la vente</h4>
                    </div>
                    <div class="modal-body container">
                        <div class="form-group">
                            <label></label>
                            <input type="hidden" id="idOeuvrevente" name="idOeuvrevente" value=""/>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2" for="idAdherent">Sélectionner l'adhérent</label>
                            <div class="col-sm-10">
                                <select id="idAdherent" name="idAdherent">
                                    <c:forEach items="${adherents}" var="adherent">
                                        <option value="${adherent.idAdherent}">${adherent.nomComplet}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
                        <input type="submit" class="btn btn-info" value="Réserver">
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>

<script>
    $(function () {
        $('a[data-toggle=modal], button[data-toggle=modal]').click(function () {
            $('#idOeuvrevente').val($(this).data('id'));
        });
    });
</script>