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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.OneToMany;
import org.primefaces.event.FlowEvent;
import session.gestionnaireClient;

/**
 *
 * @author n.mamboundou
 */
@Named(value = "clientManagedBean")
@ViewScoped
public class clientManagedBean  implements Serializable{

    @EJB
    private gestionnaireClient gClient;
    private boolean skip;
    private List<clientBancaire> listclient=new ArrayList();
    private List<CompteBancaire>listcomptesBancaireClient= new ArrayList<>();
    loginManagedBean login;
    
   private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;
    private String numeroTelephone;

   
    public List<CompteBancaire> getListcomptesBancaireClient(){
        return listcomptesBancaireClient;
    }

    /**
     * Creates a new instance of clientManagedBean
     */
    

    public clientManagedBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
    
    public String save() {        
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + this.prenom);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        gClient.creerUtilisateurByInfo(this.nom, this.prenom, this.adresse, this.mail, this.numeroTelephone);
        refreshCache();
         return "connecionClientTest?faces-redirect=true";
    }
    public String saveByClient() {        
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + this.prenom);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        gClient.creerUtilisateurByInfo(this.nom, this.prenom, this.adresse, this.mail, this.numeroTelephone);
        refreshCache();
         return "welcomeClient?faces-redirect=true";
    }
    
   
       public List<clientBancaire> getClient() {
        System.out.println("jsf get client");
        if (listclient.isEmpty()) {
            refreshCache();
        } else {
            System.out.println("J'utilise les données cahées");
        }
        return listclient;
    }

     public void refreshCache() {
        listclient = gClient.findAllBankAccountClient();
    }
     
     
      public List<clientBancaire> getClientConnected() {
        System.out.println("jsf get client");
        if (listclient.isEmpty()) {
            refreshCache();
        } else {
            System.out.println("J'utilise les données cahées");
        }
        return listclient;
    }

     public void refreshCache2() {
        listclient = gClient.findAllBankAccountClient();
    }
     
      public List<CompteBancaire> getListComptConnected() {
          int n =login.getIdClientConnect();
          System.out.println("la valeur de lID connecté est :"+n);
         return gClient.getComptesByID(n);
    }
     
     
     
    //methdes d'actions call by button or envents 
    public String creerDonneesClient() {
        System.out.println("JSF BEAN CREATION DE TEST");
        gClient.creerUtilisateur();
        refreshCache();
        return "accueil?faces-redirect=true";
    }
    
    public  String Allclient(){
        return "allClient?faces-redirect=true";
    }
    
    
    
    public void addCompteBancaireCourantById(int id3) {
        gClient.addNewCompteByID(id3,"cour");
    }
    
    public void addCompteBancaireWakById(int id3) {
        gClient.addNewCompteByID(id3,"wak");
    }
    
    public boolean isSkip() {
        return skip;
    }
     
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
    
}

