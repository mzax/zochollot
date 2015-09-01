package halde;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

class MyHandler extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/plain;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
        System.out.println(request.getReader().readLine());
        

        PrintWriter out = response.getWriter();
        
        for (Enumeration<String> e = baseRequest.getParameterNames(); 
                e.hasMoreElements();) {
            String name = e.nextElement();
            out.format("%s: %s%n", name, baseRequest.getParameter(name));
            
        }
    }
}
