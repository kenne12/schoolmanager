/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.mouchard;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Personnel;
import entities.Traceur;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "traceurCtrl")
@ViewScoped
public class MouchardCtrl extends AbstractMouchardCtrl implements MouchardInterfaceCtrl, Serializable {

    @PostConstruct
    private void initTraceur() {
        selectedTraceur = new Traceur();
        traceur = new Traceur();
    }

    @Override
    public void imprimerTraceurPdf() {
        try {

        } catch (Exception e) {

            Personnel user = UtilitaireSession.getInstance().getuser();
            if (user != null) {
                String fichier = "Liste_actionUtilisateur du " + new Date() + "_.pdf";
                Document mouchard = new Document();
                try {
                    PdfWriter.getInstance(mouchard, new FileOutputStream(Utilitaires.path + "/" + fichier));
                    mouchard.setMargins(5, 5, 5, 5);
                    mouchard.addCreator("School Manager");
                    mouchard.setPageSize(PageSize.A4);

                    mouchard.open();

                    if (!getTraceurs().isEmpty()) {

                        PdfPTable table = new PdfPTable(6);
                        table.setComplete(true);

                        table.addCell(PrintUtils.createPdfPCell("Liste des actions utilisateur", 6, detail));

                        table.addCell(new Paragraph("Nom"));
                        table.addCell(PrintUtils.createPdfPCell("Prénom ", true));
                        table.addCell(PrintUtils.createPdfPCell("Action ", 3, true));
                        table.addCell(PrintUtils.createPdfPCell("Date", true));

                        for (int i = 0; i < getTraceurs().size(); i++) {

                        }

                        JsfUtil.addSuccessMessage("Liste crée avec succès !");
                    }
                    mouchard.close();
                } catch (DocumentException | FileNotFoundException ex) {
                    Logger.getLogger(MouchardCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
            }
        }
    }

    @Override
    public void imprimerTraceurHtml() {

    }

}
