package com.dovydasvenckus.timelogger.init

import com.dovydasvenckus.timelogger.controller.SampleController
import com.dovydasvenckus.timelogger.domain.Project

public class Main {
	static void main(String... args) {
            TomEEApplication.run([
                    SampleController.class.package,

                    Project.class.package
                ],
                
                [[currentLocation: "META-INF/persistence.xml", warLocation: "META-INF/persistence.xml"]]);
	}
}