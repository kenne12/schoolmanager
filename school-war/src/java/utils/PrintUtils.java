/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Annee;
import entities.Catanneeprix;
import entities.Classe;
import entities.ClasseElementevaluation;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Elevelivreemprunte;
import entities.Etablissement;
import entities.Evaluation;
import entities.PlanningEvaluation;
import entities.Sequenceannee;
import entities.Tranche;
import entities.Trimesteannee;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import session.ClasseElementevaluationFacadeLocal;
import session.EvaluationFacadeLocal;
import session.PlanningEvaluationFacadeLocal;
import session.SequenceanneeFacadeLocal;

/**
 *
 * @author gervais
 */
public class PrintUtils {

    public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    public static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.RED);
    public static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
    public static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLUE);
    public static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    public static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);

    public static Font setUpFont(final float size, final int style, final BaseColor color) {
        Font font = new Font();
        font.setStyle(style);
        font.setSize(size);
        font.setColor(color);
        return font;
    }

    public static PdfPCell createPdfPCell(String sCell, int colspan, boolean etatHori, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell, font));
        cell.setColspan(colspan);
        if (etatHori) {
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }

        cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int colspan, boolean etatHori) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell));
        cell.setColspan(colspan);
        if (etatHori) {
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }

        cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, boolean etatHori, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell, font));
        if (etatHori) {
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }

        cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, boolean etatHori) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell));
        if (etatHori) {
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }

        cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        return cell;
    }

    public static void imprimerRecu(Eleve eleve, Eleveanneeclasse classe, Tranche tranche, int montant, int reste, Annee annee) {
        try {
            String recuName = eleve.getNom() + "_" + eleve.getPrenom() + "" + tranche.getNom() + ".pdf";
            Document recu = new Document();
            PdfWriter.getInstance(recu, new FileOutputStream(Utilitaires.path + "/" + recuName));
            recu.open();
            PdfPTable table = new PdfPTable(4);
            table.addCell(PrintUtils.createPdfPCell("COLLEGE POZAM , Année Scolaire " + annee.getCode() + "-" + (annee.getCode() + 1), 5, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Nom de l'élève", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Prénom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Matricule", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Classe", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + eleve.getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + eleve.getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + eleve.getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + classe.getIdclasse().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            table.addCell(PrintUtils.createPdfPCell("A versé un montant de #" + montant + "Fcfa#", 4, false, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("En lettres ........................................................ ", 4, false, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Pour payement des frais de la : " + tranche.getNom(), 4, false, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("A...............", 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Le..............", 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            table.addCell(PrintUtils.createPdfPCell("Visa de l'économe", 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            table.addCell(PrintUtils.createPdfPCell("Visa de l'élève", 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            table.addCell(PrintUtils.createPdfPCell("...", 2, true));
            table.addCell(PrintUtils.createPdfPCell("...", 2, true));

            recu.add(table);
            recu.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PrintUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //imprimer le proces verbal des notes annuel
    public static void printAnnualReportNote(Annee annee, Trimesteannee trimesteannee, Classe classe, ClasseElementevaluation classeElementevaluation, List<Eleveanneeclasse> eleveanneeclasses, List<Trimesteannee> trimesteannees, List<Sequenceannee> sequenceannees, ClasseElementevaluationFacadeLocal classeElementevaluationFacadeLocal, PlanningEvaluationFacadeLocal planningEvaluationFacadeLocal, EvaluationFacadeLocal evaluationFacadeLocal, SequenceanneeFacadeLocal sequenceanneeFacadeLocal) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fichier = "proces_verbal_note" + "_" + annee.getCode() + "-" + annee.getCodefin() + "_" + classe.getNom() + ".pdf";
        Document rapport = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefaultNotesAnnuel + "/" + fichier));
        rapport.open();

        rapport.add(new Paragraph("Année Scolaire : " + annee.getCode() + " / " + annee.getCodefin()));
        rapport.add(new Paragraph("Classe : " + classe.getNom()));
        rapport.add(new Paragraph("Unité Evaluation : " + classeElementevaluation.getElementevaluation().getNom()));
        rapport.add(new Paragraph("Periode : " + annee.getCode() + " - " + annee.getCodefin()));
        rapport.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(2 + sequenceannees.size());
        table.setWidthPercentage(100);

        table.addCell(PrintUtils.createPdfPCell("PROCES VERBAL ANNUEL DES NOTES ", 2 + sequenceannees.size(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("ELEVES", 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL))).setRowspan(2);
        table.addCell(PrintUtils.createPdfPCell("NOTES", sequenceannees.size(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Trimesteannee t : trimesteannees) {
            table.addCell(PrintUtils.createPdfPCell("" + t.getIdtrimestre().getNom(), sequenceanneeFacadeLocal.getByTrimestre(t.getIdtrimestrean()).size(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }

        table.addCell(PrintUtils.createPdfPCell("MATRICULE", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("NOM(S) ET PRENOM(S)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Sequenceannee s : sequenceannees) {
            table.addCell(PrintUtils.createPdfPCell("" + s.getIdsequence().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }

        for (Eleveanneeclasse e : eleveanneeclasses) {
            table.addCell(PrintUtils.createPdfPCell("" + e.getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + e.getEleve().getNom() + " " + e.getEleve().getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            for (Sequenceannee s1 : sequenceannees) {
                String r = findNote1(e, s1, classeElementevaluation, classeElementevaluationFacadeLocal, planningEvaluationFacadeLocal, evaluationFacadeLocal);
                table.addCell(PrintUtils.createPdfPCell("" + r, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            }

        }
        rapport.add(table);
        rapport.close();
    }

    //imprimer le proces verbal des notes trimestriel
    public static void printTrimestrialReportNote(Annee annee, Trimesteannee trimesteannee, Classe classe, ClasseElementevaluation classeElementevaluation, List<Eleveanneeclasse> eleveanneeclasses, List<Sequenceannee> sequenceannees, ClasseElementevaluationFacadeLocal classeElementevaluationFacadeLocal, PlanningEvaluationFacadeLocal planningEvaluationFacadeLocal, EvaluationFacadeLocal evaluationFacadeLocal) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fichier = "proces_verbal_note" + "_" + trimesteannee.getIdtrimestre().getNom() + "_" + classe.getNom() + ".pdf";
        
        Document rapport = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefaultNotesTrim + "/" + fichier));
        rapport.open();
        

        rapport.add(new Paragraph("Année Scolaire : " + annee.getCode() + " / " + annee.getCodefin()));
        rapport.add(new Paragraph("Classe : " + classe.getNom()));
        rapport.add(new Paragraph("Unité Evaluation : " + classeElementevaluation.getElementevaluation().getNom()));
        rapport.add(new Paragraph("Période : " + trimesteannee.getIdtrimestre().getNom()));
        rapport.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(2 + sequenceannees.size());
        table.setWidthPercentage(100);

        table.addCell(PrintUtils.createPdfPCell("PROCES VERBAL TRIMESTRIEL DES NOTES ", 2 + sequenceannees.size(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("ELEVES", 2, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("NOTES", sequenceannees.size(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("MATRICULE", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("NOM(S) ET PRENOM(S)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Sequenceannee s : sequenceannees) {
            table.addCell(PrintUtils.createPdfPCell("" + s.getIdsequence().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }

        for (Eleveanneeclasse e : eleveanneeclasses) {
            table.addCell(PrintUtils.createPdfPCell("" + e.getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + e.getEleve().getNom() + " " + e.getEleve().getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            for (Sequenceannee s1 : sequenceannees) {
                String r = findNote1(e, s1, classeElementevaluation, classeElementevaluationFacadeLocal, planningEvaluationFacadeLocal, evaluationFacadeLocal);
                table.addCell(PrintUtils.createPdfPCell("" + r, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            }

        }
        rapport.add(table);
        rapport.close();
    }

    //imprimer le proces verbal des notes sequentiel
    public static void printSequentialReportNote(Annee annee, Sequenceannee sequenceannee, Classe classe, List<Evaluation> evaluations) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fichier = "proces_verbal_note" + "_" + sequenceannee.getIdsequence().getNom() + "_" + classe.getNom() + ".pdf";
        Document rapport = new Document();
        PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefaultNotes + "/" + fichier));
        rapport.open();

        rapport.add(new Paragraph("Année Scolaire : " + annee.getCode() + " / " + annee.getCodefin()));
        rapport.add(new Paragraph("Classe : " + classe.getNom()));
        rapport.add(new Paragraph("Evaluation : " + evaluations.get(0).getPlanningEvaluation().getElementEvaluation().getElementevaluation().getNom()));
        rapport.add(new Paragraph("Période : " + sequenceannee.getIdsequence().getNom()));
        rapport.add(new Paragraph("  "));

        float[] widths = {1f, 3f, 0.5f, 1f};
        

        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100);

        table.addCell(PrintUtils.createPdfPCell("PROCES VERBAL DES NOTES ", 4, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("Matricule", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Nom(s) et Prénom(s)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Note", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Observation", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Evaluation e : evaluations) {
            table.addCell(PrintUtils.createPdfPCell("" + e.getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + e.getEleve().getNom() + " " + e.getEleve().getPrenom(), false, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + e.getNote(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + e.getObservation(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }

        rapport.add(table);
        rapport.close();
    }

    // Liste des eleves insolvable par tranche
    public static void printInsolventStudent(Annee annee, List<Solvabilite> eleves, Classe classe, Tranche tranche) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fichier = "liste_des_insolvables" + "_" + tranche.getNom() + "_" + classe.getNom() + ".pdf";
        Document rapport = new Document();
        PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefautInsolvavle + "/" + fichier));
        rapport.open();

        rapport.add(new Paragraph("Année Scolaire : " + annee.getCode() + " / " + annee.getCodefin()));
        rapport.add(new Paragraph("Classe : " + classe.getNom()));
        rapport.add(new Paragraph("Motif : " + tranche.getNom()));
        rapport.add(new Paragraph("Montant : " + tranche.getPrix()));
        rapport.add(new Paragraph("Echéance : " + sdf.format(tranche.getDatefin())));
        rapport.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        table.addCell(PrintUtils.createPdfPCell("Liste des élèves insolvable ", 5, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("Matricule", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Nom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Prénom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Payé (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Reste (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Solvabilite object : eleves) {
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getPaye(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getReste(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }
        rapport.add(table);
        rapport.close();

    }

    // Liste des eleve globallement insolvables
    public static void printInsolventStudent(Annee annee, List<Solvabilite> eleves, Classe classe, Catanneeprix catanneeprix) throws Exception {

        String fichier = "liste_des_insolvables_" + classe.getNom() + ".pdf";
        Document rapport = new Document();
        PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefautInsolvavle + "/" + fichier));
        rapport.open();

        rapport.add(new Paragraph("Année Scolaire : " + annee.getCode() + " / " + annee.getCodefin()));
        rapport.add(new Paragraph("Classe : " + classe.getNom()));
        rapport.add(new Paragraph("Motif : Toutes les tranches"));
        rapport.add(new Paragraph("Montant : " + catanneeprix.getPrix()));
        rapport.add(new Paragraph("Echéance : "));
        rapport.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.addCell(PrintUtils.createPdfPCell("Liste des élèves insolvable", 5, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("Matricule", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Nom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Prénom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Payé (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Reste (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Solvabilite object : eleves) {
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getPaye(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getReste(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }
        rapport.add(table);
        rapport.close();

    }

    // Liste des eleves solvable par tranche
    public static void printSolventStudent(Annee annee, List<Solvabilite> eleves, Classe classe, Tranche tranche) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fichier = "liste_des_solvables" + "_" + tranche.getNom() + "_" + classe.getNom() + ".pdf";
        Document rapport = new Document();
        PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefautSolvable + "/" + fichier));
        rapport.open();

        rapport.add(new Paragraph("Année Scolaire : " + annee.getCode() + " / " + annee.getCodefin()));
        rapport.add(new Paragraph("Classe : " + classe.getNom()));
        rapport.add(new Paragraph("Motif : " + tranche.getNom()));
        rapport.add(new Paragraph("Montant : " + tranche.getPrix()));
        rapport.add(new Paragraph("Echéance : " + sdf.format(tranche.getDatefin())));
        rapport.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        table.addCell(PrintUtils.createPdfPCell("Liste des élèves solvable ", 5, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("Matricule", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Nom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Prénom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Payé (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Reste (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Solvabilite object : eleves) {
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getPaye(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getReste(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }
        rapport.add(table);
        rapport.close();

    }

    // Liste des eleve globallement insolvables
    public static void printSolventStudent(Annee annee, List<Solvabilite> eleves, Classe classe, Catanneeprix catanneeprix) throws Exception {

        String fichier = "liste_des_solvables_" + classe.getNom() + ".pdf";
        Document rapport = new Document();
        PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDefautSolvable + "/" + fichier));
        rapport.open();

        rapport.add(new Paragraph("Année Scolaire : " + annee.getCode() + " / " + annee.getCodefin()));
        rapport.add(new Paragraph("Classe : " + classe.getNom()));
        rapport.add(new Paragraph("Motif : Toutes les tranches"));
        rapport.add(new Paragraph("Montant : " + catanneeprix.getPrix()));
        rapport.add(new Paragraph("Echéance : "));
        rapport.add(new Paragraph("  "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.addCell(PrintUtils.createPdfPCell("Liste des élèves insolvable", 5, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        table.addCell(PrintUtils.createPdfPCell("Matricule", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Nom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Prénom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Payé (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        table.addCell(PrintUtils.createPdfPCell("Reste (Fcfa)", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

        for (Solvabilite object : eleves) {
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getMatricule(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getNom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getPrenom(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getPaye(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("" + object.getReste(), true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
        }
        rapport.add(table);
        rapport.close();

    }

    //methode qui imprime la liste des documents empruntés
    public static void printLentBook(List<Elevelivreemprunte> list, Annee annee) {
        String file = "documents_empruntes.pdf";
        Document rapport = new Document();
        try {
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/" + Utilitaires.repertoireParDocEmpruntes + "/" + file));
            rapport.open();
            PdfPTable table = new PdfPTable(5);

            table.addCell(PrintUtils.createPdfPCell("COLLEGE POZAM , Année Scolaire " + annee.getCode() + " - " + (annee.getCode() + 1), 5, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Liste des Documents empruntés à la Bibliotheque", 5, true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            table.addCell(PrintUtils.createPdfPCell("Nom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Prénom", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Code du livre", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Titre", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));
            table.addCell(PrintUtils.createPdfPCell("Date emprunt", true, new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL)));

            for (Elevelivreemprunte object : list) {
                table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getNom(), true, normalFont));
                table.addCell(PrintUtils.createPdfPCell("" + object.getEleve().getPrenom(), true, normalFont));
                table.addCell(PrintUtils.createPdfPCell("" + object.getLivre().getCodeisbn(), true, normalFont));
                table.addCell(PrintUtils.createPdfPCell("" + object.getLivre().getTitre(), true, normalFont));
                table.addCell(PrintUtils.createPdfPCell("" + object.getDateemprunt(), true, normalFont));
            }
            rapport.add(table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PrintUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void entetePortrait(Document document, Etablissement etablissement) throws Exception {
        Image image = Image.getInstance(Utilitaires.path + "/" + "resources/images/logo1.png");
        image.setAbsolutePosition(400f, 450f);
        document.add(image);

        document.add(new Paragraph("" + etablissement.getNom(), normalFont));
        document.add(new Paragraph("" + etablissement.getAdresse().getAdresse(), normalFont));
        document.add(new Paragraph("Tél." + etablissement.getAdresse().getContact(), normalFont));
    }

    public static String findNote1(Eleveanneeclasse eleveanneeclasse, Sequenceannee sequenceannee, ClasseElementevaluation classeElementevaluation, ClasseElementevaluationFacadeLocal classeElementevaluationFacadeLocal, PlanningEvaluationFacadeLocal planningEvaluationFacadeLocal, EvaluationFacadeLocal evaluationFacadeLocal) {
        String resultat = null;
        try {

            PlanningEvaluation p = planningEvaluationFacadeLocal.findByElementSequence(classeElementevaluation.getId(), sequenceannee.getIdsequencean());
            if (p != null) {
                if (evaluationFacadeLocal.findByElevePlanning(eleveanneeclasse.getEleve().getIdeleve(), p.getIdplanning()) != null) {
                    resultat = evaluationFacadeLocal.findByElevePlanning(eleveanneeclasse.getEleve().getIdeleve(), p.getIdplanning()).getNote().toString();
                } else {
                    resultat = "Pas évalué";
                }
            } else {
                resultat = "Non planifié";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

}
