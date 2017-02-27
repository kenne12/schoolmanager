/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.elementevaluation;

/**
 *
 * @author Gervais
 */
public interface ElementEvaluationInterfaceCtrl {

    /**
     * Cette méthode gère l'enregistrement d'un type de pénalité en base de
     * données
     */
    public void save();

    /**
     * Modifier le type de pénalité sélectionné
     */
    public void edit();

    /**
     * Supprimer le type de pénalité sélectionné Si le type de pénalité n'est
     * pas utilisé alors il est supprimé. Il serra désactivé dans le cas
     * contraire
     */
    public void delete();

    /**
     * Gervais Méthode d'impression
     *
     * @since 17/07/15 10h07
     */
    public void imprimerMatierePdf();

    /**
     * Gervais Méthode d'impression
     *
     * @since 17/07/15 10h07
     */
    public void imprimerMatiereHtml();

}
