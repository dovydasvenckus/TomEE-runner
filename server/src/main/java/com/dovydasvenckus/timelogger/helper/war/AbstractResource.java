package com.dovydasvenckus.timelogger.helper.war;

import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractResource {
    
    public abstract void addToWar(WebArchive archive);
}
