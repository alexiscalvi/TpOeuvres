<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Edition d'un adhérant"/>
</jsp:include>
<h1>Editer un adherent</h1>
<form class="form-horizontal" method="post" action="edit">
    <div class="form-group">
        <div class="col-sm-10">
            <input type="hidden" class="form-control" name="id" id="id" value="${adherent.idAdherent}">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="nom">Nom:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="nom" id="nom" placeholder="Entrez votre nom"
                   value="${adherent.nomAdherent}" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="prenom">Prenom:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="prenom" id="prenom" placeholder="Entrez votre prénom"
                   value="${adherent.prenomAdherent}" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="ville">Ville:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="ville" id="ville" placeholder="Enter votre ville"
                   value="${adherent.villeAdherent}">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn ">Modifier</button>
        </div>
    </div>
</form>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
