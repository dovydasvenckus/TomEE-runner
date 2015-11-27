package org.willcodeforbeer.timelogger.init;

import org.willcodeforbeer.timelogger.controller.SampleController
import org.willcodeforbeer.timelogger.domain.Project

public class Main {
	static void main(String... args) {
            TomEEApplication.run([
                    org.willcodeforbeer.timelogger.controller.SampleController.class.package,

                    org.willcodeforbeer.timelogger.domain.Project.class.package
                ],
                
                [[currentLocation: "META-INF/persistence.xml", warLocation: "META-INF/persistence.xml"]]);
	}
}