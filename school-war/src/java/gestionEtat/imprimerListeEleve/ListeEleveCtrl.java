/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEtat.imprimerListeEleve;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Annee;
import entities.Classe;
import entities.Classecategorie;
import entities.Eleveanneeclasse;
import entities.Personnel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "listeEleveCtrl")
@SessionScoped
public class ListeEleveCtrl extends AbstractListeEleveCtrl implements ListeEleveInterfaceCtrl, Serializable {

    @PostConstruct
    private void initEvaluation() {
        personnel = new Personnel();
        annee = anneeFacade.findByEtatSingle(true);
        classe = new Classe();
        categorie = new Classecategorie();
        classeCategorie = new Classecategorie();
        eleveAnneeClasse = new Eleveanneeclasse();
        selectedEleveAnneeClasse = new Eleveanneeclasse();
    }

    private void initCreate() {

    }

    @Override
    public void modifier() {

    }

    @Override
    public void supprimer() {

    }

    @Override
    public void imprimerListeClasse() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user")) {
            if (classe != null) {
                Annee an = anneeFacade.findByEtatSingle(true);
                if (an != null) {
                    List<Eleveanneeclasse> elevs = eleveAnneeClasseFacade.findByAnneeClasse(an.getIdannee(), classe.getIdclasse());
                    if (!elevs.isEmpty()) {

                        try {
                            String listeName = "liste_des_eleve_" + classe.getNom() + ".pdf";
                            Document liste = new Document();
                            liste.setMargins(2, 2, 2, 2);
                            PdfWriter.getInstance(liste, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefautClasse + "/" + listeName));
                            liste.open();

                            PdfPTable table = new PdfPTable(4);
                            table.setComplete(true);

                            table.addCell(PrintUtils.createPdfPCell("Liste des élèves de la " + classe.getNom() + " Année Scolaire : " + an.getCode() + " - " + (an.getCode() + 1), 4, true));

                            table.addCell(PrintUtils.createPdfPCell("NOM", true, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));
                            table.addCell(PrintUtils.createPdfPCell("PRENOM", true, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));
                            table.addCell(PrintUtils.createPdfPCell("MATRICULE", true, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));
                            table.addCell(PrintUtils.createPdfPCell("DATE DE NAISSANCE", true, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL)));

                            //debut d'ecriture des lignes;
                            for (int i = 0; i < elevs.size(); i++) {
                                table.addCell(PrintUtils.createPdfPCell("" + elevs.get(i).getEleve().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
                                table.addCell(PrintUtils.createPdfPCell("" + elevs.get(i).getEleve().getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
                                table.addCell(PrintUtils.createPdfPCell("" + elevs.get(i).getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
                                table.addCell(PrintUtils.createPdfPCell("" + elevs.get(i).getEleve().getDatenaissance().getDay() + "-" + elevs.get(i).getEleve().getDatenaissance().getMonth() + "-" + elevs.get(i).getEleve().getDatenaissance().getYear(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
                            }

                            liste.add(table);

                            liste.close();

                            JsfUtil.addSuccessMessage("Impression réussie !");
                        } catch (FileNotFoundException | DocumentException ex) {
                            Logger.getLogger(ListeEleveCtrl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JsfUtil.addErrorMessage("La classe selectionnée n'a aucune !");
                    }
                } else {
                    JsfUtil.addFatalErrorMessage("L'année en cours n'est pas parametrée");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une classe !");
            }
        } else {
            String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            } catch (IOException ex) {
                Logger.getLogger(ListeEleveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void handleEleveChange() {
        Annee an = anneeFacade.findByEtatSingle(true);
        if (categorie.getId() != null) {
            categorie = classecategorieFacadeLocal.find(categorie.getId());
            eleveAnneeClasses = eleveAnneeClasseFacade.findByAnneeClasse(an.getIdannee(), categorie.getIdclasse().getIdclasse());
        } else {
            eleveAnneeClasses.clear();
        }
    }
}
