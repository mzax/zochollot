package zochollot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;


public class SQLiteGETContentHandler extends AbstractHandler
{
	
	
	 public void handle(String target,Request baseRequest,HttpServletRequest request,HttpServletResponse response)
	            throws IOException, ServletException{
	        try{
	            response.setContentType("text/html;charset=utf-8");
	            response.setStatus(HttpServletResponse.SC_OK);
	            baseRequest.setHandled(true);

	            
	            Connection c = null;
	            Statement stmt = null;
	            try {
	              Class.forName("org.sqlite.JDBC");
	              c = DriverManager.getConnection("jdbc:sqlite:test.db");
	              c.setAutoCommit(false);
	              System.out.println("Opened database successfully");

	              stmt = c.createStatement();
	             // ResultSet rs = stmt.executeQuery( "SELECT last_insert_rowid() FROM XYZT;" );
	              
	              //ResultSet res = stmt.executeQuery("select count(*) from XYZT");
	              //res.next();
	              //int count = res.getInt(1);
	              //System.out.println("Count:"+count);
	              
	              //ResultSet resu = stmt.executeQuery("select * from XYZT WHERE rowid="+count);
	              // float x = resu.getFloat("X");
	             // float y = resu.getFloat("Y");
	              
	             
	              ResultSet rslt = stmt.executeQuery("SELECT * FROM XYZT ORDER BY TIMESTAMP DESC LIMIT 1");
	              float x = rslt.getFloat("X");
	              float y = rslt.getFloat("Y");
	              Timestamp t = rslt.getTimestamp("TIMESTAMP");
	              PrintWriter out = response.getWriter();
	           //   out.println("X="+x);
	              
	              //wieso funktioniert das? Beispiele arbeiten immer mit StringEntity...
	              String params =new String("{\"Position\":[{\"X\":\""+x+"\",\"Y\":\""+y+"\",\"T\":\""+t+"\"} ]}");
	              
	              
	              out.println(params);
	              System.out.println(params);
	              
	             
	            //  System.out.println("LastRowID: "+ rs);
	              
	             // ResultSet last_column = stmt.executeQuery("SELECT * FROM XYZT WHERE rowid="+ rs +";");
	             // float x = last_column.getFloat("X");
	            //  System.out.println("last_lat:"+ x);
	              
	             // rs.close();
	            //  last_column.close();
	              stmt.close();
	              c.close();
	            } catch ( Exception e ) {
	              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	              System.exit(0);
	            }
	            System.out.println("Operation done successfully");
	            
	            
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
 /* public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
     // ResultSet rs = stmt.executeQuery( "SELECT last_insert_rowid() FROM XYZT;" );
      
      ResultSet res = stmt.executeQuery("select count(*) from XYZT");
      res.next();
      int count = res.getInt(1);
      System.out.println("Count:"+count);
      
      ResultSet resu = stmt.executeQuery("select * from XYZT WHERE rowid=3");
      float x = resu.getFloat("X");
      System.out.println("X:"+x);
     
    //  System.out.println("LastRowID: "+ rs);
      
     // ResultSet last_column = stmt.executeQuery("SELECT * FROM XYZT WHERE rowid="+ rs +";");
     // float x = last_column.getFloat("X");
    //  System.out.println("last_lat:"+ x);
      
     // rs.close();
    //  last_column.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }*/
}