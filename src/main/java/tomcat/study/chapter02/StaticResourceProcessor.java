package tomcat.study.chapter02;

/**
 * Created by huwei on 2017/8/14.
 */
public class StaticResourceProcessor implements Processor{

    @Override
    public void process(Request request,Response response){
        response.sendStaticResource();
    }
}
