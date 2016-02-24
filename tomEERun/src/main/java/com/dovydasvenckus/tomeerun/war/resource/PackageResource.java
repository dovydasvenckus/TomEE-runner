package com.dovydasvenckus.tomeerun.war.resource;

import org.jboss.shrinkwrap.api.spec.WebArchive;

public class PackageResource extends AbstractResource{
    private Package pack;
    
    public PackageResource(Package pack){
        this.pack = pack;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }
    
    @Override
    public void addToWar(WebArchive archive) {
        archive.addPackage(pack);
    }
    
}
