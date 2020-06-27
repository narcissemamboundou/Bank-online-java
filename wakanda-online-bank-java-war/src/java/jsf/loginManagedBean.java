/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.clientBancaire;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.gestionnaireClient;

/**
 *
 * @author n.mamboundou
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class loginManagedBean implements Serializable {

    @EJB
    private gestionnaireClient gClient;
    private String mail;
    private String message;
    private clientBancaire user;
    private boolean connection;
    private String motDePasse;
    private int idClient;

  
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


    public loginManagedBean(String mail, String motDePasse) {
        this.mail = mail;
        this.motDePasse = motDePasse;
    }

    public String loginClient() {

        clientBancaire client = gClient.login(mail, motDePasse);

        if (client == null) {
            connection = false;
            message = "Erreur d'authentification";
            return null;
        } else {
            connection = true;
            
            message = "Authentification réussie";
            String check = client.getMail();
            if (check == "lesas@nice.fr") {
                return "menu?faces-redirect=true";
            } 
            String check1 = client.getMail();
            if(check1 == "admin") {
                return "accueil?faces-redirect=true";
            }else{
                 return "menu?faces-redirect=true";
            }
        }
        
    }
    
    public int getIdClientConnect(){
        clientBancaire client = gClient.login(mail, motDePasse);
        return client.getId()+1;
    }
  
    public int getIdClientConnected(clientBancaire client){
        System.out.println("id client connecté");
         client =gClient.login(mail, motDePasse);
         System.out.println("id client connecté"+ client.getMail()+" "+client.getNomProprietaire());
       return client.getId();
    }
    
      
    public String creercompteByClient(){
        return "indexClient?faces-redirect=true";
    }
 
    
    public clientBancaire getClientConnectedByLogin(){
        return gClient.login(mail, motDePasse);
    }
    
    public String deconnexion(){
        System.out.println("deconnectionnnnnnnnnnn");
        return "connecionClientTest?faces-redirect=true";
    }
    
    
    public boolean isConnection() {
        return connection;
    }

    public clientBancaire getUser() {
        return user;
    }

    public void setUser(clientBancaire user) {
        this.user = user;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public loginManagedBean() {
    }
    public void logout() {
     	FacesContext context = FacesContext.getCurrentInstance();
     	context.getExternalContext().invalidateSession();
         try {
 context.getExternalContext().redirect("connecionClientTest.xhtml");
 } catch (IOException e) {
 e.printStackTrace();
 }
     }

}
