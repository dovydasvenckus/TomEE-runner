package org.willcodeforbeer

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
 
@Stateless
@Path("/groovy")
class GroovyController {
 
    @GET
    @Produces("text/plain")
    def String groovy() {
        
        return ['some', 'groovy', 'magic'].join(' ')
    }
}


