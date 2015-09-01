package halde;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.Fields.Field;



public class HTTPClientSendForm {

    private Server server;
    private HttpClient client;

    private void startServer() throws Exception {

        server = new Server(8080);
        server.setHandler(new MyHandler());
        server.start();
    }

    private void startClient() throws Exception {

        client = new HttpClient();
        client.start();

        Field name = new Field("Name", "Robert");
        Field age = new Field("Age", "32");
        Fields fields = new Fields();
        fields.put(name);
        fields.put(age);

        ContentResponse res = client.FORM("http://localhost:8080", fields);
       
        System.out.println("client: " + res.getContentAsString());
       
    }

    private void stopClientServer() throws Exception {
        client.stop();
        server.stop();
    }

    public static void main(String[] args) throws Exception {

        HTTPClientSendForm smp = new HTTPClientSendForm();
        smp.startServer();
        smp.startClient();
        smp.stopClientServer();
    }
}
