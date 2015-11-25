package org.willcodeforbeer.init;

import groovy.transform.CompileStatic
import org.willcodeforbeer.*
import org.willcodeforbeer.controller.SampleController

@CompileStatic
public class Main {
	static void main(String... args) {
            TomEEApplication.run(org.willcodeforbeer.controller.SampleController.class.package);
	}
}