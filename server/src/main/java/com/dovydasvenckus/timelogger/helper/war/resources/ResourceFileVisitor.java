package com.dovydasvenckus.timelogger.helper.war.resources;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ResourceFileVisitor extends SimpleFileVisitor<Path>{
    WebArchive archive;
    Integer subPathFrom = 3;
    
    public ResourceFileVisitor(WebArchive archive){
        this.archive = archive;
    }

    public Integer getSubPathFrom() {
        return subPathFrom;
    }

    public void setSubPathFrom(Integer subPathFrom) {
        this.subPathFrom = this.subPathFrom > 0 ? subPathFrom: 0;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
        Path webPath = file.subpath(subPathFrom, file.getNameCount());
        archive.addAsWebResource(file.toFile(), webPath.toString());
        return FileVisitResult.CONTINUE;
    }
    
    
}
