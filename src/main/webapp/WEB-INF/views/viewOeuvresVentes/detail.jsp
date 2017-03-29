<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../viewTemplates/header.jsp">
    <jsp:param name="titre" value="Détail d'une oeuvre"/>
</jsp:include>

      <div class="container">
        <h1>Détail d'une oeuvre</h1>
          <div class="row well">
              <img id="img_oeuvre" src="http://www.celestialseasonings.com/sites/celestialseasonings.com/files/artwork/SleepytimeRoom.jpg" class="img-responsive" alt="Cinque Terre">
              <br>
              <ul>
                  <li><b>ID : </b>${oeuvre.idOeuvrevente}</li>
                  <li><b>Titre : </b>${oeuvre.titreOeuvrevente}</li>
                  <li><b>Etat : </b>${oeuvre.etatOeuvrevente}</li>
                  <li><b>Prix : </b>${oeuvre.prixOeuvrevente}</li>
                  <li><b>Propriétaire : </b>${oeuvre.proprietaire.nomComplet}</li>
              </ul>
          </div>

    <script language="JavaScript">

        function random_image() {
            var images = [];
            images[1] = "http://www.celestialseasonings.com/sites/celestialseasonings.com/files/artwork/BengelSpice.jpg";
            images[2] = "http://www.celestialseasonings.com/sites/celestialseasonings.com/files/artwork/CandyCaneLane.jpg";
            images[3] = "http://www.celestialseasonings.com/sites/celestialseasonings.com/files/artwork/RedZinger.jpg";
            images[4] = "http://www.celestialseasonings.com/sites/celestialseasonings.com/files/artwork/MintMagic.jpg";
            images[5] = "http://www.celestialseasonings.com/sites/celestialseasonings.com/files/artwork/TensionTamer.jpg";
            images[6] = "http://www.celestialseasonings.com/sites/celestialseasonings.com/files/artwork/SleepytimeRoom.jpg";
            var ry = Math.floor(Math.random() * images.length);
            if (ry == 0) ry = 1;
            $("#img_oeuvre").attr('src', images[ry]);
        }
        random_image();
    </script>

<jsp:include page="../viewTemplates/footer.jsp">
    <jsp:param name="titre" value="Expo : Médiathèque De POLYTECH"/>
</jsp:include>
