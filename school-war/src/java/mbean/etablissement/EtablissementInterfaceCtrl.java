/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.etablissement;

/**
 *
 * @author Gervais
 */
public interface EtablissementInterfaceCtrl {

    /**
     * Cette méthode gère l'enregistrement d'un type de pénalité en base de
     * données
     */
    public void enregistrerEtablissement();

    /**
     * Modifier le type de pénalité sélectionné
     */
    public void modifier();

    /**
     * Supprimer le type de pénalité sélectionné Si le type de pénalité n'est
     * pas utilisé alors il est supprimé. Il serra désactivé dans le cas
     * contraire
     */
    public void supprimer();

}
