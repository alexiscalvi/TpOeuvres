<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Edition d'un propriétaire"/>
</jsp:include>
<h2>Editer un propriétaire</h2>
<form class="form-horizontal" method="post" action="/user/editProprio">
    <div class="form-group">
        <div class="col-sm-10">
            <input type="hidden" class="form-control" name="id" id="idProprietaire"
                   value="${proprio.idProprietaire}" readonly>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="prenom">Prenom:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="prenom" id="prenom" placeholder="Entrez votre prénom"
                   value="${proprio.prenomProprietaire}" required="true">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="nom">Nom:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="nom" id="nom" placeholder="Entrez votre nom"
                   value="${proprio.nomProprietaire}" required="true">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn">Editer</button>
        </div>
    </div>
</form>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
