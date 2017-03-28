package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.ProprietaireDAO;
import com.epul.oeuvres.metier.Proprietaire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("proprietaire/")
public class ProprietaireController {

    private final ProprietaireDAO proprietaireDAO;

    public ProprietaireController() {
        super();
        this.proprietaireDAO = new ProprietaireDAO();
    }

    @RequestMapping(value = "liste")
    public ModelAndView listeAction(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("owners", proprietaireDAO.findAll());
        return new ModelAndView("proprietaire/liste");
    }

    @RequestMapping(value = "editer")
    public ModelAndView editAction(HttpServletRequest request, HttpServletResponse response) {
        int idProprietaire = Integer.parseInt(request.getParameter("idProprietaire"));
        Proprietaire proprietaire = proprietaireDAO.find(idProprietaire);
        String nom, prenom;

        if ((nom = request.getParameter("txtnom")) != null && (prenom = request.getParameter("txtprenom")) != null) {
            proprietaire.setNomProprietaire(nom);
            proprietaire.setPrenomProprietaire(prenom);
            proprietaireDAO.update(proprietaire);
            request.setAttribute("owners", proprietaireDAO.findAll());
            //this.clearFlashMessages();
            //this.addFlashMessages(new FlashMessage("Modification effectuee", FlashMessageStatut.SUCCESS));
            return new ModelAndView("proprietaire/liste");
        } else {
            request.setAttribute("owner", proprietaire);
            return new ModelAndView("proprietaire/editer");
        }
    }

    @RequestMapping(value = "add")
    public ModelAndView addAction(HttpServletRequest request, HttpServletResponse response) {
        Proprietaire proprietaire = new Proprietaire();
        String nom, prenom;
        if ((nom = request.getParameter("txtnom")) != null && (prenom = request.getParameter("txtprenom")) != null) {
            proprietaire.setNomProprietaire(nom);
            proprietaire.setPrenomProprietaire(prenom);
            proprietaireDAO.insert(proprietaire);
            //this.clearFlashMessages();
            //this.addFlashMessages(new FlashMessage("Proprietaire ajoute", FlashMessageStatut.SUCCESS));
            request.setAttribute("owners", proprietaireDAO.findAll());
            return new ModelAndView("proprietaire/liste");
        } else {
            return new ModelAndView("proprietaire/add");
        }
    }

    @RequestMapping(value = "delete")
    public ModelAndView deleteAction(HttpServletRequest request, HttpServletResponse response) {
        int idProprietaire = Integer.parseInt(request.getParameter("idProprietaire"));
        proprietaireDAO.delete(idProprietaire);
        //this.clearFlashMessages();
        //this.addFlashMessages(new FlashMessage("Proprietaire supprime", FlashMessageStatut.SUCCESS));
        request.setAttribute("owners", proprietaireDAO.findAll());
        return new ModelAndView("proprietaire/liste");
    }
}
