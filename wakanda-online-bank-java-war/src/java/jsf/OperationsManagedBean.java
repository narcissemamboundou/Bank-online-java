/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Author: Na.mamboundou
 */
package jsf;

import entities.CompteBancaire;
import entities.OperationBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.GestionnaireCompteBancaires;
import session.gestionnaireClient;

/**
 *
 * @author n.mamboundou
 */
@Named(value = "operationsManagedBean")
@ViewScoped
public class OperationsManagedBean implements Serializable{

    @EJB
    private gestionnaireClient gestionnaireClient;

    @EJB 
    private GestionnaireCompteBancaires gc;
    private List<CompteBancaire> listCompt = new ArrayList();
    private int id;

    public gestionnaireClient getGestionnaireClient() {
        return gestionnaireClient;
    }

    public void setGestionnaireClient(gestionnaireClient gestionnaireClient) {
        this.gestionnaireClient = gestionnaireClient;
    }
    
    private double montant;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    
    public List<OperationBancaire> getOperations(){
        return gc.getOperations(id);
    }
 
    public OperationsManagedBean() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GestionnaireCompteBancaires getGc() {
        return gc;
    }

    public void setGc(GestionnaireCompteBancaires gc) {
        this.gc = gc;
    }
    public String retier(int id_x) {
        try {
            System.out.println("retirer sur compte  :" + id + "  le montant  :" + montant+" vbr");
            gc.retirer(id_x, montant);
            refreshCache();
           
            addMessage("Success", "Retrait effectué");
        } catch (Exception e) {
            
            addMessage("Success", "Echec retrait");
        }
        return "OperationsManagedBean?faces-redirect=true";
    }
    public String crediterByID(int id_x) {
        try {
            System.out.println("retirer sur compte  :" + id + "  le montant  :" + montant+" vbr");
            gc.deposer(id_x, montant);
            refreshCache();
           
            addMessage("Success", "depot effectué");
        } catch (Exception e) {   
            
            addMessage("Success", "Echec retrait");
        }
        return "OperationsManagedBean?faces-redirect=true";
    }
    
    
    public void addMessage(String summary, String detail) {
        FacesMessage messageo = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, messageo);
    }
    public void refreshCache() {
        listCompt = gc.findAllBankAccount();
    }
    
}
