/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.sessionController;

/**
 *
 * @author Gervais
 */
public interface SessionInterfaceCtrl {

    /**
     * Cette méthode gère l'enregistrement d'un type de pénalité en base de
     * données
     */
    public String authentifier();

    /**
     * Modifier le type de pénalité sélectionné
     */
    public String deconnecter();

    public void privilege();

    /**
     * Supprimer le type de pénalité sélectionné Si le type de pénalité n'est
     * pas utilisé alors il est supprimé. Il serra désactivé dans le cas
     * contraire
     */
}
