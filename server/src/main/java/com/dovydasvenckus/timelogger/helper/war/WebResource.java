package com.dovydasvenckus.timelogger.helper.war;

import java.io.File;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class WebResource extends AbstractResource{
    String path;
    String targetPath = null;
    
    public WebResource(String path){
        this.path = path;
    }
    
    public WebResource(String path, String targetPath){
        this.path = path;
        this.targetPath = targetPath;
    }
    
    @Override
    public void addToWar(WebArchive archive) {
        if (targetPath != null)
            archive.addAsWebResource(new File(path), targetPath);
        else archive.addAsWebResource(new File(path));
    }
    
}
