package com.dovydasvenckus.timelogger.init;

import com.dovydasvenckus.timelogger.controller.SampleController;
import com.dovydasvenckus.timelogger.domain.Project;
import com.dovydasvenckus.tomeerun.init.TomEEApplication;
import com.dovydasvenckus.tomeerun.war.resource.AbstractResource;
import com.dovydasvenckus.tomeerun.war.resource.PackageResource;
import com.dovydasvenckus.tomeerun.war.resource.Resource;
import com.dovydasvenckus.tomeerun.war.resource.WebResource;
import com.dovydasvenckus.tomeerun.war.resource.WebXmlResource;

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
        webAppResources.add(new WebResource("src/main/webapp/resources"));

        TomEEApplication.run(webAppResources);
    }
}