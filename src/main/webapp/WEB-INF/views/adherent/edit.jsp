<%--
  Created by IntelliJ IDEA.
  User: seljo
  Date: 19/02/2017
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="../header.jsp">
        <jsp:param name="titre" value="Edition d'un adhérant"/>
    </jsp:include>
    <h2>Modifier un adherent</h2>
    <form class="form-horizontal"  method="post" action="edit">
        <div class="form-group">
            <div class="col-sm-10">
                <input type="hidden" class="form-control" name="idAdherent" id="id" value="${adherent.idAdherent}">
            </div>
        </div>
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
                <button type="submit" class="btn btn-info">Modifier</button>
            </div>
        </div>
    </form>

<jsp:include page="../footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
