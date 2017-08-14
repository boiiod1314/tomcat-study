package tomcat.study.chapter02;



import util.DateUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by huwei on 2017/8/14.
 */
public class PrimitiveServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        System.out.println("PrimitiveServlet.service");
        ServletOutputStream out = res.getOutputStream();

        String body = "hello world\r\nhttpservlet";

        out.println("HTTP/1.1 200 OK");
        out.println("Server:httpserver");
        out.println("Date:"+ DateUtil.currentRFC1123Date());
        out.println("Content-Type:text/html");
        out.println("Content-Length:"+body.getBytes().length);
        out.println();
        out.print(body);




    }
}
