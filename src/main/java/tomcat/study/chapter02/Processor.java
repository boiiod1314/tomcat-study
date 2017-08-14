package tomcat.study.chapter02;

import java.io.IOException;

/**
 * Created by huwei on 2017/8/14.
 */
public interface Processor {
    public void process(Request request,Response response) throws IOException;
}
