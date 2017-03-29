<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Edition d'une oeuvre"/>
</jsp:include>
    <h1>Edition d'une oeuvre</h1>
    <form class="form-horizontal" name='identification' method="post" action="editVente?id=${id}">
        <div class="form-group">
            <label class="control-label col-sm-2">Titre de l'oeuvre :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="titre" id="titre" placeholder="Entrez le titre ..." value="${titre}" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Prix de l'oeuvre :</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="prix" id="prix" value="${prix}" min="0" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Proprietaire de l'oeuvre :</label>
            <div class="col-sm-10">
                <select id="idProprio" name="idProprio">
                    <c:forEach items="${proprios}" var="proprio">
                        <option value="${proprio.idProprietaire}">${proprio.nomComplet}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn">Modifier</button>
            </div>
        </div>
    </form>

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
