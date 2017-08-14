package tomcat.study.chapter01;

import util.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by huwei on 2017/8/14.
 */
public class Response {

    private Request request;
    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource(){

        byte[] bytes = new byte[1024];
        FileInputStream fis = null;
        try{
            File file = new File(HttpServer.WEB_ROOT,request.getUri());
            if(file.exists()){

                String headmsg = "HTTP/1.1 200 OK\r\n" +
                        "Server:httpserver\r\n" +
                        "Date:"+ DateUtil.currentRFC1123Date()+"\r\n" +
                        "Content-Type:text/html\r\n" +
                        "Content-Length:"+file.length()+"\r\n" +
                        "\r\n";
                System.out.println(headmsg);
                output.write(headmsg.getBytes());

                fis = new FileInputStream(file);
                int ch = fis.read(bytes);
                while (ch!=-1){
                    output.write(bytes,0,ch);
                    ch = fis.read();
                }
            }else{
                String errormsg = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type:text/html\r\n" +
                        "Content-Length:23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errormsg.getBytes());
            }
            output.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
