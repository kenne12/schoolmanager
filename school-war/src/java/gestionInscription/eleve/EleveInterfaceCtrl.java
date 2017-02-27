/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.eleve;

/**
 *
 * @author Gervais
 */
public interface EleveInterfaceCtrl {

    /**
     * Cette méthode gère l'enregistrement d'un type de pénalité en base de
     * données
     */
    public void enregistrerEleve();

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

    /**
     * Gervais Méthode d'impression
     *
     * @since 17/07/15 10h07
     */
    public void imprimerElevePdf();

    /**
     * Gervais Méthode d'impression
     *
     * @since 17/07/15 10h07
     */
    public void imprimerEleveHtml();

    /**
     * Gervais: méthode permettant d'ajouter une image a la liste de celles du
     * batiment
     *
     * @return
     */
    public boolean handleAddImages();

}
