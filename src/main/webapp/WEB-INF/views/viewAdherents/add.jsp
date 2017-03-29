<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Ajouter un adhérent"/>
</jsp:include>

<h1>Ajout d'un adhérent</h1>
<form class="form-horizontal" name='identification' method="post" action="/user/addAdherent">
    <div class="form-group">
        <label class="control-label col-sm-2" for="prenom">Prenom :</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="prenom" id="prenom" placeholder="Entrez votre prénom"
                   value="${adherent.prenomAdherent}" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="nom">Nom :</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="nom" id="nom" placeholder="Entrez votre nom"
                   value="${adherent.nomAdherent}" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="ville">Ville :</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="ville" id="ville" placeholder="Entrez votre ville"
                   value="${adherent.villeAdherent}">
        </div>
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