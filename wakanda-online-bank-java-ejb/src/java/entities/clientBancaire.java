/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.transaction.UserTransaction;

/**
 *
 * @author n.mamboundou
 */
@Entity
public class clientBancaire implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;
    private String motDePasse;
    private String numeroTelephone;
    @OneToMany(cascade = CascadeType.PERSIST)
    List<CompteBancaire> listeComptes = new ArrayList<CompteBancaire>();
        
    private void addCompteBancaire(CompteBancaire cb) {
        listeComptes.add(cb);
    }
   
    public clientBancaire() {
       
    }
    public void addNewCompte(CompteBancaire cb){
        listeComptes.add(cb);
    }
    public clientBancaire(String type) {
        addCompteBancaire(new CompteBancaire(this.nom+" "+this.prenom, 15000,"wak"));
    }

    public List<CompteBancaire> getListeComptes() {
        return listeComptes;
    }

    public void setListeComptes(List<CompteBancaire> listeComptes) {
        this.listeComptes = listeComptes;
    }
    
    public String getNumeroTelephone() {
        return numeroTelephone;
    }
    
    
    
    public clientBancaire(String nom, String prenom, String adresse, String mail, String numeroTelephone) {
        System.out.println("client test");
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.motDePasse="newuser";
        this.numeroTelephone = numeroTelephone;
        addCompteBancaire(new CompteBancaire(nom+" "+prenom, 15000,"wak"));
        addCompteBancaire(new CompteBancaire(nom+" "+prenom, 15000,"cour"));
    }
    
     public void clientBancaireByType(String adr) {
       
        addCompteBancaire(new CompteBancaire(this.nom+" "+this.prenom, 15000,"adr"));
    }
     public void getInfoClientById(int i){
         
     } 
     private void addOperation(CompteBancaire compte) {
        listeComptes.add(compte);
    }
    //public clientBancaire(String nom) {
    //    this.nom = nom;
    //}
    public clientBancaire(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getAdresse() {
        return adresse;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clientBancaire)) {
            return false;
        }
        clientBancaire other = (clientBancaire) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.clientBancaire[ id=" + id + " ]";
    }

   
   /* public List<CompteBancaire> getlisteComptes() {
        return listeComptes;
    }
*/
    public String getNomProprietaire() {
        return  nom +" "+prenom;
    } 

    public List<CompteBancaire> listeComptes() {
        return listeComptes;
    }
    
}
