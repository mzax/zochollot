package zochollot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class PostClient {
	
	public static void main(String[] args) throws Exception {
	
		PostClient postClient = new PostClient();
		
		postClient.sendPost();
	}
	

private void sendPost() throws Exception {
	
	String body = "param1=" + URLEncoder.encode( "3", "UTF-8" ) + "&" +
            	  "param2=" + URLEncoder.encode( "5", "UTF-8" );

	URL url = new URL( "http://localhost/gps" );
	
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();

connection.setRequestMethod("POST");
connection.setDoInput(true);
connection.setDoOutput(true);
connection.setUseCaches( false );
connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
connection.setRequestProperty( "Content-Length", String.valueOf(body.length()) );
OutputStreamWriter writer = new OutputStreamWriter( connection.getOutputStream() );
writer.write( body );
writer.flush();
writer.close();

BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()) );

for ( String line; (line = reader.readLine()) != null; )
{
System.out.println( line );
}


reader.close();	
}
	
}

