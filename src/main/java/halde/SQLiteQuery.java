package halde;

import java.sql.*;


public class SQLiteQuery
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM XYZT;" );
      while ( rs.next() ) {
      //   int id = rs.getInt("id");
         
         float x = rs.getFloat("X");
         float y = rs.getFloat("Y");
         float z = rs.getFloat("Z");
         Date t = rs.getDate("TIMESTAMP");
         
       //  System.out.println( "ID = " + id );
         System.out.println( "X = " + x );
         System.out.println( "Y = " + y );
         System.out.println( "Z = " + z );
         System.out.println( "TIMESTAMP = " + t );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}