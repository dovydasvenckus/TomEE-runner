package org.willcodeforbeer;
import groovy.transform.CompileStatic

@CompileStatic
public class Main {
	static void main(String... args) {
            TomEEApplication.run(HelloWorldServlet.class, SampleController.class, GroovyController.class);
	}
}