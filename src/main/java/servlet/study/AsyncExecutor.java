package servlet.study;

import util.DateUtil;

import javax.servlet.AsyncContext;
import java.io.PrintWriter;

/**
 * Created by huwei on 2017/8/14.
 */
public class AsyncExecutor implements Runnable{

    private AsyncContext ac ;

    public AsyncExecutor(AsyncContext ac) {
        this.ac = ac;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);

            PrintWriter out = ac.getResponse().getWriter();
            out.println("handle done time:"+ DateUtil.currentDate());
            ac.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void exec(){
        Thread thread = new Thread(this);
        thread.start();
    }
}
