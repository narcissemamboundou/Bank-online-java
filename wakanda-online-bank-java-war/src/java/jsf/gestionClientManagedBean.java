/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.CompteBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.gestionaireCompteClient;

/**
 *
 * @author n.mamboundou
 */
@Named(value = "gestionClientManagedBean")
@ViewScoped
public class gestionClientManagedBean implements Serializable{

    @EJB
    private gestionaireCompteClient gCompteClient;
    private loginManagedBean clientconnected;
    private List<CompteBancaire> listComptClient;

    public gestionClientManagedBean() {
    }

    public gestionaireCompteClient getgCompteClient() {
        return gCompteClient;
    }

    public void setgCompteClient(gestionaireCompteClient gCompteClient) {
        this.gCompteClient = gCompteClient;
    }

    public List<CompteBancaire> getListComptClient() {
        return listComptClient;
    }

    public void setListComptClient(List<CompteBancaire> listComptClient) {
        this.listComptClient = listComptClient;
    }
    public List<CompteBancaire> getListClientConnected(int idm){
        return gCompteClient.getCompesById(idm);
    }
    

       
   
    
      public loginManagedBean getClientconnected() {
        return clientconnected;
    }


    public List<CompteBancaire> getCompt() {
        System.out.println("jsf get compte");
        if (listComptClient.isEmpty()) {
            refreshCache();
        } else {
            System.out.println("J'utilise les données cahées");
        }
        return listComptClient;
    }
    
    public void refreshCache() {
        listComptClient = gCompteClient.findAllBankAccountById(clientconnected.getIdClient());
    }
    
}
