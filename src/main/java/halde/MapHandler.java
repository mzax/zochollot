package halde;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

public class MapHandler extends AbstractHandler{
	
	public MapHandler(Server server){
		
	
	ResourceHandler resource_handler = new ResourceHandler();
    // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
    // In this example it is the current directory but it can be configured to anything that the jvm has access to.
    resource_handler.setDirectoriesListed(true);
    resource_handler.setWelcomeFiles(new String[]{ "website/index.html" });
    resource_handler.setResourceBase(".");

    // Add the ResourceHandler to the server.
    GzipHandler gzip = new GzipHandler();
    server.setHandler(gzip);
    HandlerList handlers = new HandlerList();
    handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
    gzip.setHandler(handlers);

	}
	
	public void handle(String target,Request baseRequest,HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException{
        try{
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
}
