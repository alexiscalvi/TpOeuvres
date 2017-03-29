<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Liste des propriétaires"/>
</jsp:include>
<h1>Liste des propriétaires </h1>
<a class="btn btn-success" href="/user/addProprio" role="button">Ajouter un propriétaire</a>
<table id="table_propriétaires">
    <thead>
    <th>Numero</th>
    <th>Nom</th>
    <th>Prénom</th>
    <th>Actions</th>
    </thead>

    <c:forEach items="${proprios}" var="proprio">
        <tr>
            <td>${proprio.idProprietaire}</td>
            <td>${proprio.nomProprietaire}</td>
            <td>${proprio.prenomProprietaire}</td>
            <td>
                <a href="editProprio?id=${proprio.idProprietaire}" ><span class="glyphicon glyphicon-pencil"></span></a>
                <a href="deleteProprio?id=${proprio.idProprietaire}" ><span class="glyphicon glyphicon-remove"></span></a>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
