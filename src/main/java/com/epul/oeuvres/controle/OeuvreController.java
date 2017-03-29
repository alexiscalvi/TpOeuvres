package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.*;
import com.epul.oeuvres.metier.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.Integer.parseInt;
import java.util.Calendar;

@Controller
@RequestMapping("oeuvre/")
public class OeuvreController {

    private OeuvrepretDAO oeuvrepretDAO;
    private OeuvreventeDAO oeuvreventeDAO;

    public OeuvreController() {
        super();
        this.oeuvrepretDAO = new OeuvrepretDAO();
        this.oeuvreventeDAO = new OeuvreventeDAO();
    }

    @RequestMapping(value = "pret", method = RequestMethod.GET)
    public ModelAndView pretAction(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("oeuvres", this.oeuvrepretDAO.get());
        return new ModelAndView("viewOeuvresPrets/list");
    }


    @RequestMapping(value = "deletePret")
    public ModelAndView deletePretAction(HttpServletRequest request, HttpServletResponse response) {
        oeuvrepretDAO.delete(parseInt(request.getParameter("id")));
        request.setAttribute("oeuvres", this.oeuvrepretDAO.get());
        return new ModelAndView("viewOeuvresPrets/list");
    }

    @RequestMapping(value = "detailPret", method = RequestMethod.GET)
    public ModelAndView detailPretAction(HttpServletRequest request, HttpServletResponse response) {
        Oeuvrepret oeuvre = this.oeuvrepretDAO.get(parseInt(request.getParameter("id")));
        request.setAttribute("oeuvre", oeuvre);
        return new ModelAndView("viewOeuvresPrets/detail");
    }


    @RequestMapping(value = "addPret")
    public ModelAndView addPretAction(HttpServletRequest request, HttpServletResponse response) {
        String titre = request.getParameter("titre");
        if (titre != null && request.getParameter("idProprietaire") != null) {
            OeuvrepretDAO oeuvrepretDAO = new OeuvrepretDAO();
            ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
            Integer idProprio = Integer.parseInt(request.getParameter("idProprietaire"));

            Oeuvrepret oeuvrepret = new Oeuvrepret();
            oeuvrepret.setTitreOeuvrepret(titre);
            oeuvrepret.setProprietaire(proprietaireDAO.get(idProprio));
            oeuvrepretDAO.add(oeuvrepret);

            request.setAttribute("oeuvres", this.oeuvrepretDAO.get());
            return new ModelAndView("viewOeuvresPrets/list");
        }

        request.setAttribute("proprios", new ProprietaireDAO().get());
        return new ModelAndView("viewOeuvresPrets/add");
    }

    @RequestMapping(value = "editPret")
    public ModelAndView editerPretAction(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        Oeuvrepret oeuvre = oeuvrepretDAO.get(id);
        String titre = request.getParameter("titre");

        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        if (titre != null && request.getParameter("idProprio") != null) {
            Integer idProprio = Integer.parseInt(request.getParameter("idProprio"));
            oeuvre.setTitreOeuvrepret(titre);
            Proprietaire proprietaire = proprietaireDAO.get(idProprio);
            oeuvre.setProprietaire(proprietaire);
            oeuvrepretDAO.update(oeuvre);
            return this.pretAction(request, response);
        } else {
            request.setAttribute("id", id);
            request.setAttribute("titre", oeuvre.getTitreOeuvrepret());
            request.setAttribute("idProprio", oeuvre.getProprietaire().getIdProprietaire());
            request.setAttribute("proprios", proprietaireDAO.get());
            return new ModelAndView("viewOeuvresPrets/edit");
        }
    }


