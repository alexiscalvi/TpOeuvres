package com.epul.oeuvres.controle;

import com.epul.oeuvres.utilitaires.FlashMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu on 14/03/2017.
 */
public class Controller
{

    private List<FlashMessage> flashMessages = new ArrayList<FlashMessage>();

    protected List<FlashMessage> getFlashMessages() {
        return flashMessages;
    }

    protected void addFlashMessages(FlashMessage flashMessage) {
        this.flashMessages.add(flashMessage);
    }

    private String getFlashMessagesHtml() {
        String html = "";
        for (FlashMessage flashMessage :this.getFlashMessages()) {
            html += flashMessage.buildHtml();
        }
        return html;
    }

    protected void clearFlashMessages() {
        this.flashMessages.clear();
    }
}
