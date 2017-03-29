<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Edition d'une oeuvre"/>
</jsp:include>

<h1>Edition d'une oeuvre</h1>
<form class="form-horizontal" name='identification' method="post" action="edit?id=${idOeuvrepret}">
    <div class="form-group">
        <label class="titre">Titre de l'oeuvre :</label>
        <input type="text" class="form-control" name="titre" id="titre" placeholder="Titre"
               value="${txttitre}" required>*
    </div>
    <div class="form-group">
        <label for="idProprio">Proprietaire de l'oeuvre :</label>
        <select id="idProprio" name="idProprio">
            <c:forEach items="${proprietaires}" var="proprio">
                <option value="${proprio.idProprietaire}">${proprio.nomComplet}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-info">Modifier</button>
        </div>
    </div>
</form>
<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>