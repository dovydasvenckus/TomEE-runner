package com.dovydasvenckus.tomeerun.war.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class WebResource extends AbstractResource {
    final String webAppPath = "src/main/webapp/";
    String path;
    String targetPath = null;

    public WebResource(String path) {
        this.path = path;
    }

    public WebResource(String path, String targetPath) {
        this.path = path;
        this.targetPath = targetPath;
    }

    @Override
    public void addToWar(WebArchive archive) {
        Path resourcePath = Paths.get(path);
        boolean isDirectory = Files.isDirectory(resourcePath);

        if (isDirectory) {
            try {
                Files.walkFileTree(resourcePath, new ResourceFileVisitor(archive));
            } catch (IOException ex) {
                Logger.getLogger(WebResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (targetPath != null) {
                archive.addAsWebResource(new File(path), targetPath);
            } else {
                archive.addAsWebResource(new File(path));
            }
        }
    }

}
