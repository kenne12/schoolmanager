/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Annee;
import entities.CompteUtilisateur;
import entities.Etablissement;
import entities.EtablissementPersonnel;
import entities.Personnel;
import entities.Personnelmatiereclasseannee;
import entities.Traceur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class Utilitaires {

    private static final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    public static final String path = servletContext.getRealPath("");
    public static final String nomImageParDefautEleve = "default.png";
    public static final String repertoireImageParDefautEleve = "photos/photo_eleves";
    public static final String nomImageParDefautPersonnel = "default.png";
    public static final String repertoireImageParDefautPersonnel = "photos/photo_personnels";
    public static final String repertoireParDefautBulletin = "rapport/bulletins";
    public static final String repertoireParDefautInsolvavle = "rapport/insolvabilite";
    public static final String repertoireParDefautSolvable = "rapport/solvabilite";
    public static final String repertoireParDefautClasse = "rapport/listeClasse";
    public static final String repertoireParDocEmpruntes = "rapport/livre_Empruntes";
    public static final String repertoireParDefaultNotes = "rapport/notes/sequentiel";
    public static final String repertoireParDefaultNotesTrim = "rapport/notes/trimestriel";
    public static final String repertoireParDefaultNotesAnnuel = "rapport/notes/annuel";
    public static final String chemin = servletContext.getContextPath();

    public static void saveOperation(TraceurFacadeLocal traceurFacadeLocal, String action, Personnel personnel) {
        try {
            Traceur traceur = new Traceur();
            traceur.setAction(action);
            traceur.setPersonnel(personnel);
            traceur.setDateaction(new Date());
            traceur.setHeure(new Date());
            traceurFacadeLocal.create(traceur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveOperation(TraceurFacadeLocal traceurFacadeLocal, String action, CompteUtilisateur compteUtilisateur, Annee annee) {
        try {
            Traceur traceur = new Traceur();
            traceur.setAction(action);
            traceur.setCompteutilisateur(compteUtilisateur);
            traceur.setAnnee(annee);
            traceur.setDateaction(new Date());
            traceur.setHeure(new Date());
            traceurFacadeLocal.create(traceur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Etablissement> findSchoolByPersonnel(Personnel personnel) {
        List<Etablissement> etablissements = new ArrayList<>();
        try {
            List<EtablissementPersonnel> etablissementPersonnels = personnel.getEtablissementPersonnelList();
            if (!etablissementPersonnels.isEmpty()) {
                for (EtablissementPersonnel e : etablissementPersonnels) {
                    etablissements.add(e.getEtablissement());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return etablissements;
    }

    public static List<Personnelmatiereclasseannee> findByPersonnel(Personnel personnel) {
        List<Personnelmatiereclasseannee> results = new ArrayList<>();
        List<Personnelmatiereclasseannee> resultTemp = new ArrayList<>();
        try {
            List<Etablissement> etablissements = findSchoolByPersonnel(personnel);
            List<EtablissementPersonnel> etablissementPersonnels = new ArrayList<>();
            List<Personnel> personnels = new ArrayList<>();
            if (!etablissements.isEmpty()) {
                for (Etablissement e : etablissements) {
                    etablissementPersonnels = e.getEtablissementPersonnelList();
                    if (!etablissementPersonnels.isEmpty()) {
                        for (EtablissementPersonnel E : etablissementPersonnels) {
                            resultTemp = E.getPersonnel().getPersonnelmatiereclasseanneeList();
                            if (!resultTemp.isEmpty()) {
                                results.addAll(resultTemp);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static String codeInscription(Etablissement etablissement, Annee annee) {
        String resultat = "";
        try {
            String[] chaines = etablissement.getNom().split(" ");
            int i = 0;
            for (String s : chaines) {
                if (i == 1) {
                    if (!StringUtils.upperCase(s).startsWith(StringUtils.upperCase("d"))) {
                        resultat += s.toUpperCase().charAt(0);
                    }
                } else {
                    resultat += s.toUpperCase().charAt(0);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public static String getExtension(String nomFichier) {
        int taille = nomFichier.length();
        String extension = "";
        for (int i = 0; i < taille; i++) {
            if (nomFichier.charAt(i) == '.') {
                extension = "";
                continue;
            }
            extension += nomFichier.charAt(i);
        }
        return extension;
    }

    public static boolean estExtensionImage(String extension) {
        if (extension == null || extension.equals("")) {
            return false;
        }
        String ext = extension.toUpperCase();
        if (ext.equals("JPG")) {
            return true;
        }
        if (ext.equals("JPEG")) {
            return true;
        }
        if (ext.equals("GIF")) {
            return true;
        }
        if (ext.equals("PNG")) {
            return true;
        }
        if (ext.equals("BMP")) {
            return true;
        }
        return false;
    }

    public static boolean estFichierImage(String nom) {
        String extension = getExtension(nom);
        if (extension == null || extension.equals("")) {
            return false;
        }
        String ext = extension.toUpperCase();
        if (ext.equals("JPG")) {
            return true;
        }
        if (ext.equals("JPEG")) {
            return true;
        }
        if (ext.equals("GIF")) {
            return true;
        }
        if (ext.equals("PNG")) {
            return true;
        }
        if (ext.equals("BMP")) {
            return true;
        }
        return false;
    }

    public static boolean handleFileUpload(FileUploadEvent event, String absoluteSavePath) {
        OutputStream saveFile;
        try {
            saveFile = new FileOutputStream(new File(absoluteSavePath));
            InputStream in = event.getFile().getInputstream();
            byte buff[] = new byte[8];
            int n;
            while ((n = in.read(buff)) >= 0) {
                saveFile.write(buff);
                buff = new byte[8];
            }
        } catch (IOException ex) {
            FacesMessage message = new FacesMessage("Error", "Error While uploading " + event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);
            Logger.getLogger(Utilitaires.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean handleFileUpload(UploadedFile file, String absoluteSavePath) {
        OutputStream saveFile;
        try {
            //Création du fichier
            saveFile = new FileOutputStream(new File(absoluteSavePath));
            InputStream in = file.getInputstream();
            byte buff[] = new byte[8];
            int n;
            while ((n = in.read(buff)) >= 0) {
                saveFile.write(buff);
                buff = new byte[8];
            }
        } catch (IOException ex) {
            FacesMessage message = new FacesMessage("Error", "Error While uploading " + file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);
            Logger.getLogger(Utilitaires.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean CopierFichier(File Source, File Destination) {
        boolean resultat = false;
        //  System.err.println("copie de : "+Source.getAbsolutePath()+"\n\tvers  : "+Destination.getAbsolutePath());
        FileInputStream filesource = null;
        FileOutputStream fileDestination = null;
        try {
            filesource = new FileInputStream(Source);
            fileDestination = new FileOutputStream(Destination);
            byte buffer[] = new byte[1000];
            int nblecture;
            while ((nblecture = filesource.read(buffer)) != -1) {
                fileDestination.write(buffer, 0, nblecture);
                buffer = new byte[8];
            }
            resultat = true;
        } catch (FileNotFoundException nf) {
            nf.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                filesource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fileDestination.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultat;
    }

}
