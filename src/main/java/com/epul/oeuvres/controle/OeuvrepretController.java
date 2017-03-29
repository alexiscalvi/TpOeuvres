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

/**
 * Created by Alexis on 25/03/2017.
 *
 */
@Controller
@RequestMapping("oeuvrepret/")
public class OeuvrepretController  {

    private OeuvrepretDAO oeuvrepretDAO;

    public OeuvrepretController() {
        super();
        this.oeuvrepretDAO = new OeuvrepretDAO();
    }

    @RequestMapping(value = "liste", method = RequestMethod.GET)
    public ModelAndView listeAction(HttpServletRequest request, HttpServletResponse response) {
        List<Oeuvrepret> oeuvreprets = this.oeuvrepretDAO.findAll();;
        request.setAttribute("oeuvreprets", oeuvreprets);
        return new ModelAndView("oeuvrepret/liste");
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)

    public ModelAndView detailAction(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value="idOeuvrepret") final String idOeuvrepretString) {

        int idOeuvrepret = Integer.parseInt(idOeuvrepretString);
        Oeuvrepret oeuvrepret = this.oeuvrepretDAO.find(idOeuvrepret);
        request.setAttribute("oeuvrepret", oeuvrepret);
        return new ModelAndView("oeuvrepret/detail");
    }

    @RequestMapping(value = "delete")
    public ModelAndView ModelAndView(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value="idOeuvrepret") final String idOeuvrepretString) {
        int idOeuvrepret = Integer.parseInt(idOeuvrepretString);
        oeuvrepretDAO.delete(idOeuvrepret);
        List<Oeuvrepret> oeuvreprets = this.oeuvrepretDAO.findAll();;
        request.setAttribute("oeuvreprets", oeuvreprets);
        return new ModelAndView("oeuvrepret/liste");
    }

    @RequestMapping(value = "editer")
    public ModelAndView editerAction(HttpServletRequest request, HttpServletResponse response) {
        int idOeuvrepret = parseInt(request.getParameter("idOeuvrepret"));
        Oeuvrepret oeuvrepret = oeuvrepretDAO.find(idOeuvrepret);
        request.setAttribute("idOeuvrepret", idOeuvrepret);
        String titre;
        Integer idProprietaire;
        titre = request.getParameter("txttitre");
        if(titre != null && request.getParameter("idProprietaire")!=null){
            idProprietaire = Integer.parseInt(request.getParameter("idProprietaire"));
            oeuvrepret.setTitreOeuvrepret(titre);
            ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
            Proprietaire proprietaire = proprietaireDAO.find(idProprietaire);
            oeuvrepret.setProprietaire(proprietaire);
            oeuvrepretDAO.updateOeuvrepret(oeuvrepret);
            return this.listeAction(request, response);
        }else{
            request.setAttribute("txttitre", oeuvrepret.getTitreOeuvrepret());
            request.setAttribute("idProprietaire", oeuvrepret.getProprietaire().getIdProprietaire());
            request.setAttribute("proprietaires", new ProprietaireDAO().findAll());
            return new ModelAndView("oeuvrepret/editer");
        }
    }

    @RequestMapping(value = "add")
    public ModelAndView addAction(HttpServletRequest request, HttpServletResponse response) {
        String titre;
        Integer prix, idProprietaire;
        titre = request.getParameter("txttitre");
        titre = request.getParameter("txttitre");
        if(titre != null && request.getParameter("idProprietaire")!=null){
            //Si le formulaire est valide
            idProprietaire = Integer.parseInt(request.getParameter("idProprietaire"));
            Oeuvrepret oeuvrepret = new Oeuvrepret();
            oeuvrepret.setTitreOeuvrepret(titre);
            ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
            Proprietaire proprietaire = proprietaireDAO.find(idProprietaire);
            oeuvrepret.setProprietaire(proprietaire);
            OeuvrepretDAO oeuvrepretDAO = new OeuvrepretDAO();
            oeuvrepretDAO.insert(oeuvrepret);
            List<Oeuvrepret> oeuvreprets = this.oeuvrepretDAO.findAll();;
            request.setAttribute("oeuvreprets", oeuvreprets);
            return new ModelAndView("oeuvrepret/liste");
        }

        request.setAttribute("proprietaires", new ProprietaireDAO().findAll());
        return new ModelAndView("oeuvrepret/add");

    }
}
