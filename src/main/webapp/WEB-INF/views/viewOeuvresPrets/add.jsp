<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Ajouter une oeuvre"/>
</jsp:include>

<h1>Ajout d'une oeuvre de prêt</h1>
<form class="form-horizontal" name='identification' method="post" action="add">
    <div class="form-group">
        <label for="titre">Titre de l'oeuvre :</label>
            <input type="text" class="form-control" id="titre" name="titre" placeholder="Entrez le titre" required>
    </div>
    <div class="form-group">
        <label for="proprio">Proprietaire de l'oeuvre :</label>
            <select name="idProprio" id="proprio">
                <c:forEach items="${proprios}" var="proprio">
                    <option value="${proprio.idProprietaire}">${proprio.nomComplet}</option>
                </c:forEach>
            </select>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-info">Créer</button>
        </div>
    </div>
</form>
<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
