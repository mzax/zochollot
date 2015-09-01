package zochollot;

import java.sql.*;

public final class SQLiteJDBC{
	
	private static SQLiteJDBC sqLiteJDBC = null;
	private static Connection c = null;
    Statement stmt = null;
    
    private SQLiteJDBC(){
    }
      
    public static SQLiteJDBC getInstance(){
    	System.out.println("getInstance");
    	if(sqLiteJDBC == null){
    		sqLiteJDBC = new SQLiteJDBC();
    		
    		
    	}
    	sqLiteJDBC.createDBconnection();
    	return sqLiteJDBC;
    }
    
    static {
	    try {
	        Class.forName("org.sqlite.JDBC");
	    } catch (ClassNotFoundException e) {
	        System.err.println("Failed to load JDBC-Driver");
	        e.printStackTrace();
	    }
    }
    
    private void createDBconnection(){
    	System.out.println("createDBconnection");
	    try {
	    	
	    	if (c != null){ //dieser ganze if-Block scheint ziemlich überflüssig, da nicht wirklich (c !=null && c.isClosed ==false) ist 
	    		if(c.isClosed() == true){
	    			System.out.println("Database is closed: "+ c.isClosed());
	    			c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    			c.setAutoCommit(false);
	    			System.out.println("Database is closed: "+ c.isClosed());
	    			return;
	    		}
	    		else 
	    		
	    		return;
	    	}
	    	c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    	c.setAutoCommit(false); /*In der Standardverarbeitung in JDBC wird jede SQL-Anweisung für sich als Transaktion abgearbeitet. 
	      	Dies nennt sich Auto-Commit. Um jedoch eine Folge von Anweisungen in einer Transaktion auszuführen, 
	      	muss zunächst das Auto-Commit zurückgesetzt werden. Dann werden die Datenbankmanipulationen ausgeführt, 
	      	und die Transaktion kann anschließend abgeschlossen (commit) oder zurückgesetzt (rollback) werden. 
	    	*/
	    	 if (!c.isClosed()){
	                System.out.println("Database connection established");
	    	 }
	    	 
	    	 
	    }
	    catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    public void writeSQLite (float longitude, float latitude, float height)
    {
    try{
    	
    	DatabaseMetaData dbm = c.getMetaData();
    	ResultSet tables = dbm.getTables(null, null, "XYZT", null);
    	if (tables.next()){
    		System.out.println("Table XYZT exists already");
    		System.out.println(tables);
    	}
    	else {
    		System.out.println("Table XYZT does not exist");
		     stmt = c.createStatement();
		      
		    
		     String schema = "CREATE TABLE XYZT " +
		                      "( X              REAL    NOT NULL, " + 
		                      " Y              REAL     NOT NULL, " + 
		                      " Z              REAL, " + 
		                      " TIMESTAMP      TIMESTAMP)"; 
		      stmt.executeUpdate(schema);
		      stmt.close();
    	}
		      
 
      

      
      //String INSERT_RECORD = "insert into XYZT(ID,X,Y,Z,TIMESTAMP) values(?, ?, ?, ?, ?)";
      String INSERT_RECORD = "insert into XYZT(X,Y,Z,TIMESTAMP) values( ?, ?, ?, ?)";
      PreparedStatement pstmt = c.prepareStatement(INSERT_RECORD);
      //pstmt.setInt(1, 1);
      
      //java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
      java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
      System.out.println ("Timestamp: "+sqlDate);

      pstmt.setFloat(1, longitude);
      pstmt.setFloat(2, latitude);
      pstmt.setFloat(3, height);
      pstmt.setTimestamp(4, sqlDate);
      pstmt.executeUpdate();




      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
}