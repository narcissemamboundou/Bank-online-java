/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import session.GestionnaireCompteBancaires;
import session.gestionnaireClient;

/**
 *
 * @author n.mamboundou
 */
@Singleton
@LocalBean
@Startup
public class initBD {

    @EJB
    private gestionnaireClient gClient;

    @EJB
    private GestionnaireCompteBancaires gc;
    
    
    public initBD() {
        System.out.println("###### INIT BD");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    //fonction appeler juste apres le constructeur / la creation de EJB
    public void remplirBD(){
        System.out.println("appel de la facade");
        //gc.creerComptesTest();
        gClient.creerUtilisateur();
    }
}
