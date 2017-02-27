/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.motif;

/**
 *
 * @author Gervais
 */
public interface MotifInterfaceCtrl {

    /**
     * Cette méthode gère l'enregistrement d'un type de pénalité en base de
     * données
     */
    public void enregistrerMotif();

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
    public void imprimerMotifPdf();

    /**
     * Gervais Méthode d'impression
     *
     * @since 17/07/15 10h07
     */
    public void imprimerMotifHtml();

}
