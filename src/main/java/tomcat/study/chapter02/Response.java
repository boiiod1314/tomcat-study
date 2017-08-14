package tomcat.study.chapter02;

import util.DateUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by huwei on 2017/8/14.
 */
public class Response implements ServletResponse{

    private Request request;
    private OutputStream output;
    private PrintWriter writer;

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
            File file = new File(Constants.WEB_ROOT,request.getUri());
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

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
       return new DefaultServletOutputStream(output);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(output,true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
