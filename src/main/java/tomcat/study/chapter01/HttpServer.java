package tomcat.study.chapter01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huwei on 2017/8/14.
 */
public class HttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator +"webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;


    public static void main(String[] args) throws IOException{
        HttpServer server = new HttpServer();
        int port = 8080;
        server.await(port);
    }

    public void await(int port) throws IOException{

        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
            System.out.println("server start...");
        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }


        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try{
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

                shutdown = SHUTDOWN_COMMAND.equals(request.getUri());

            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
