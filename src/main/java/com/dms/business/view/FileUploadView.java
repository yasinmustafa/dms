package com.dms.business.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
 
@ManagedBean
public class FileUploadView {
	
	final String INCOMING = "/scan/incoming/";
 
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
    }
    
    public void copyFile(String fileName, InputStream in) throws IOException {
          
             OutputStream out = new FileOutputStream(new File(INCOMING + fileName));
          
             int read = 0;
             byte[] bytes = new byte[1024];
          
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
          
             in.close();
             out.flush();
             out.close();

 }
}
