package com.dovydasvenckus.timelogger.init;

import com.dovydasvenckus.timelogger.controller.SampleController;
import com.dovydasvenckus.timelogger.domain.Project;
import com.dovydasvenckus.timelogger.helper.war.Resource;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        List<Package> packages = new ArrayList<>();
        packages.add(SampleController.class.getPackage());
        packages.add(Project.class.getPackage());

        List<Resource> resources = new ArrayList<>();
        resources.add(new Resource("META-INF/persistence.xml"));
        System.err.println(resources.get(0).getLocationInWar());
        TomEEApplication.run(packages, resources);
    }
}