    @RequestMapping(value = "vente")
    public ModelAndView venteAction(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("idAdherent") != null && request.getParameter("id") != null) {
            int idAdherent = parseInt(request.getParameter("idAdherent"));
            int id = parseInt(request.getParameter("id"));

            Adherent adherent = new AdherentDAO().get(idAdherent);
            Oeuvrevente oeuvre = oeuvreventeDAO.get(id);
            oeuvre.setEtatOeuvrevente("R");
            oeuvreventeDAO.update(oeuvre);

            Reservation resa = new Reservation();
            resa.setAdherent(adherent);
            resa.setOeuvrevente(oeuvre);
            resa.setStatut("confirmee");
            resa.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        }

        request.setAttribute("oeuvres", oeuvreventeDAO.get());
        request.setAttribute("adherents", new AdherentDAO().get());
        return new ModelAndView("viewOeuvresVentes/list");
    }


    @RequestMapping(value = "editVente")
    public ModelAndView editVenteAction(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        Oeuvrevente oeuvre = oeuvreventeDAO.get(id);
        String titre = request.getParameter("titre");
        if (titre != null && request.getParameter("prix") != null && request.getParameter("idProprio") != null) {
            float prix = Float.parseFloat(request.getParameter("prix"));
            Integer idProprietaire = Integer.parseInt(request.getParameter("idProprio"));
            ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
            Proprietaire proprietaire = proprietaireDAO.get(idProprietaire);

            oeuvre.setEtatOeuvrevente("L");
            oeuvre.setTitreOeuvrevente(titre);
            oeuvre.setPrixOeuvrevente(prix);
            oeuvre.setProprietaire(proprietaire);
            oeuvreventeDAO.update(oeuvre);
            return this.venteAction(request, response);
        } else {
            request.setAttribute("prix", oeuvre.getPrixOeuvrevente());
            request.setAttribute("idProprio", oeuvre.getProprietaire().getIdProprietaire());
            request.setAttribute("titre", oeuvre.getTitreOeuvrevente());
            request.setAttribute("id", id);
            request.setAttribute("proprios", new ProprietaireDAO().get());
            return new ModelAndView("viewOeuvresVentes/edit");
        }
    }

    @RequestMapping(value = "deleteVente", method = RequestMethod.GET)
    public ModelAndView deleteVenteAction(HttpServletRequest request, HttpServletResponse response) {
        oeuvreventeDAO.delete(parseInt(request.getParameter("id")));
        request.setAttribute("oeuvres", oeuvreventeDAO.get());
        request.setAttribute("adherents", new AdherentDAO().get());
        return new ModelAndView("viewOeuvresVentes/list");
    }


    @RequestMapping(value = "addVente")
    public ModelAndView addVenteAction(HttpServletRequest request, HttpServletResponse response) {
        String titre = request.getParameter("titre");
        Integer idProprietaire;
        float prix;

        if (titre != null && request.getParameter("prix") != null && request.getParameter("idProprio") != null) {
            prix = Float.parseFloat(request.getParameter("prix"));
            idProprietaire = Integer.parseInt(request.getParameter("idProprio"));

            Oeuvrevente oeuvre = new Oeuvrevente();
            oeuvre.setPrixOeuvrevente(prix);
            oeuvre.setTitreOeuvrevente(titre);
            oeuvre.setEtatOeuvrevente("L");
            Proprietaire proprio = new ProprietaireDAO().get(idProprietaire);
            oeuvre.setProprietaire(proprio);
            oeuvreventeDAO.add(oeuvre);
            return this.venteAction(request, response);
        }

        request.setAttribute("proprios", new ProprietaireDAO().get());

        return new ModelAndView("viewOeuvresVentes/add");

    }


    @RequestMapping(value = "detailVente", method = RequestMethod.GET)
    public ModelAndView detailVenteAction(HttpServletRequest request, HttpServletResponse response) {
        Oeuvrevente oeuvre = this.oeuvreventeDAO.get(parseInt(request.getParameter("id")));
        request.setAttribute("oeuvre", oeuvre);
        return new ModelAndView("viewOeuvresVentes/detail");
    }
}

