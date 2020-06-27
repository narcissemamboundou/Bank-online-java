/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.CompteBancaire;
import entities.clientBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import session.GestionnaireCompteBancaires;
import session.gestionnaireClient;

/**
 *
 * @author n.mamboundou
 */
@Named(value = "comptesManagedBean")
@ViewScoped
public class ComptesManagedBean implements Serializable {

    @EJB
    private gestionnaireClient gestionnaireClient;

    @EJB
    private GestionnaireCompteBancaires gcbancaire;
    loginManagedBean login;
    private List<CompteBancaire> listCompt = new ArrayList();
    private List<CompteBancaire> listComptConnected = new ArrayList();
    private LazyDataModel<CompteBancaire> lazyModel;
    private int id;
    private int idEmeteur;
    private int idRecepteur;
    private double montant;
    private String message;

    
    public void setListComptConnected(List<CompteBancaire> listComptConnected) {
        this.listComptConnected = listComptConnected;
    }
    
    /**
     * Creates a new instance of ComptesManagedBean
     */
    public ComptesManagedBean() {
        /* 
       System.out.println("dans le constructeur ");
        lazyModel = new LazyDataModel<CompteBancaire>() {
            @Override
            public List load(int start, int nombreMax, String filterColumn, SortOrder sort, Map map) {
                //recuperation de tous les  comptes avec les paramettres 
                System.out.println("load start: "+start+" nom colone: "+filterColumn);
                return gcbancaire.findRange(start, nombreMax, filterColumn, sort.toString());
            }
            public int getrowCount() {
                return gcbancaire.count();
            }
        };
         */
    }

    public gestionnaireClient getGestionnaireClient() {
        return gestionnaireClient;
    }

    public void setGestionnaireClient(gestionnaireClient gestionnaireClient) {
        this.gestionnaireClient = gestionnaireClient;
    }
    
    public LazyDataModel<CompteBancaire> getLazyModel() {
        return lazyModel;
    }

    public int getIdEmeteur() {
        return idEmeteur;
    }

    public void setIdEmeteur(int idEmeteur) {
        this.idEmeteur = idEmeteur;
    }

    public int getIdRecepteur() {
        return idRecepteur;
    }

    public void setIdRecepteur(int idRecepteur) {
        this.idRecepteur = idRecepteur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }
    /* //lazyModel 
    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<CompteBancaire>() {
            @Override
            public List load(int start, int endd, String filterColumn, SortOrder sort, Map map) {
                //recuperation de tous les  comptes avec les paramettres 
                return (gcbancaire.findRange(start, endd, filterColumn, sort.toString()));
            }
            
            public int getrowCount() {
                return gcbancaire.count();
            }
        };
    }
     */
 /*palier au ob de tri*/
    
    public List<CompteBancaire> getCompt() {
        System.out.println("jsf get compte");
        if (listCompt.isEmpty()) {
            refreshCache();
        } else {
            System.out.println("J'utilise les données cahées");
        }
        return listCompt;
    }
    public void refreshCache() {
        listCompt = gcbancaire.findAllBankAccount();
    }
    
      ///a enlever --> enlever   
   public List<CompteBancaire> getComptConnected(int id) {
        System.out.println("jsf get compte");
        if (listComptConnected.isEmpty()) {
            gcbancaire.findAllAccountByIdClientConnected(id);
        } else {
            System.out.println("J'utilise les données cahées");
        }
        return listComptConnected;
    }
   
    //methdes d'actions call by button or envents 
    public String creerDonneesTests() {
        System.out.println("JSF BEAN CREATION DE TEST");
        gcbancaire.creerComptesTest();
        refreshCache();
        return "accueil?faces-redirect=true";
    }

    public String deposer() {
        try {
            System.out.println("deposer sur compte  :" + id + "  le montant  :" + montant);
            gcbancaire.deposer(id, montant);
            refreshCache();
       
            addMessage("Success", "Compte crédité");
        } catch (Exception e) {
           
           addMessage("Success", "Echec creditation");
        }
        return "accueil?faces-redirect=true";
    }
     public String deposer1() {
        try {
            System.out.println("deposer sur compte  :" + id + "  le montant  :" + montant);
            gcbancaire.deposer(id, montant);
            refreshCache();
       
            addMessage("Success", "Compte crédité");
        } catch (Exception e) {
           
           addMessage("Success", "Echec creditation");
        }
        return "menu?faces-redirect=true";
    }

    public String retier() {
        try {
            System.out.println("retirer sur compte  :" + id + "  le montant  :" + montant+" vbr");
            gcbancaire.retirer(id, montant);
            refreshCache();
           
            addMessage("Success", "Retrait effectué");
        } catch (Exception e) {
            
            addMessage("Success", "Echec retrait");
        }
        return "accueil?faces-redirect=true";
    }

    public String transferer() {
        try {
            System.out.println("transfert de :" + montant + " de l'id  :" + idEmeteur + " vers l'ID " + idRecepteur);
            gcbancaire.transferer(idEmeteur, idRecepteur, montant);
            refreshCache();
            
            addMessage("Success", "transfer reussi");
        } catch (Exception e) {
            addMessage("fail", "Erreur transfer");
        }
        return "accueil?faces-redirect=true";
    }
    public String transferer1() {
        try {
            System.out.println("transfert de :" + montant + " de l'id  :" + idEmeteur + " vers l'ID " + idRecepteur);
            gcbancaire.transferer(idEmeteur, idRecepteur, montant);
            refreshCache();
            
            addMessage("Success", "transfer reussi");
        } catch (Exception e) {
            addMessage("fail", "Erreur transfer");
        }
        return "menu?faces-redirect=true";
    }

    public String ShowOperation(int id) {
        System.out.println("AFFICHER OPERATION DU COMPTE " + id);
        return "operationsListe?id="+id+"&amp;faces-redirect=true";
    }

    public void suprimerCompteBancaire(CompteBancaire cb) {
        //si le compte bancaire n'est pas connecter on ne peux pas le sup 
        //on va donc d'abord le reconnecter 
        gcbancaire.suprimerCompte(cb);
        refreshCache();
        /*FacesContext context = FacesContext.getCurrentInstance();
        message = "compte wakandais supprimer";
        context.addMessage(null, new FacesMessage("Successful1", "INFO: " + message));*/
        addMessage("Success", "Compte supprimer deleted");
        
    }
    public void addMessage(String summary, String detail) {
        FacesMessage messageo = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, messageo);
    }
    public List<CompteBancaire> getListClientConnectedd(int idm){
        return gestionnaireClient.getComptesByID(idm);
    }
     public List<CompteBancaire> getListComptConnected() {
         return gestionnaireClient.getComptesByID(login.getIdClientConnect());
    }
 
  
}
