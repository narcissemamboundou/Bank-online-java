/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Author: Na.mamboundou
 */
package jsf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author n.mamboundou
 */
@Named(value = "menuView")
@ViewScoped
public class MenuView implements Serializable {

    /**
     * Creates a new instance of MenuView
     */
   
     private MenuModel model;
  public MenuView() {
    }
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
 
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
 
         DefaultMenuItem item = new DefaultMenuItem("COMPTES");
       
        item.setCommand("#{menuView.listeCompteClient}");
        
        item.setCommand("#{menuView.listeOperations}");
        item.setUpdate("messages");
        secondSubmenu.addElement(item);
 
        item = new DefaultMenuItem("Virement et prelevement");
        item.setCommand("#{menuView.crediterCompte}");
        item.setCommand("#{menuView.virement}");
        item.setAjax(false);
        secondSubmenu.addElement(item);
 
        item = new DefaultMenuItem("Gestion de budget");
        item.setCommand("#{menuView.imageDepense}");
        secondSubmenu.addElement(item);
 
        model.addElement(secondSubmenu);
    }
 
    public MenuModel getModel() {
        return model;
    }
 
    public String listeCompteClient() {
        //doit renvoyer la liste des comptes du client connecté
        addMessage("Success", "liste des client");
        return "menu?faces-redirect=true";
    }
 
    public void listeOperations() {
        //doit renvoyer la liste des operations du client connecté
        addMessage("Success", "Liste des operations");
    }
 
    public String imageDepense() {
        addMessage("Success", "image des depenses");
        //doit renvoyer image des depense  du client connecté
        return "depenseCompte?faces-redirect=true";
    }
    public String crediterCompte() {
        //doit pouvoir crediter son compte
        addMessage("Success", "crediter le compte");
        return "crediterCompte?faces-redirect=true";
    }
    public String virement() {
        //doit pouvoir faire un virement 
        addMessage("Success", "virement");
        return "virementCompte?faces-redirect=true";
    }
 
 
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
