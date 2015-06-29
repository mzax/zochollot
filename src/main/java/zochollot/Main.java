package zochollot;

import org.eclipse.jetty.server.Server;

public class Main {
    
	public static void main(String[] args) throws Exception {
	
		Server server = new Server(8888); //create server on port 8888 (http://localhost:8888/)
		server.start();//start server process
		server.join(); //join (wait) on server process
	}
}
