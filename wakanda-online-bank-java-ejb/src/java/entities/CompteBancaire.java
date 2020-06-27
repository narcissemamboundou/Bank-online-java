/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
/**
 *
 * @author n.mamboundou
 */

@Entity
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    /*declaration  des attributs*/
    private int id;
    private String nom;
    private double solde;
    private String numeroCompte;

    @OneToMany(cascade = CascadeType.PERSIST)
    @OrderBy("dateOperation ASC")
    List<OperationBancaire> listeOperations = new ArrayList<OperationBancaire>();

    /*constructeur par default*/
    public CompteBancaire() {
    }

    /*constructeur chargé*/
    public CompteBancaire(String nom,double solde1, String bool) {
        this.solde = solde1;
        this.nom = nom;
        this.libeleCompte=libeleByBool(bool);
        /// OperationBancaire operation = new OperationBancaire("Creation du compte");
        this.numeroCompte=numeroCompte(bool);
        addOperation(new OperationBancaire("Creation du compte"));
    }
    private String libeleByBool(String  x){
        String resultats="error";
        if(x.trim().equals("wak")){
            resultats="Compte Wakandais";
        }
        else if(x.trim().equals("cour")){
            resultats="Compte Courant";
        }
        return resultats;
    }
    public CompteBancaire(String bool) {
        this.solde = 100;
        this.libeleCompte=libeleByBool(bool);
        this.numeroCompte=numeroCompte(bool);
        addOperation(new OperationBancaire("Creation du compte"));
        
    }

    public List<OperationBancaire> getListeOperations() {
        return listeOperations;
    }

    private void addOperation(OperationBancaire operation) {
        listeOperations.add(operation);
    }

    public CompteBancaire(int id1, double montant) {
        this.id = id1;
        this.solde += montant;
    }

    /*Les methodes*/
    public void deposerArgent(double montant) {
        this.solde += montant;
        addOperation(new OperationBancaire("Créditer le montant :" + montant+" vbr"));
    }

    public void retirerArgent(double montant) {
        this.solde -= montant;
        addOperation(new OperationBancaire("Débiter le montant :" + montant+" vbr"));
    }

    
    private  String numeroCompte(String x) {
                
		Random rand = new Random();
		String resultat="error";
		if(x.trim().equals("wak")) {
			//compte wakandais
			resultat="WAK "+Math.abs(rand.nextLong())+" VBR64";
		}
                else if(x.trim().equals("cour")) {
			//compte courant
			resultat="0000"+Math.abs(rand.nextInt())+" FR64";
		}
		return resultat;		
	}
     public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    private String libeleCompte;

    public String getLibeleCompte() {
        return libeleCompte;
    }
    
    public void setLibeleCompte(String libeleCompte) {
        this.libeleCompte = libeleCompte;
    }
      public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNomProprietaire() {
        return nom;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nom = nomProprietaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompteBancaire[ id=" + id + " ]";
    }
  
}
