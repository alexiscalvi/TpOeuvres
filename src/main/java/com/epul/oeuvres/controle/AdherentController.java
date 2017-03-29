package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.AdherentDAO;
import com.epul.oeuvres.metier.Adherent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lucas on 29/03/2017.
 *
 */
@RequestMapping("adherent/")
@Controller
public class AdherentController  {
    private final AdherentDAO adherentDAO;

    public AdherentController() {
        super();
        this.adherentDAO = new AdherentDAO();
    }

    @RequestMapping(value = "liste", method = RequestMethod.GET)
    public ModelAndView liste(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(("here"));
        request.setAttribute("mesAdherents", adherentDAO.findAll());
        return new ModelAndView("adherent/liste");
    }

    @RequestMapping(value = "edit")
    public ModelAndView editAction(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("idAdheren"));
        int idAdherent = Integer.parseInt(request.getParameter("idAdherent"));
        Adherent adherent = adherentDAO.find(idAdherent);
        String nom, prenom, ville;
        if ((nom = request.getParameter("txtnom")) != null
                && (prenom = request.getParameter("txtprenom")) != null
                && (ville = request.getParameter("txtville")) != null) {
            adherent.setNomAdherent(nom);
            adherent.setPrenomAdherent(prenom);
            adherent.setVilleAdherent(ville);
            adherentDAO.update(adherent);
            return liste(request, response);
        } else {
            request.setAttribute("adherent", adherent);
            return new ModelAndView("adherent/edit");
        }

    }
    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("je passe par la");
        Adherent adherent = new Adherent();
        String nom, prenom, ville;
        if ((nom = request.getParameter("txtnom")) != null
                && (prenom = request.getParameter("txtprenom")) != null
                && (ville = request.getParameter("txtville")) != null) {
            adherent.setNomAdherent(nom);
            adherent.setPrenomAdherent(prenom);
            adherent.setVilleAdherent(ville);
            adherentDAO.insert(adherent);
            return liste(request, response);
        } else {
            return new ModelAndView("adherent/add");
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        int idAdherent = Integer.parseInt(request.getParameter("idAdherent"));
        adherentDAO.delete(idAdherent);
        return liste(request, response);
    }
}
