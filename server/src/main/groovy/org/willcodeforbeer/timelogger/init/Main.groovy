package org.willcodeforbeer.init;

import org.willcodeforbeer.controller.SampleController
import org.willcodeforbeer.domain.Project

public class Main {
	static void main(String... args) {
            TomEEApplication.run([
                    org.willcodeforbeer.controller.SampleController.class.package,

                    org.willcodeforbeer.domain.Project.class.package
                ],
                
                [[currentLocation: "META-INF/persistence.xml", warLocation: "META-INF/persistence.xml"]]);
	}
}