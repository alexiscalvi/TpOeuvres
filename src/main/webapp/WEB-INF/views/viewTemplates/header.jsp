<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>${param.titre}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/style.css"></c:url> ">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet">
</head>
<script src="<c:url value="/resources/script/main.js"></c:url>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<body>
<div class="container">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar-collapse-target">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/index"><span class="glyphicon glyphicon-home"></span> Accueil</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse-target">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="/viewAdherents/list">Adhérents</a></li>
                    <li class="dropdown"><a href="/viewProprietaires/list">Propriétaires</a></li>
                    <ul class="dropdown-menu" aria-labelledby="Oeuvres">
                        <li class="dropdown"><a href="/viewOeuvresVentes/list">Oeuvres ventes</a></li>
                        <li class="dropdown"><a href="/viewOoeuvresPrets/list">Oeuvres prêts</a></li>
                    </ul>
                </ul>
            </div>
        </div>
    </nav>
