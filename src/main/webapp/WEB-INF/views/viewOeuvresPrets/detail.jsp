<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Détail d'une oeuvre"/>
</jsp:include>

<h1>Détail d'une oeuvre pret</h1>
<br>
<p>ID : ${oeuvre.idOeuvrepret}</p>
<p>Titre : ${oeuvre.titreOeuvrepret}</p>
<p>Propriétaire : ${oeuvre.proprietaire.nomComplet}</p>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
