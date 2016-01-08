package com.dovydasvenckus.timelogger.helper.war;

import java.io.File;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class WebResource extends AbstractResource{
    String path;
    
    public WebResource(String path){
        this.path = path;
    }
    @Override
    public void addToWar(WebArchive archive) {
        archive.addAsWebResource(new File(path));
    }
    
}
