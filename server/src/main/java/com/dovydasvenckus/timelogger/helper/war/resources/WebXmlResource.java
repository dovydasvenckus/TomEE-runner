package com.dovydasvenckus.timelogger.helper.war.resources;

import java.io.File;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class WebXmlResource extends AbstractResource{
    String path = "src/main/webapp/WEB-INF/web.xml";
    
    public WebXmlResource() {}
    
    public WebXmlResource(String path){
        this.path = path;
    }
    
    @Override
    public void addToWar(WebArchive archive) {
        File webXmlFile = new File(path);
        archive.setWebXML(webXmlFile);
    }
    
}
