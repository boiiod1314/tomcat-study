package tomcat.study.chapter02;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by huwei on 2017/8/14.
 */
public class ServletProcessor1 implements Processor {

    @Override
    public void process(Request request,Response response) throws IOException{

        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);

        URL[] urls = new URL[1];
        File classPath = new File(Constants.WEB_ROOT);

        String repository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
        URLStreamHandler streamHandler = null;
        urls[0] = new URL(null,repository,streamHandler);

        URLClassLoader loader = new URLClassLoader(urls);

        Class myClass = null;
        try {
            myClass = loader.loadClass("tomcat.study.chapter02."+servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Servlet servlet = (Servlet)myClass.newInstance();
            servlet.service(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
