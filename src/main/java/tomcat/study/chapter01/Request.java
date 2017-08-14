package tomcat.study.chapter01;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by huwei on 2017/8/14.
 */
public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input){
        this.input = input;
    }

    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try{
            i = input.read(buffer);
        }catch (IOException e){
            e.printStackTrace();
            i = -1;
        }
        System.out.println("len="+i);
        for(int j=0;j<i;j++){
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());

        this.uri = parseURI(request.toString());
        System.out.println("uri="+this.uri);
    }


    private String parseURI(String requesString){
        if(requesString==null){
            return null;
        }
        int index1 = requesString.indexOf(" ");
        if(index1==-1){
            return null;
        }
        int index2 = requesString.indexOf(" ", index1 + 1);
        if(index2<=index1){
            return null;
        }
        String uri = requesString.substring(index1+1, index2);
        return uri;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
