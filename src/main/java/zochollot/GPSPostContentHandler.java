package zochollot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

class GPSPostContentHandler extends AbstractHandler{

    public void handle(String target,Request baseRequest,HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException{
        try{
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
           
            float longitude = Float.parseFloat(request.getParameter("long"));
            float latitude = Float.parseFloat(request.getParameter("lat"));
            float height = Float.parseFloat(request.getParameter("height"));
            
            
           
            SQLiteJDBC db = SQLiteJDBC.getInstance();
            db.writeSQLite(longitude, latitude, height);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}