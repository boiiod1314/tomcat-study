package servlet.study;

import util.DateUtil;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by huwei on 2017/8/14.
 */

@WebServlet(
    name = "AsyncServlet01",
    urlPatterns = {"/AsyncServlet01"},
    asyncSupported = true,
    loadOnStartup = -1,
    initParams={
        @WebInitParam(name="k1",value ="v1"),
        @WebInitParam(name="k2",value ="v2")
    }
)
public class AsyncServlet01 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.println("into servlet time:"+ DateUtil.currentDate());

        AsyncContext ac = req.startAsync();

        AsyncExecutor executor = new AsyncExecutor(ac);
        executor.exec();


        out.println("done servlet time:"+DateUtil.currentDate());

    }


}
