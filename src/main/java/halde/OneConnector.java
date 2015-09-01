package halde;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
 
/**
 * A Jetty server with one connectors.
 */
public class OneConnector
{
    public static void main( String[] args ) throws Exception
    {
        // The Server
        Server server = new Server();
 
        // HTTP connector
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(8080);
        http.setIdleTimeout(3000);
 
        // Set the connector
        server.addConnector(http);
 
        // Set a handler
        server.setHandler(new HelloWorldHandler());
 
        // Start the server
        server.start();
        server.join();
    }
}
