/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEtat.imprimerBulletin;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Annee;
import entities.Classe;
import entities.Classecategorie;
import entities.Classematiere;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Evaluation;
import entities.Matiere;
import entities.Naturematiere;
import entities.Personnel;
import entities.Sequenceannee;
import entities.Trimesteannee;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "bulletinCtrl")
@SessionScoped
public class BulletinCtrl extends AbstractBulletinCtrl implements BulletinInterfaceCtrl, Serializable {

    @PostConstruct
    private void initEvaluation() {
        selectedEvaluation = new Evaluation();
        evaluation = new Evaluation();
        matiere = new Matiere();
        personnel = new Personnel();
        annee = anneeFacade.findByEtatSingle(true);
        trimestre = new Trimesteannee();
        sequence = new Sequenceannee();
        eleve = new Eleve();
        pickList = new DualListModel<>(eleves, eleveTarget);
        classe = new Classe();
        categorie = new Classecategorie();
        classeCategorie = new Classecategorie();
        eleveAnneeClasse = new Eleveanneeclasse();
    }

    private void initCreate() {
        evaluation.setEleve(eleve);

        evaluation.setPersonnel(personnel);
    }

    @Override
    public void enregistrerBulletin() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user")) {

            if (eleveAnneeClasse.getId() != null) {
                classematieres.clear();
                matieres.clear();
                notesPropres.clear();
                matiereCompses.clear();
                eleveAnneeClasse = eleveAnneeClasseFacade.find(eleveAnneeClasse.getId());
                if (sequence != null) {
                    Annee an = anneeFacade.findByEtatSingle(true);
                    Eleveanneeclasse eleveClasse = eleveAnneeClasseFacade.getEleveAnneeClaseByAnneClasse(eleveAnneeClasse.getEleve().getIdeleve(), an.getIdannee());
                    if (eleveClasse != null) {
                        List<Naturematiere> nature = natureMatiereFacade.findAll();
                        if (!nature.isEmpty()) {
                            List<Evaluation> notes = evaluationFacadeLocal.getByEleveAnneeSequence(eleveAnneeClasse.getEleve().getIdeleve(), an.getIdannee(), sequence.getIdsequencean());

                            /*if (!notes.isEmpty()) {*/
                            try {

                                String bulletinName = "" + eleveAnneeClasse.getEleve().getNom() + "_" + eleveAnneeClasse.getEleve().getPrenom() + "_" + sequence.getIdsequence().getNom() + ".pdf";
                                Document bulletin = new Document();

                                PdfWriter.getInstance(bulletin, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefautBulletin + "/" + bulletinName));

                                bulletin.setMargins(5, 5, 5, 5);
                                bulletin.addCreator("School Manager");
                                bulletin.setPageSize(PageSize.A4);

                                bulletin.open();

                                //tableau qui va contenir les notes
                                PdfPTable table = new PdfPTable(5);
                                table.setComplete(true);

                                //entete de bulletin
                                table.addCell(PrintUtils.createPdfPCell("COLLEGE POZAM Année Scolaire " + an.getCode() + " - " + (an.getCode() + 1) + " " + an.getTheme(), 5, detail));
                                table.addCell(PrintUtils.createPdfPCell("BULLETIN DE NOTES DE : " + sequence.getIdsequence().getNom(), 5, true));
                                table.addCell(PrintUtils.createPdfPCell("Nom : " + eleveAnneeClasse.getEleve().getNom(), 2, false));

                                PdfPCell cellLogo = new PdfPCell(new Paragraph("  "));
                                cellLogo.setRowspan(2);
                                table.addCell(cellLogo);

                                table.addCell(PrintUtils.createPdfPCell("Matricule : " + eleveAnneeClasse.getEleve().getMatricule(), 2, false));

                                table.addCell(PrintUtils.createPdfPCell("Prénom : " + eleveAnneeClasse.getEleve().getPrenom(), 2, false));

                                //table.addCell("kenne");
                                PdfPCell cellClasse = new PdfPCell(new Paragraph("Classe : " + eleveClasse.getIdclasse().getNom()));
                                cellClasse.setColspan(2);
                                table.addCell(cellClasse);

                                //debut de saisis de notes
                                PdfPTable entete = new PdfPTable(5);
                                table.addCell(new Paragraph("Dicipline"));
                                table.addCell(PrintUtils.createPdfPCell("Note ", true));
                                table.addCell(PrintUtils.createPdfPCell("Coéfficient ", true));
                                table.addCell(PrintUtils.createPdfPCell("Total", true));
                                table.addCell(PrintUtils.createPdfPCell("Appréciation", true));

                                //partie qui contient es totaux
                                PdfPTable total = new PdfPTable(5);

                                int totalCoef = 0;
                                float totalPoint = 0;

                                classematieres = classeMatiereFacade.get(eleveAnneeClasse.getIdclasse().getIdclasse());
                                if (!classematieres.isEmpty()) {
                                    for (Classematiere m : classematieres) {
                                        matieres.add(m.getIdmatiere());
                                    }

                                    /*for (Evaluation e : notes) {
                                     if (matieres.contains(e.getIdmatiere())) {
                                     notesPropres.add(e);
                                     matiereCompses.add(e.getIdmatiere());
                                     }
                                     }*/
                                    for (int i = 0; i < nature.size(); i++) {
                                        table.addCell(PrintUtils.createPdfPCell("Matières " + nature.get(i).getLibelle(), 5, false, PrintUtils.blueFont));

                                        int totalCoefBloc = 0;
                                        int totalPointBloc = 0;

                                        for (int a = 0; a < matieres.size(); a++) {

                                            if (matiereCompses.contains(matieres.get(a))) {
                                                //if (Objects.equals(notesPropres.get(a).getIdmatiere(), nature.get(i).getIdnaturematiere())) {;
                                                //decompte des coefficient et nombre point globaux
                                                //totalCoef += notesPropres.get(a).getIdmatiere().getCoeficient();
                                                // totalPoint += notesPropres.get(a).getNote() * notesPropres.get(a).getIdmatiere().getCoeficient();

                                                    //decompte des coef et point par groupe                                          
                                                //totalCoefBloc += notesPropres.get(a).getIdmatiere().getCoeficient();
                                                //totalPointBloc += notesPropres.get(a).getNote() * notesPropres.get(a).getIdmatiere().getCoeficient();
//                                                    table.addCell(PrintUtils.createPdfPCell("" + notesPropres.get(a).getIdmatiere().getIdtypematiere().getLibelle(), false, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                                                //table.addCell(PrintUtils.createPdfPCell("" + notesPropres.get(a).getNote(), true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                                                //table.addCell(PrintUtils.createPdfPCell("" + notesPropres.get(a).getIdmatiere().getCoeficient(), true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                                                //table.addCell(PrintUtils.createPdfPCell("" + (notesPropres.get(a).getNote() * notesPropres.get(a).getIdmatiere().getCoeficient()), true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                                                //table.addCell(PrintUtils.createPdfPCell("" + notesPropres.get(a).getObservation(), true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC)));
                                                // }
                                            } else {
                                                if (Objects.equals(matieres.get(a).getIdnaturematiere().getIdnaturematiere(), nature.get(i).getIdnaturematiere())) {
                                                    //decompte des coefficient et nombre point globaux
//                                                    totalCoef += matieres.get(a).get
                                                    totalPoint += 0;
                                                    //decompte des coef et point par groupe                                          
//                                                    totalCoefBloc += matieres.get(a).getCoeficient();
                                                    totalPointBloc += 0;

//                                                    table.addCell(PrintUtils.createPdfPCell("" + matieres.get(a).getIdtypematiere().getLibelle(), false, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                                                    table.addCell(PrintUtils.createPdfPCell("O", true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
//                                                    table.addCell(PrintUtils.createPdfPCell("" + matieres.get(a).getCoeficient(), true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                                                    table.addCell(PrintUtils.createPdfPCell("0", true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                                                    table.addCell(PrintUtils.createPdfPCell("Pas encore évalué", true, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC)));
                                                }
                                            }
                                        }

                                        //le texte Total pour chaque bloc
                                        PdfPCell cellTotalBlocText = new PdfPCell(new Paragraph("Total Matières " + nature.get(i).getLibelle() + " ", new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
                                        cellTotalBlocText.setColspan(2);
                                        table.addCell(cellTotalBlocText);

                                        table.addCell(PrintUtils.createPdfPCell("" + totalCoefBloc, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.RED)));

                                        table.addCell(PrintUtils.createPdfPCell("" + totalPointBloc, 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.RED)));

                                        table.addCell(PrintUtils.createPdfPCell("Moyenne Matières  " + nature.get(i).getLibelle(), 3, false, PrintUtils.blueFont));
                                        if (totalCoefBloc != 0) {
                                            table.addCell(PrintUtils.createPdfPCell("" + (totalPointBloc / totalCoefBloc) + "/20", 2, true, PrintUtils.redFont));
                                        } else {
                                            table.addCell(PrintUtils.createPdfPCell("......", 2, true, PrintUtils.redFont));
                                        }
                                        table.addCell(PrintUtils.createPdfPCell("  ", 5, false));
                                    }
                                } else {

                                }
                                //on ecrit totaux
                                table.addCell(PrintUtils.createPdfPCell("Totaux  ", 2, false, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLUE)));

                                //on met le total de coefficient
                                table.addCell(PrintUtils.createPdfPCell("" + totalCoef, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.RED)));

                                //on met le total de point
                                table.addCell(PrintUtils.createPdfPCell("" + totalPoint, 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.RED)));

                                table.addCell(PrintUtils.createPdfPCell("Moyenne de la " + sequence.getIdsequence().getNom(), 3, false, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
                                if (totalCoef != 0) {
                                    table.addCell(PrintUtils.createPdfPCell((totalPoint / totalCoef) + "/20", 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED)));
                                }

                                bulletin.add(table);
                                bulletin.close();
                                JsfUtil.addSuccessMessage("Opération réussie,redirigez-vous dans le repertoire bulletin");
                            } catch (DocumentException ex) {
                                Logger.getLogger(BulletinCtrl.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(BulletinCtrl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            /*} else {
                             JsfUtil.addErrorMessage("L'élève selectionné n a aucune note à la séquence sélectionnée");
                             }*/
                        } else {
                            JsfUtil.addErrorMessage("Aucune nature de matiere parametrée");
                        }
                    } else {
                        JsfUtil.addErrorMessage("l'eleve n a pas de classe");
                    }
                } else {
                    JsfUtil.addErrorMessage("veuillez selectionner une séquence !");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner un élève");
            }
        } else {
            String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            } catch (IOException ex) {
                Logger.getLogger(BulletinCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void modifier() {

    }

    @Override
    public void supprimer() {

    }

    @Override
    public void imprimerBulletinPdf(int eleve, int sequence, Annee annee) {
        //on declare une bulletin qui est un document;
        Document bulletin = new Document(PageSize.A4);

        //on declare le nom du bulletin
        String bulletinName;

        //l eleve qui a le bulletin
        Eleve eleve1 = eleveFacade.findById(eleve);

        // la sequence à la quelle l'on veut imprimer le bulletin         
        Sequenceannee sequence1 = new Sequenceannee();

        Eleveanneeclasse anneeClasse = eleveAnneeClasseFacade.getEleveAnneeClaseByAnneClasse(eleve1.getIdeleve(), annee.getIdannee());
        if (anneeClasse != null) {
            bulletinName = eleve1.getNom() + "_" + eleve1.getPrenom() + "_" + sequence1.getIdsequence().getNom() + ".pdf";
            try {
                PdfWriter.getInstance(bulletin, new FileOutputStream(bulletinName));
                bulletin.open();
                bulletin.add(new Phrase("hello word"));

            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            JsfUtil.addErrorMessage("L'élève selectionné n 'a aucune classe");
        }
        bulletin.close();
    }

    @Override
    public void imprimerBulletinHtml() {
        System.out.println("Impression html types compte");
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
                            Logger.getLogger(BulletinCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(BulletinCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void imprimerFichePresence() {

        Personnel perso = (Personnel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("current_user");

        /*if(classe!=null){
         if(semaineFin.after(semaineDebut)){
         int reste = semaineFin.compareTo(semaineDebut);
         Annee an = anneeFacade.findByEtatSingle(true);
         if(an!=null){
         List<Eleveanneeclasse>elevs = eleveAnneeClasse.findByAnneeClasse(an.getIdannee(), classe.getIdclasse());
         if(!elevs.isEmpty()){
                    
         try {
                         

         String listName = "liste_de_presence_de"+classe.getNom()+".pdf";
         Document listePresence = new Document(); 
                        
                         
                         
         PdfWriter.getInstance(listePresence, new FileOutputStream(Utilitaires.path+"/"+listName));
         listePresence.open();
         PdfPTable table = new PdfPTable(36);
         PdfPCell cellEntete = new PdfPCell(new Paragraph("Fiche de la présence de la classe"+classe.getNom()+"de la semaine du"));
         cellEntete.setColspan(6);
         table.addCell(cellEntete);
                         
         PdfPCell cellEleve = new PdfPCell(new Paragraph("Eleve"));
         cellEleve.setColspan(6);
         table.addCell(cellEleve);
                         
         PdfPCell cellLundi = new PdfPCell(new Paragraph("Lundi"));
         cellLundi.setColspan(6);
         table.addCell(cellLundi);
                         
         PdfPCell cellMardi = new PdfPCell(new Paragraph("Mardi"));
         cellLundi.setColspan(6);
         table.addCell(cellMardi);
                         
         PdfPCell cellMercredi = new PdfPCell(new Paragraph("Mercredi"));
         cellLundi.setColspan(6);
         table.addCell(cellMercredi);
                         
         PdfPCell cellJeudi = new PdfPCell(new Paragraph("Jeudi"));
         cellLundi.setColspan(6);
         table.addCell(cellJeudi);
                         
         PdfPCell cellVendredi = new PdfPCell(new Paragraph("Vendredi"));
         cellLundi.setColspan(6);
         table.addCell(cellVendredi); 
                     
         listePresence.add(table);
                         
         listePresence.close();
         JsfUtil.addSuccessMessage("Opération réussie");
         } catch (FileNotFoundException ex) {
         Logger.getLogger(BulletinCtrl.class.getName()).log(Level.SEVERE, null, ex);
         } catch (DocumentException ex) {
         Logger.getLogger(BulletinCtrl.class.getName()).log(Level.SEVERE, null, ex);
         }
         }else{
         JsfUtil.addErrorMessage("la classe selectionnée n'a aucun élève !");
         }
         }else{
         JsfUtil.addErrorMessage("Aucune année en cours n'est parametrés, contactez l'administrateur !");
         }
         }else{
         JsfUtil.addErrorMessage("Les dates sont incorrectes !");
         } 
         }else{
         JsfUtil.addErrorMessage("veuillez selectionner une classe");
         }  */
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
