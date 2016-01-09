package com.dovydasvenckus.timelogger.helper.war.resources;

import org.jboss.shrinkwrap.api.spec.WebArchive;

public class Resource extends AbstractResource{
    String location;
    String locationInWar;

    public Resource(String location){
        this.location = location;
        this.locationInWar = location;
    }

    public Resource(String location, String locationInWar) {
        this.location = location;
        this.locationInWar = locationInWar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationInWar() {
        return locationInWar;
    }

    public void setLocationInWar(String locationInWar) {
        this.locationInWar = locationInWar;
    }

    @Override
    public void addToWar(WebArchive archive) {
        archive.addAsResource(location, locationInWar);
    }
}
