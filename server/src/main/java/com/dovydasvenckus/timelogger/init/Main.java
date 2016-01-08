package com.dovydasvenckus.timelogger.init;

import com.dovydasvenckus.timelogger.controller.SampleController;
import com.dovydasvenckus.timelogger.domain.Project;
import com.dovydasvenckus.timelogger.helper.war.AbstractResource;
import com.dovydasvenckus.timelogger.helper.war.PackageResource;
import com.dovydasvenckus.timelogger.helper.war.Resource;
import com.dovydasvenckus.timelogger.helper.war.WebResource;
import com.dovydasvenckus.timelogger.helper.war.WebXmlResource;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        List<AbstractResource> webAppResources = new ArrayList<>();
        webAppResources.add(new PackageResource(SampleController.class.getPackage()));
        webAppResources.add(new PackageResource(Project.class.getPackage()));
        webAppResources.add(new Resource("META-INF/persistence.xml"));
        webAppResources.add(new WebXmlResource());
        webAppResources.add(new WebResource("src/main/webapp/index.html"));
        
        TomEEApplication.run(webAppResources);
    }
}