package com.epul.oeuvres.utilitaires;

/**
 * Created by Gaetan on 04/03/2017.
 */
public class FlashMessage {
    private String message;
    private FlashMessageStatut statut;

    public FlashMessage(String message, FlashMessageStatut statut) {
        this.message = message;
        this.statut = statut;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatut(FlashMessageStatut statut) {
        this.statut = statut;
    }

    public FlashMessageStatut getStatut() {
        return statut;
    }

    public String getMessage() {
        return message;
    }

    public String buildHtml() {
        String html = "<div class=\"alert " + this.getClassFromStatut(this.statut) + " alert-dismissable\">";
        html += "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>";
        html += this.message;
        html += "</div>";
        return html;
    }

    private String getClassFromStatut(FlashMessageStatut statut) {
        switch (statut) {
            case ERROR:
                return "alert-danger";
            case SUCCESS:
                return "alert-success";
            case WARNING:
                return "alert-warning";
            default:
                return "alert-info";
        }
    }
}
