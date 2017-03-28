<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp">
    <jsp:param name="titre" value="Ajouter un  adhérent"/>
</jsp:include>

<h2>Ajout d'un adhérent</h2>
<form class="form-horizontal" name='identification' method="post" action="add">
    <div class="form-group">
        <label class="control-label col-sm-2" for="nom">Nom:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="txtnom" id="nom" placeholder="Entrez votre nom" value="${adherent.nomAdherent}" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="prenom">Prenom:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="txtprenom" id="prenom" placeholder="Entrez votre prénom" value="${adherent.prenomAdherent}" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="ville">Ville:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="txtville" id="ville" placeholder="Enter votre ville" value="${adherent.villeAdherent}">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-info">Ajouter</button>
        </div>
    </div>
</form>
<jsp:include page="../footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>