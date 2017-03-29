package com.epul.oeuvres.controle;

import ch.qos.logback.classic.boolex.GEventEvaluator;
import com.epul.oeuvres.dao.*;
import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.utilitaires.FlashMessage;
import com.epul.oeuvres.utilitaires.FlashMessageStatut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static java.lang.Integer.parseInt;


@Controller
@RequestMapping("oeuvre/")
public class OeuvreController {

    private OeuvrepretDAO oeuvrepretDAO;

    public OeuvreController() {
        super();
        this.oeuvrepretDAO = new OeuvrepretDAO();
    }

    @RequestMapping(value = "pret", method = RequestMethod.GET)
    public ModelAndView pretAction(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("oeuvres", this.oeuvrepretDAO.findAll());
        return new ModelAndView("viewOeuvresPrets/list");
    }


    @RequestMapping(value = "detailPret", method = RequestMethod.GET)
    public ModelAndView detailPretAction(HttpServletRequest request, HttpServletResponse response) {
        Oeuvrepret oeuvre = this.oeuvrepretDAO.find(parseInt(request.getParameter("id")));
        request.setAttribute("oeuvrepret", oeuvre);
        return new ModelAndView("viewOeuvresPrets/detail");
    }

    @RequestMapping(value = "deletePret")
    public ModelAndView deletePretAction(HttpServletRequest request, HttpServletResponse response) {
        oeuvrepretDAO.delete(parseInt(request.getParameter("id")));
        request.setAttribute("oeuvres", this.oeuvrepretDAO.findAll());
        return new ModelAndView("viewOeuvresPrets/list");
    }

    @RequestMapping(value = "editPret")
    public ModelAndView editerPretAction(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        Oeuvrepret oeuvre = oeuvrepretDAO.find(id);
        String titre = request.getParameter("titre");

        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        if (titre != null && request.getParameter("idProprio") != null) {
            Integer idProprio = Integer.parseInt(request.getParameter("idProprio"));
            oeuvre.setTitreOeuvrepret(titre);
            Proprietaire proprietaire = proprietaireDAO.find(idProprio);
            oeuvre.setProprietaire(proprietaire);
            oeuvrepretDAO.updateOeuvrepret(oeuvre);
            return this.pretAction(request, response);
        } else {
            request.setAttribute("id", id);
            request.setAttribute("titre", oeuvre.getTitreOeuvrepret());
            request.setAttribute("idProprio", oeuvre.getProprietaire().getIdProprietaire());
            request.setAttribute("proprios", proprietaireDAO.findAll());
            return new ModelAndView("viewOeuvresPrets/edit");
        }
    }

    @RequestMapping(value = "addPret")
    public ModelAndView addPretAction(HttpServletRequest request, HttpServletResponse response) {
        String titre = request.getParameter("titre");
        if (titre != null && request.getParameter("idProprietaire")!=null) {
            OeuvrepretDAO oeuvrepretDAO = new OeuvrepretDAO();
            ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
            Integer idProprio = Integer.parseInt(request.getParameter("idProprietaire"));

            Oeuvrepret oeuvrepret = new Oeuvrepret();
            oeuvrepret.setTitreOeuvrepret(titre);
            oeuvrepret.setProprietaire(proprietaireDAO.find(idProprio));
            oeuvrepretDAO.insert(oeuvrepret);

            request.setAttribute("oeuvres", this.oeuvrepretDAO.findAll());
            return new ModelAndView("viewOeuvresPrets/list");
        }

        request.setAttribute("proprios", new ProprietaireDAO().findAll());
        return new ModelAndView("viewOeuvresPrets/add");
    }
}

