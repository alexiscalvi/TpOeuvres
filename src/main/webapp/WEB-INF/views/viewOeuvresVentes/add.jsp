<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Ajout d'une oeuvre"/>
</jsp:include>

<h1>Ajout d'une oeuvre</h1>
<form class="form-horizontal" name='identification' method="post" action="/oeuvre/addVente">
    <div class="form-group">
        <label class="control-label col-sm-2">Titre de l'oeuvre :</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="titre" id="titre" placeholder="Entrez le titre ..." required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Prix de l'oeuvre :</label>
        <div class="col-sm-10">
            <input type="number" class="form-control" name="prix" id="prix" required>
        </div>
    </div>
    <div class="form-group">
        <label for="idProprio">Proprietaire de l'oeuvre :</label>
        <select id="idProprio" name="idProprio">
            <c:forEach items="${proprios}" var="proprio">
                <option value="${proprio.idProprietaire}">${proprio.nomComplet}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-info">Ajouter</button>
        </div>
    </div>
</form>
<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
