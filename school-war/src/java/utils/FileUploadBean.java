/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.richfaces.exception.FileUploadException;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class FileUploadBean {

    /**
     * Creates a new instance of FileUploadBean
     */
    public FileUploadBean() {
    }

    private ArrayList<UploadedFile> files = new ArrayList<UploadedFile>();

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.close();
    }

    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        
        
        
        files.add(item);
    }

    public String clearUploadData() {
        files.clear();
        return null;
    }

    public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public ArrayList<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<UploadedFile> files) {
        this.files = files;
    }

}
