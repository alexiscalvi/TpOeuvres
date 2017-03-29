package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.AdherentDAO;
import com.epul.oeuvres.dao.ProprietaireDAO;
import com.epul.oeuvres.metier.Adherent;
import com.epul.oeuvres.metier.Proprietaire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user/")
public class UserController {

    private final ProprietaireDAO proprietaireDAO;
    private final AdherentDAO adherentDAO;

    public UserController() {
        super();
        this.proprietaireDAO = new ProprietaireDAO();
        this.adherentDAO = new AdherentDAO();
    }

    @RequestMapping(value = "proprio")
    public ModelAndView proprioAction(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("proprios", proprietaireDAO.get());
        return new ModelAndView("viewProprios/list");
    }

    @RequestMapping(value = "deleteProprio")
    public ModelAndView deleteProprioAction(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        proprietaireDAO.delete(id);
        request.setAttribute("proprios", proprietaireDAO.get());
        return new ModelAndView("viewProprios/list");
    }

    @RequestMapping(value = "addProprio")
    public ModelAndView addProprioAction(HttpServletRequest request, HttpServletResponse response) {
        Proprietaire proprio = new Proprietaire();
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        if (nom != null && prenom != null) {
            proprio.setPrenomProprietaire(prenom);
            proprio.setNomProprietaire(nom);
            proprietaireDAO.add(proprio);
            request.setAttribute("proprios", proprietaireDAO.get());
            return new ModelAndView("viewProprios/list");
        } else {
            return new ModelAndView("viewProprios/add");
        }
    }

    @RequestMapping(value = "editProprio")
    public ModelAndView editProprioAction(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Proprietaire proprio = proprietaireDAO.get(id);
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        if (nom != null && prenom != null) {
            proprio.setPrenomProprietaire(prenom);
            proprio.setNomProprietaire(nom);
            proprietaireDAO.update(proprio);
            request.setAttribute("proprios", proprietaireDAO.get());
            return new ModelAndView("viewProprios/list");
        } else {
            request.setAttribute("proprio", proprio);
            return new ModelAndView("viewProprios/edit");
        }
    }


    @RequestMapping(value = "adherent", method = RequestMethod.GET)
    public ModelAndView adherentAction(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("adherents", adherentDAO.get());
        return new ModelAndView("viewAdherents/list");
    }

    @RequestMapping(value = "deleteAdherent", method = RequestMethod.GET)
    public ModelAndView deleteAdherent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        adherentDAO.delete(id);
        return adherentAction(request, response);
    }

    @RequestMapping(value = "editAdherent")
    public ModelAndView editAdherentAction(HttpServletRequest request, HttpServletResponse response) {
        int idAdherent = Integer.parseInt(request.getParameter("id"));
        Adherent adherent = adherentDAO.get(idAdherent);
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String ville = request.getParameter("ville");
        if (nom != null && prenom != null && ville != null) {
            adherent.setPrenomAdherent(prenom);
            adherent.setNomAdherent(nom);
            adherent.setVilleAdherent(ville);
            adherentDAO.update(adherent);
            return adherentAction(request, response);
        } else {
            request.setAttribute("adherent", adherent);
            return new ModelAndView("viewAdherents/edit");
        }

    }


    @RequestMapping(value = "addAdherent")
    public ModelAndView addAdherentAction(HttpServletRequest request, HttpServletResponse response) {
        Adherent adherent = new Adherent();
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String ville = request.getParameter("ville");
        if (nom != null && prenom != null && ville != null) {
            adherent.setPrenomAdherent(prenom);
            adherent.setNomAdherent(nom);
            adherent.setVilleAdherent(ville);
            adherentDAO.add(adherent);
            return adherentAction(request, response);
        } else {
            return new ModelAndView("viewAdherents/add");
        }
    }
}
