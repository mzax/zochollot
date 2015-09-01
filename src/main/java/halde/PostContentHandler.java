package halde;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

class PostContentHandler extends AbstractHandler{

    public void handle(String target,Request baseRequest,HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException{
        try{
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            int number1 = Integer.parseInt(request.getParameter("param1"));
            int number2 = Integer.parseInt(request.getParameter("param2"));
            response.getWriter().println(number1*number2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}