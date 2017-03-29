<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Détail d'une oeuvre"/>
</jsp:include>

<div class="container">
    <h1>Détail d'une oeuvre</h1>
    <div class="row well">
        <img src="http://www2.ac-lyon.fr/etab/lycees/lyc-69/bernard/local/cache-vignettes/L400xH291/la_persistence_de_la_memoire_ou_les_montres_molles-ff51f.jpg"
             class="img-responsive" alt="image">
        <br>
        <ul>
            <li><b>ID : </b>${oeuvre.idOeuvrevente}</li>
            <li><b>Titre : </b>${oeuvre.titreOeuvrevente}</li>
            <li><b>Etat : </b>${oeuvre.etatOeuvrevente}</li>
            <li><b>Prix : </b>${oeuvre.prixOeuvrevente}</li>
            <li><b>Propriétaire : </b>${oeuvre.proprietaire.nomComplet}</li>
        </ul>
    </div>
    <jsp:include page="../viewTemplates/footer.jsp">
        <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
    </jsp:include>
