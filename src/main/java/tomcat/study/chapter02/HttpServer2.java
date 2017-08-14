package tomcat.study.chapter02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huwei on 2017/8/14.
 */
public class HttpServer2 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;


    public static void main(String[] args) throws IOException {
        HttpServer2 server = new HttpServer2();
        int port = 8080;
        server.await(port);
    }

    public void await(int port) throws IOException{

        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
            System.out.println("http server.2 start...");

        }catch (IOException e){
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

                Processor processor = null;

                if(request.getUri().startsWith("/servlet/")){
                    processor = new ServletProcessor2();
                }else{
                    processor = new StaticResourceProcessor();
                }

                processor.process(request,response);

                socket.close();

                shutdown = SHUTDOWN_COMMAND.equals(request.getUri());

            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
