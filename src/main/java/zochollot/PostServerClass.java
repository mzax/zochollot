package zochollot;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

public class PostServerClass {
   
	public void startServer() throws Exception{
        int port=80;
        Server server= new Server();        
       
        //Contex Handler for Data Input
        ContextHandler contextIn = new ContextHandler();
        contextIn.setContextPath("/gps"); //Warum kommt der POST Request ohne "/gps" aus?
        contextIn.setAllowNullPathInfo(true); //oder context.setContextPath("/gps/");
        contextIn.setResourceBase(".");
        contextIn.setClassLoader(Thread.currentThread().getContextClassLoader());
        contextIn.setHandler(new GPSPostContentHandler());
        
        //Context Handler for Data Output: Last Position into Map
        ContextHandler contextPos = new ContextHandler();
        contextPos.setContextPath("/last");
       // contextPos.setAllowNullPathInfo(true);
       // contextPos.setResourceBase(".");
       // contextPos.setClassLoader(Thread.currentThread().getContextClassLoader());
        contextPos.setHandler(new SQLiteGETContentHandler());
        
        
        //
        ResourceHandler resource_handler = new ResourceHandler();
        // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
        // In this example it is the current directory but it can be configured to anything that the jvm has access to.
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "website/index.html" });
        resource_handler.setResourceBase(".");

        // Add the ResourceHandler to the server.
        GzipHandler gzip = new GzipHandler();
        //server.setHandler(gzip);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
        gzip.setHandler(handlers);
        
        
        //
        // HTTP connector
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(port);
        http.setIdleTimeout(300000);
 
        // Set the connector
        server.addConnector(http);
        
      //Context Handler for Data Output
        ContextHandler contextOut = new ContextHandler();
        contextOut.setContextPath("/");
        contextOut.setHandler(gzip);
        
        // Set the Handlers    
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { contextIn, contextOut, contextPos });

        //...to the server
        server.setHandler(contexts);
        
        server.start();
    }

    public static void main(String []args) throws Exception{
        PostServerClass server= new PostServerClass();
        server.startServer();
    }

}
