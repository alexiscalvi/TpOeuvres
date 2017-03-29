package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.*;
import com.epul.oeuvres.metier.Adherent;
import com.epul.oeuvres.metier.Oeuvrevente;
import com.epul.oeuvres.metier.Proprietaire;
import com.epul.oeuvres.metier.Reservation;
import com.epul.oeuvres.utilitaires.FlashMessage;
import com.epul.oeuvres.utilitaires.FlashMessageStatut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;


@Controller
@RequestMapping("oeuvrevente/")
public class OeuvreventeController {

    private OeuvreventeDAO oeuvreventeDAO;

    public OeuvreventeController() {
        super();
        this.oeuvreventeDAO = new OeuvreventeDAO();
    }
    @RequestMapping(value = "liste")
    public ModelAndView listeAction(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("idAdherent") != null && request.getParameter("idOeuvrevente") != null) {
            FlashMessage flashMessage = this.reservationSubAction(request, response);
            //this.clearFlashMessages();
            //this.addFlashMessages(flashMessage);
        }

        List<Oeuvrevente> oeuvreventes = this.oeuvreventeDAO.findAll();
        List<Adherent> adherents = new AdherentDAO().findAll();
        request.setAttribute("oeuvreventes", oeuvreventes);
        request.setAttribute("adherents", adherents);
        System.out.println("je passe par la");
        return new ModelAndView("oeuvrevente/liste");
    }

    @RequestMapping(value = "add")
    public ModelAndView addAction(HttpServletRequest request, HttpServletResponse response) {
        String titre;
        Integer idProprietaire;
        float prix;
        titre = request.getParameter("txttitre");
        if(titre != null && request.getParameter("numberprix")!=null && request.getParameter("idProprietaire")!=null){
            //Si le formulaire est valide
            prix = Float.parseFloat(request.getParameter("numberprix"));
            idProprietaire = Integer.parseInt(request.getParameter("idProprietaire"));
            Oeuvrevente oeuvrevente = new Oeuvrevente();
            oeuvrevente.setEtatOeuvrevente("L");
            oeuvrevente.setTitreOeuvrevente(titre);
            oeuvrevente.setPrixOeuvrevente(prix);
            ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
            Proprietaire proprietaire = proprietaireDAO.find(idProprietaire);
            oeuvrevente.setProprietaire(proprietaire);
            OeuvreventeDAO oeuvreventeDAO = new OeuvreventeDAO();
            oeuvreventeDAO.insert(oeuvrevente);
            //this.clearFlashMessages();
            //this.addFlashMessages(new FlashMessage("Ajout d'une oeuvre", FlashMessageStatut.SUCCESS));
            return this.listeAction(request, response);
        }

        request.setAttribute("proprietaires", new ProprietaireDAO().findAll());

        return new ModelAndView("oeuvrevente/add");

    }

    @RequestMapping(value = "editer")
    public ModelAndView editerAction(HttpServletRequest request, HttpServletResponse response) {
        int idOeuvrevente = parseInt(request.getParameter("idOeuvrevente"));
        Oeuvrevente oeuvrevente = oeuvreventeDAO.find(idOeuvrevente);
        String titre;
        Integer idProprietaire;
        float prix;
        titre = request.getParameter("txttitre");
        if(titre != null && request.getParameter("numberprix")!=null && request.getParameter("idProprietaire")!=null){
            prix = Float.parseFloat(request.getParameter("numberprix"));
            idProprietaire = Integer.parseInt(request.getParameter("idProprietaire"));
            oeuvrevente.setEtatOeuvrevente("L");
            oeuvrevente.setTitreOeuvrevente(titre);
            oeuvrevente.setPrixOeuvrevente(prix);
            ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
            Proprietaire proprietaire = proprietaireDAO.find(idProprietaire);
            oeuvrevente.setProprietaire(proprietaire);
            OeuvreventeDAO oeuvreventeDAO = new OeuvreventeDAO();
            oeuvreventeDAO.updateOeuvrevente(oeuvrevente);
            //this.clearFlashMessages();
            //this.addFlashMessages(new FlashMessage("Modification d'une oeuvre", FlashMessageStatut.SUCCESS));
            return this.listeAction(request, response);
        }else{
            request.setAttribute("txttitre", oeuvrevente.getTitreOeuvrevente());
            request.setAttribute("txttitre", oeuvrevente.getTitreOeuvrevente());
            request.setAttribute("numberprix", oeuvrevente.getPrixOeuvrevente());
            request.setAttribute("idProprietaire", oeuvrevente.getProprietaire().getIdProprietaire());
            request.setAttribute("proprietaires", new ProprietaireDAO().findAll());
            request.setAttribute("idOeuvrevente", idOeuvrevente);
            return new ModelAndView("oeuvrevente/editer");
        }
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detailAction(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        Oeuvrevente oeuvrevente = this.oeuvreventeDAO.find(id);
        request.setAttribute("oeuvrevente", oeuvrevente);
        return new ModelAndView("oeuvrevente/detail");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ModelAndView deleteAction(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        System.out.println(""+id);
        oeuvreventeDAO.delete(id);
        //this.clearFlashMessages();
        //this.addFlashMessages(new FlashMessage("Oeuvre vente supprimee", FlashMessageStatut.SUCCESS));
        List<Oeuvrevente> oeuvreventes = this.oeuvreventeDAO.findAll();
        List<Adherent> adherents = new AdherentDAO().findAll();
        request.setAttribute("oeuvreventes", oeuvreventes);
        request.setAttribute("adherents", adherents);
        System.out.println("je passe par la");
        return new ModelAndView("oeuvrevente/liste");
    }

    public FlashMessage reservationSubAction(HttpServletRequest request, HttpServletResponse response) {
        int idAdherent = parseInt(request.getParameter("idAdherent"));
        int idOeuvrevente = parseInt(request.getParameter("idOeuvrevente"));
        Adherent adherent = new AdherentDAO().find(idAdherent);
        Oeuvrevente oeuvrevente = this.oeuvreventeDAO.find(idOeuvrevente);
        oeuvrevente.setEtatOeuvrevente("R");
        this.oeuvreventeDAO.updateOeuvrevente(oeuvrevente);
        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        reservation.setOeuvrevente(oeuvrevente);
        reservation.setStatut("confirmee");
        if (new ReservationDAO().insert(reservation) == -1) { // If Reservation already done
            String message = "Reservation impossible (Reservation de " + adherent.getNomComplet()+" deja existante)";
            return new FlashMessage(message, FlashMessageStatut.ERROR);
        }
        return new FlashMessage("Reservation bien prise en compte", FlashMessageStatut.SUCCESS);
    }

}
